package kr.or.ddit.utils;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.enumpkg.MimeType;

public class JsonResponseUtils {
	//마샬링 해야 함-> jsondata 만들어짐. 직렬화도 함께 하자
	//외부 접근 가능 : public
	//객체 생성하지 않고도 사용할 수 있도록 : static
		public static void toJsonResponse(HttpServletRequest req, HttpServletResponse resp) throws IOException { 
			//IOException : 이 예외는 doPost가 떠안는다.doPost에서 throws가 되고 있기 때문에 최종적으로는 톰캣이 예외 가져감-> 500 상태코드로 나타냄
			Enumeration<String> names = req.getAttributeNames();
			String ptrn = " \"%s\" : \"%s\" "; //문제점 : 데이터가 무조건 문자열로만 감.. 마샬링 사용하는 라이브러리 써보자
			Map<String, Object> dataMap = new HashMap<>();
			while (names.hasMoreElements()) {
				String name = (String) names.nextElement(); //json properties가 될 것 key
				Object value = req.getAttribute(name); //value. 
				dataMap.put(name, value); //리퀘스트가 가지고 있는 모든 속성은 dataMap으로 옮겨감
			}
			ObjectMapper mapper = new ObjectMapper();
			String contentType = resp.getContentType();
			if(contentType == null) {
				resp.setContentType(MimeType.JSON.toString());
			}
			
			//다시 보기!
			
			try(
			PrintWriter out = resp.getWriter()//지수의 의견 여기가 마샬링! Outputstream..?클라이언트 측 보여줌.. 데이터가 넘어가는 건 이진.. 조합해서 보여주려면
			//문자는 2바이트 이상씩 넘어가니까 .. 그 형식에 맞춰서..! 
			){
			mapper.writeValue(out, dataMap); //여기가 직렬화! 마샬링과 직렬화를 한꺼번에 처리
			//왜 직렬화인데 바이너리 데이터가 아니라 Writer를 통해 텍스트로 출력되지..?
			
			}
		}
}
