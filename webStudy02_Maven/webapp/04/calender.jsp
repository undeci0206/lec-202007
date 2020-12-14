<%@page import="kr.or.ddit.enumpkg.ServiceKind"%>
<%@page import="java.util.TimeZone"%>
<%@page import="java.util.Locale"%>
<%@page import="java.text.DateFormatSymbols"%>
<%@page import="java.util.Calendar"%>
<%@page import="static java.util.Calendar.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
request.setCharacterEncoding("UTF-8");
String yearStr = request.getParameter("year");
String monthStr = request.getParameter("month");
String language = request.getParameter("language");
String timeZone = request.getParameter("timeZone");

Locale clientLocale = request.getLocale();
if(language!=null){
	clientLocale = Locale.forLanguageTag(language);
}
DateFormatSymbols dfs = DateFormatSymbols.getInstance(clientLocale); 

TimeZone zone = TimeZone.getDefault(); //서버의 기본 TimeZone 가져옴 (클라이언트가 타임존에 대한 정보를 주지 않았음)

if(timeZone!=null){
	zone = TimeZone.getTimeZone(timeZone);
}

Calendar cal = Calendar.getInstance(zone, clientLocale);

boolean currentFlag = true;
int currentYear = cal.get(YEAR);
int currentMonth = cal.get(MONTH);

//cal.set(Calendar.MONTH, 10-1);
int current = cal.get(DAY_OF_MONTH);
cal.set(DAY_OF_MONTH, 1); //날짜 데이터는 여러 개의 section으로 구성되어 있기 때문에, field 정보를 줘야 함

if(yearStr!=null && yearStr.matches("\\d{4}")){
	cal.set(YEAR, Integer.parseInt(yearStr));
}
if(monthStr!=null && monthStr.matches("[01]?[0-9]")){
	cal.set(MONTH, Integer.parseInt(monthStr));
}
if(cal.get(YEAR)!=currentYear || cal.get(MONTH)!=currentMonth){ //현재가 아닐 때 
	currentFlag = false;
}



int lastDate = cal.getActualMaximum(DAY_OF_MONTH);
int offset = cal.get(DAY_OF_WEEK)-1; //offset : 빈 칸의 개수 결정

cal.add(MONTH, -1);
int beforeYear = cal.get(YEAR);
int beforeMonth = cal.get(MONTH);

cal.add(MONTH, 2);
int nextYear = cal.get(YEAR);
int nextMonth = cal.get(MONTH);

//다시 현재 날짜로 캘린더 상태 바꾸기
cal.add(MONTH, -1);
%>
<style>
	td, th{
		border: 1px solid black;
	}
	table{
		border-collapse: collapse;
		width: 100%;
		height: 500px;
	}
	.red{
		color: red;
	}
	.blue{
		color: blue;
	}
	.current{
		background-color: orange;
	}
</style>
<a href="#" data-year = "<%=beforeYear %>" data-month="<%=beforeMonth %>" class="controlA">이전달</a>
<h4><%=String.format("%tc", cal) %></h4>
<a href="#" data-year="<%=nextYear%>" data-month="<%=nextMonth %>" class="controlA">다음달</a>
<form id="calendarForm" action="?service=CALENDER"> <!-- "?service=CALENDER"안됨 : bts는 post방식 form이었기 때문에 얘만 get써서 따로 보낼 수 있지만
이건 get방식이므로 여기서 get보내면 아래 get에 묻혀서 없어짐
이럴 때 hidden 쓰는 것 ! ! !  -->
<input type="hidden" name="service" value="<%=ServiceKind.CALENDER.name() %>" />
<input type="number" name="year" placeholder="2020" value="<%=cal.get(YEAR)%>"/>
<select name="month">
	<%
		String[] months = dfs.getMonths();
		String optPtrn = "<option value='%s' %s>%s</option>";
		for(int idx = 0; idx<months.length-1; idx++){
			String selected = idx==cal.get(MONTH)? "selected" : "";
			out.println(String.format(optPtrn, idx, selected, months[idx]));
		}
	%>
</select>
<select name="language">
	<%
		Locale[] locales = Locale.getAvailableLocales(); //배열로 리턴되는 타입
		for(Locale tmp : locales){
			String displayLanguage = tmp.getDisplayLanguage(tmp);
			if(displayLanguage.isEmpty()) continue;
			String selected = tmp.equals(clientLocale)?"selected":"";
			out.println(String.format(optPtrn, tmp.toLanguageTag(), selected, displayLanguage));
		}
	%>
	<option value="ko">한국어</option>
</select>

<select name="timeZone">
	<%
		String[] ids = TimeZone.getAvailableIDs();
		for(String zoneId : ids){
			TimeZone tmp = TimeZone.getTimeZone(zoneId);
			String selected = tmp.equals(zone)?"selected":"";
			out.println(String.format(optPtrn, zoneId, selected, tmp.getDisplayName(clientLocale)));
		}
	%>

</select>

<input type="submit" value="전송" />

</form>
<table>
<thead>
	<tr>
		<%
			String[] weekdays = dfs.getWeekdays();
			for(int idx=SUNDAY; idx<=SATURDAY; idx++){
				%>
				<th><%=weekdays[idx] %></th>
				<%
			}
		%>
	</tr>
</thead>
<tbody>
<!-- 스크립틀릿 기호 사용, for문 -->
<%
	String tdPtrn = "<td class = '%s'>%s</td>";
	int count = 1; //일련번호
	for(int row=1; row<=6; row++){
		out.println("<tr>");
		for(int col=1; col<=7; col++){
			int date = count++ - offset;
			String clz = col==1? "red": col==7? "blue" : currentFlag&&(date==current)? "current" : "normal";
			String dateStr = date < 1? "" : date>lastDate? "" : date+"";
			out.println(String.format(tdPtrn, clz, dateStr));
		}
		out.println("</tr>");
	}

%>
</tbody>
</table>
<script type="text/javascript">
	let calendarForm = $("#calendarForm");
	calendarForm.find(':input[name]').on("change", function(){// : -> 모든 입력태그를 포함(select태그,input태그 등..)
		calendarForm.submit(); //값이 바뀔때마다 바로바로 전송됨
	}); 
	$(".controlA").on("click", function(event){
		event.preventDefault();
		//form태그를 통해 대신 넘겨야 하는 파라미터 정해줌
		//let = 변수의 범위가 블럭 안으로 제한됨, var = 범위가 제한되지 않음
		//scope사용 시에는 let사용하는 게 좋다.
		let year = $(this).data("year");
		let month = $(this).data("month");
		$("#calendarForm").find("[name='year']").val(year); //자식들 중 name이라는 속성값이 year로 되어있는 것 selecting
		$("#calendarForm").find("[name='month']").val(month);
		//form 전송시키기
		$("#calendarForm").submit();
		return false;
	});
</script>