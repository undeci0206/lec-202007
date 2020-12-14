package kr.or.ddit.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;


public class TemplateUtils {
	public static String readTemplate(HttpServletRequest request) throws IOException {
		//tmpl 객체 읽기 위해 파일 생성     
		//실제 파일 경로나 resource경로를 가져와야 함
		
		//String tmplUrl = "/01/imageList.tmpl"; 이렇게 하드코딩하지 않고 현재 요청이 어떤 주소를 커맨드하고 있는지 찾아서 적어주기 
		//->request를 통해 찾으면 됨
		String requestURI = request.getRequestURI(); //request line에 있는 주소 꺼내자
		String cp = request.getContextPath(); //WebStudy01 //얘를 버려야 함..
		
		String tmplUrl = requestURI.substring(cp.length()); //자른다.
		URL url = request.getServletContext().getResource(tmplUrl);
		File tmplFile = new File(url.getFile());
		StringBuffer template = new StringBuffer(); //String이 아니라 Stringbuffer? 메모리
		
		//파일 읽어야 함 //fileReader를 만들지 않고(인코딩 설정 불가) FileInputStream만듦
		try(
			//1바이트로 쪼개서 읽다가 이들을 2개로 묶어서 합쳐서 읽을 때 인코딩 적용되는 것
			FileInputStream fis = new FileInputStream(tmplFile); 
				
			//1바이트와 2바이트를 연결해 주는 역할. 문자의 charset도 설정 가능
			InputStreamReader isr = new InputStreamReader(fis, "UTF-8"); 
			
			//버퍼를 이미 가지고 있는 스트림 사용하자!
			BufferedReader reader = new BufferedReader(isr);
		){
			String tmp = null;
			while((tmp = reader.readLine()) != null) {
				template.append(tmp+"\n"); //한 줄 통으로 읽기 줄바꿈까지 해 줌
			}
		}
		return template.toString();
		
	}
	
	static Pattern pattern = Pattern.compile("%([a-zA-Z0-9_]+)%"); //+:1글자 이상 반복 *:0글자 이상 반복
	
	//완전한 형태의 응답 데이터를 return 형태로 내보낼 것임-> 템플릿 필요
	public static String replaceTemplateToData(HttpServletRequest request, Map<String, Object> dataMap) throws IOException{
		String template = readTemplate(request);
		Matcher matcher = pattern.matcher(template);
		StringBuffer html = new StringBuffer();
		while(matcher.find()) {
			String dataName = matcher.group(1); //그룹이 하나만 존재하기 때문에
			Object data = dataMap.get(dataName); //return타입 Object
			//String value = data.toString();//데이터가 반드시 있다는 걸 보장할 수 없으므로.. 이렇게 쓰지 않음
			String value = Objects.toString(data, ""); //data==null?"":data.toString();를 대체할 수 있음 //1.7이후로 적용 가능
			matcher.appendReplacement(html, value);
		}
		
		matcher.appendTail(html);
//		String html = template.replace("%title%", (String)dataMap.get("title"));
//		html = html.replace("%optionData%", dataMap.get("optionData").toString());
//		String html = null;
		
		return html.toString();
		
	}
	
}
