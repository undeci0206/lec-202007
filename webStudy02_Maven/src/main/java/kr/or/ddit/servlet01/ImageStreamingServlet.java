package kr.or.ddit.servlet01;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class imageStreamingServlet
 */
@WebServlet("/imageView.do")
public class ImageStreamingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	File folder;
    @Override
    public void init() throws ServletException {
    	super.init();
    	String contentFolder = getServletContext().getInitParameter("contentFolder");
//    	String contentFolder = config.getInitParameter("contentFolder");
    	folder = new File(contentFolder);
    }
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String imageName = request.getParameter("image");
		
		//필수 파라미터가 정상적으로 잘 넘어왔는지 체크해 주어야 함
		if(imageName == null || imageName.isEmpty()) {
			//상태코드를 임의로 변경해 줄 수 있어야 함.
			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
			
			return;
		}
		
		File imageFile = new File(folder, imageName);//이미지 객체로 만들기
		
		//imageName이 폴더에 있는지 확인해야 함
		if(!imageFile.exists()) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return;
		}
		
		//mime체킹-이미지인지 아닌지
		if(!getServletContext().getMimeType(imageName).startsWith("image")) {
			response.sendError(400);
			return;
		}
		
		//mime 설정 :	this method is called before getWriteris called. 
		//(OutputStream os = response.getOutputStream(); 전에 설정해 줘야 함) 
		String mime = getServletContext().getMimeType(imageFile.getName());
		response.setContentType(mime);
		try(
			//스트림 생성
			FileInputStream fis = new FileInputStream(imageFile);
			OutputStream os = response.getOutputStream(); //최상위
		){
			//end만날 때까지 입력과 출력 반복. 반복 수 줄이기 위해 버퍼 생성
			byte[] buffer = new byte[1024];
			int cnt = -1;
			while((cnt = fis.read(buffer))!=-1 ) { //EOF 문자 (파일의 끝) : -1
				os.write(buffer, 0, cnt); // 버퍼를 비워주거나 버퍼에게 기록 범위를 지정해줘야 함 write(byte[] b, int off, int len)
				//이미지라는 것 알려주기.
			}
		}
	}

	

}
