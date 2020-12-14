package kr.or.ddit.servlet01;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/mime.do")
public class MimeDescriptionServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//MIME : Multipurposed Internet Mail Extension
		//main_type/sub_type;charset=encoding
		//text/html;charset=EUC-KR
		//text/plain;charset=UTF-8
		
		String mime = "text/plain;charset=UTF-8"; //response에 세팅해야 함 (client에게 줘야함)
		resp.setContentType(mime);
		String data = "안녕 서블릿";
		String html = "";
		html+="<html> ";
		html+="<body> ";
		html+="<h4>"+data+"</h4>	";
		html+="</body>";
		html+="</html>";
		//PrintWriter out = null;
		/*try{
			out = resp.getWriter();
			out.println(html);
		}catch(IOException e) {
			
		}finally {
			if(out!=null) {
				out.close();
			}
		}*/
		
		try (PrintWriter out = resp.getWriter();){
			out.println(html);
		} 
	}
}

