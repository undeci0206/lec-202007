package kr.or.ddit.servlet01;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.utils.TemplateUtils;

/**
 * 서블릿의 콜백메서드 종류
 *  lifecycle callback(singleton) : init, destroy
 *  request callback : service, doXXX
 *  1) service : request method 판단 -> do[MethodName] 콜백을 호출하여 요청 처리 위임.
 *  2) doXXX : request method 따라 처리될 구체적인 작업을 정의.
 */
//템플릿 메서드 : service , 후크 메서드 : getMimeType, getDataMap
public abstract class UseTemplateServlet extends HttpServlet{ //템플릿을 읽어들이는 작업 할 것임 . abstract : 단독 사용 불가
	
	@Override //service : 메서드에 상관없이 무조건 실행됨 doget, dopost전단계 호출되는 것
	
	//final : 이 메서드에서는 마임과 데이터맵만 생성 가능
	protected final void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType(getMimeType()); 
		
		Map<String, Object> dataMap = new HashMap<>();
		
		Map<String, String[]> parameterMap = request.getParameterMap(); //모든 파라미터는 request안의 map에 들어가있다.
		//dataMap.putAll(parameterMap);
		
		for(Entry<String, String[]> entry : parameterMap.entrySet()) {
			String paramName = entry.getKey();//파라미터 이름 꺼내기
			String[] paramValues = entry.getValue();//값 꺼내기
			dataMap.put(paramName, paramValues.length==1?paramValues[0]:paramValues); //값이 하나인 경우 문자로 들어감 
		}
		
		// 추상 클래스를 구현한 자식쪽에서 이 메서드를 구현할 것임.. 뭘 만들어낼진 모르지만 만들어낼것
		//그렇게 만든 데이터를 아래에서 사용 가능!
		getDataMap(dataMap, request); 
		
		//데이터 치환 필요. 완전한 소스 만들어냄
		String html = TemplateUtils.replaceTemplateToData(request, dataMap);
		
		//출력해줘야 함 -> outputStream. 응답 데이터를 내보냄
		try(
			PrintWriter out = response.getWriter();
		){
			out.println(html);
		}
	}
	
	public abstract String getMimeType(); //추상 메서드 //자식쪽에서 어떤 마임이든 만들 수 있음

	public abstract void getDataMap(Map<String, Object> dataMap, HttpServletRequest req); //상속받고 있는 실제 구현체에서 
}
