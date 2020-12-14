package kr.or.ddit.servlet02;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/imageList.do")
public class ImageListServlet_Model2 extends HttpServlet{
	// 전역변수. 모든 클라이언트가 이 전역변수를 같이 쓴다.(주의:가능하면 전역변수도 싱글톤일때만 따로 빼라.)
	ServletContext application; 
	File folder;
	
	//lifecycle callback method
	@Override
	public void init() throws ServletException {
		super.init();
		application = getServletContext(); //Returns a reference to the ServletContext
		String contentFolder = application.getInitParameter("contentFolder");
		folder = new File(contentFolder);
	}

	//get메소드로 한정시키겠다.
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String[] children = folder.list((dir,name)->{
			String mime = application.getMimeType(name);
			return mime!=null && mime.startsWith("image");
		});
		
		//이것을 어떻게 jsp로 보낼까? tmpl에서는 dataMap으로 보냈는데..jsp에는 dataMap이 존재하지 않음
		String title = "이미지 목록"; 
		req.setAttribute("title", title);
		req.setAttribute("today", new Date());
		req.setAttribute("imageFiles", children);
		
		// 서버가 사용할 것이기 때문에 contextPath이후 경로만 기술되어야 함. forward방식!
		req.getRequestDispatcher("/WEB-INF/views/imageList.jsp").forward(req, resp); 
	}
}
