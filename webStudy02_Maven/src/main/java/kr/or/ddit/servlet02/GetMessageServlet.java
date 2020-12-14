package kr.or.ddit.servlet02;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.enumpkg.MimeType;

@WebServlet("/02/getMessage.do") //URI에 더 가까움(논리적인 구조) -> 02에는 getMessage.do가 없다.
public class GetMessageServlet extends HttpServlet{ //오버라이드 할 수 있는 메서드 : doget, service
	
	@Override
	protected void doHead(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.getOutputStream().close();
	}
	
	//1. 클라이언트 헤더 분석 2. 로케일 분석 3. 파라미터 분석 4.데이터 분석 5. 최종적으로 선택하고 이동
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MimeType contentType = MimeType.parseAcceptToMimeType(request);
		
		response.setContentType(contentType.toString());
		String language = request.getParameter("lang");
		String acceptLanguage = request.getHeader("accept-language");
		Locale locale = request.getLocale();
		if(language!=null && !language.isEmpty()){ //파라미터에 따라 로케일을 바꿀 수 있어야 함
			//파라미터가 없다 : 기본 로케일, 있다 : 클라이언트가 선택한 로케일
			locale = Locale.forLanguageTag(language.toLowerCase()); 
		}
		ResourceBundle bundle = ResourceBundle.getBundle("kr.or.ddit.msg.message", locale);
		
		String message = bundle.getString("bow");
		
		//enum일 경우는 ==와 equals가 같다.
		if(MimeType.PLAIN.equals(contentType)) { //왼쪽에 미리 값 결정되어있는 것 쓰기(nullpoint방지)
			try(
				PrintWriter out = response.getWriter();
			){
				out.println(message);
			}
		}else if(MimeType.JSON==contentType) {
			//String message = bundle.getString("bow");를 JSON으로 만드려고 하는데 name과 값 쌍으로 존재하지 않는다.
			//존재하게 하려면.. 쌍을 가지고 있는 객체 생성해야 함
			//객체의 생성을 위해 클래스의 형태와 유사한 Map사용
			Map<String, Object> data = new HashMap<>();
			data.put("message", message);
			
			//**마샬링 : native언어로 표현되고 있는 데이터를 공통적인 표현 언어로 바꾸는 것. **언마샬링 : 반대
			//{"message" : "데이터"} 이런 형태 코드를 만들어야 하는데.. 우리는 JSP단에서 하고 있었던 것. jsp는 html단으로 나가야 할때만 사용하면 되므로 굳이 쓸 필요 X
			StringBuffer json = new StringBuffer();
			//형식이 고정되어있으려면 format함수 사용하자
			String propPtrn = " \"%s\" : \"%s\"  ";
			json.append("{");
			for( Entry<String, Object> entry : data.entrySet()) {
				String name = entry.getKey();
				Object value = entry.getValue();
				json.append(String.format(propPtrn, name, value));
				json.append(",");
			}
			int lastIdx = json.lastIndexOf(",");
			json.deleteCharAt(lastIdx);
			json.append("}");
			try(
				PrintWriter out = response.getWriter();
			){
				out.println(json);
			}
		}else if(MimeType.XML==contentType){
			//XML 마샬링 할때는 대상이 되는 객체가 먼저 필요하다
			Map<String, Object> data = new HashMap<>();
			data.put("message", message);
			StringBuffer xml = new StringBuffer();
			String propPtrn = "<%1$s>%2$s</%1$s>";
			xml.append("<root>");
			for( Entry<String, Object> entry : data.entrySet()) {
				String name = entry.getKey();
				Object value = entry.getValue();
				xml.append(String.format(propPtrn, name, value));
			}
			xml.append("</root>");
			try(
					PrintWriter out = response.getWriter();
				){
					out.println(xml);
				}
		}else { //기본설정. 어디에도 속하지 않음 -> html
			//scope를 이용해서 dataMap보내주기
			request.setAttribute("message", message);
			String view = "/WEB-INF/views/getMessage.jsp";
			//이동
			request.getRequestDispatcher(view).forward(request, response);
			
		}
		
	}
}
