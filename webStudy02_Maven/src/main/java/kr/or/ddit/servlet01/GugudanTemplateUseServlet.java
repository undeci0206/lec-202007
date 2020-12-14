package kr.or.ddit.servlet01;

import java.util.Map;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

@WebServlet("/01/gugudan.tmpl")
public class GugudanTemplateUseServlet extends UseTemplateServlet {

	@Override
	public String getMimeType() {
		return "text/html;charset=UTF-8";
	}

	@Override
	public void getDataMap(Map<String, Object> dataMap, HttpServletRequest req) {
		String minDanStr = req.getParameter("minDan");
		String maxDanStr = req.getParameter("maxDan");
		
		//null값 아닌지. 숫자이며 2-9사이에 있는지 확인
		int minDan = 2;
		int maxDan = 9;
		if(minDanStr!=null && minDanStr.matches("[2-9]")) {
			minDan = Integer.parseInt(minDanStr); //서버 형태로 가지고 놀려면 타입 변환 필요
		}
		if(maxDanStr!=null && maxDanStr.matches("[2-9]")) {
			maxDan = Integer.parseInt(maxDanStr);
		}
			
		
		dataMap.put("title", "구구단");
		
		StringBuffer gugudanTrTags = new StringBuffer();
		
		dataMap.put("gugudanTrTags", gugudanTrTags);
		String ptrn = "<td>%d*%d=%d</td>";
		for(int dan=minDan; dan<=maxDan; dan++) {
			gugudanTrTags.append("<tr>");
			for(int mul=1; mul<=9; mul++) {
				gugudanTrTags.append(String.format(ptrn, dan, mul, (dan*mul))); //<td>2*2=4<td>//문자 포맷팅을 해 보자
			}
			gugudanTrTags.append("</tr>");
		}
	}
	
	

}
