package kr.or.ddit.servlet05;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.enumpkg.ServiceKind;

@WebServlet("/module.do")
public class ModulizationServlet extends HttpServlet{
	//service : 메서드에 상관없이 동작
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String service = req.getParameter("service");
		int status = HttpServletResponse.SC_OK;
		String includePath = null;
		
		if(StringUtils.isNotBlank(service)) {
			try{
				ServiceKind serviceKind = ServiceKind.valueOf(service);
				includePath = serviceKind.getMenu().getMenuPath();
				req.setAttribute("includePath", includePath);
			//catch문에 return은 안 넣는게 좋다.
			}catch(Exception e) {
				status = HttpServletResponse.SC_NOT_FOUND;
			}
		}
		
		if(status==HttpServletResponse.SC_OK) {
			req.getRequestDispatcher("/model1"+includePath+".tiles").forward(req, resp);
		}else {
			//이렇게 하면 catch문에 return을 넣지 않아도 된다.
			resp.sendError(status);
		
		}
		
	}
}
