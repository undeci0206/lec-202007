package kr.or.ddit.servlet03;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/login/logout.do")
public class LogoutServlet extends HttpServlet{
	@Override
	public void init() throws ServletException {
		super.init();
		System.out.println(getServletContext().hashCode());
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		//현재 세션자체를 만료시켜야 함 -> 각 세션을 식별하기 위해 존재하는 세션의 id를 없애줌
		//세션식별시 id가 있다는 것..나중에 세션 id에 대해 뜯어보자.
		session.invalidate();
		
		String encoded = URLEncoder.encode("로그아웃성공", "UTF-8");
		
		//로그아웃이라는 명령이 끝남
		//로그아웃 시 session의 req도 모두 사라진 것
		resp.sendRedirect(req.getContextPath()+"/login/loginForm.jsp?message="+encoded); //클라이언트가 새로운 요청을 발생시킬 때 사용한다.

		//parameter
	}
}
