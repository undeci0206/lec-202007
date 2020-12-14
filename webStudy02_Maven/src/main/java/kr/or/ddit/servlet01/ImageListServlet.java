package kr.or.ddit.servlet01;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class imageListServlet
 */
//@WebServlet("/01/imageList.tmpl") //서블릿에 의해 동적인 요청의 형태로 데이터가 처리됨
public class ImageListServlet extends UseTemplateServlet {
	private static final long serialVersionUID = 1L;
	
	File folder;
    @Override
    public void init() throws ServletException {
    	super.init();
    	String contentFolder = getServletContext().getInitParameter("contentFolder");
//    	String contentFolder = config.getInitParameter("contentFolder");
    	folder = new File(contentFolder);
    }
	
	/*protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.setContentType("text/html;charset=UTF-8");
		
		// 원하는 파일 확장자 아닌 파일도 들어있을 때 filter 기능 사용	
		File folder = new File("d:/contents"); // file system resource
		String[] imageList = folder.list((dir, name)->{
			//mime을 checking해서 이미지 파일인지 확인 //servletContext사용
			String mime = getServletContext().getMimeType(name);
			//return은 boolean 타입 //톰캣이 가지고 있는 mime타입 안에 있어야 하므로, 
			//return mime.startsWith("image");로
			//쓰면 nullpoint. mime!=null 써주자
			return mime!=null && mime.startsWith("image"); 
			
		}); 
		String pattern = "<option value='%1$s'>%1$s</option>";
		
		//String과 Stringbuffer 차이 - 메모리 구조에 대해 설명할 수 있어야 함
		StringBuffer options = new StringBuffer("");
		for(String filename: imageList) {
			options.append(String.format(pattern, filename, filename)); //기존의 객체 상태를 자꾸 바꿈. 메모리 객체 상태를 해결 가능
		}
		
		Map<String, Object> dataMap = new HashMap<>();
		dataMap.put("title","이미지 목록");
		dataMap.put("optionData", options);//치환해야 하는 데이터를 모두 Map이 가지고 있다.
		
		//아랫부분 TemplateUtils 파일로 복붙하기
		//tmpl 객체 읽기 위해 파일 생성  
		//실제 파일 경로나 resource경로를 가져와야 함
		
		//String tmplUrl = "/01/imageList.tmpl"; 이렇게하드코딩하지 않고 현재 요청이 어떤 주소를 커맨드하고 있는지 찾아서 적어주기 
		//->request를 통해 찾으면 됨
		
		String requestURI = request.getRequestURI(); //request line에 있는 주소 꺼내자
		String cp = request.getContextPath(); //WebStudy01 //얘를 버려야 함..
		String tmplUrl = requestURI.substring(cp.length()); //자른다.
		URL url = getServletContext().getResource(tmplUrl);
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
		
		//데이터 치환 필요.
		String html = TemplateUtils.replaceTemplateToData(request, dataMap);
		
		//출력해줘야 함 -> outputStream
		try(
			PrintWriter out = response.getWriter();
		){
			out.println(html);
		}
		
		
	}*/

    @Override
	public String getMimeType() {
		return "text/html;charset=UTF-8";
	}

	@Override
	public void getDataMap(Map<String, Object> dataMap, HttpServletRequest req) { //request callback
		File folder = new File("d:/contents"); // file system resource
		String[] imageList = folder.list((dir, name)->{
			//mime을 checking해서 이미지 파일인지 확인 //servletContext사용
			String mime = getServletContext().getMimeType(name);
			//return은 boolean 타입 //톰캣이 가지고 있는 mime타입 안에 있어야 하므로, 
			//return mime.startsWith("image");로
			//쓰면 nullpoint. mime!=null 써주자
			return mime!=null && mime.startsWith("image"); 
			
		}); 
		String pattern = "<option value='%1$s'>%1$s</option>";
		
		//String과 Stringbuffer 차이 - 메모리 구조에 대해 설명할 수 있어야 함
		StringBuffer options = new StringBuffer("");
		for(String filename: imageList) {
			options.append(String.format(pattern, filename, filename)); //기존의 객체 상태를 자꾸 바꿈. 메모리 객체 상태를 해결 가능
		}
		
		dataMap.put("title","이미지 목록");
		dataMap.put("optionData", options);
		dataMap.put("today", new Date());
		
	}
}
