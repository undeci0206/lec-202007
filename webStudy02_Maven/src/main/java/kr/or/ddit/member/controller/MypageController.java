package kr.or.ddit.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.member.service.AuthenticateServiceImpl;
import kr.or.ddit.member.service.IAuthenticateService;
import kr.or.ddit.vo.MemberVO;

@WebServlet("/mypage.do")
public class MypageController extends HttpServlet{
	private IAuthenticateService service = AuthenticateServiceImpl.getInstance();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String logicalView = "member/passwordForm";
		req.getRequestDispatcher("/"+logicalView+".tiles").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String mem_pass = req.getParameter("mem_pass");
		if(StringUtils.isBlank(mem_pass)) {
			resp.sendError(400);
			return;
		}
		
		HttpSession session = req.getSession(false);
		if(session == null || session.isNew()) {
			resp.sendError(400);
			return;
		}
		
		MemberVO authMember = (MemberVO) session.getAttribute("authMember");
		String mem_id = authMember.getMem_id();
		
		Object result = service.authenticate(MemberVO.builder()
											.mem_id(mem_id)
											.mem_pass(mem_pass)
											.build());
		boolean redirect = false;
		String goPage = null;
		if(result instanceof MemberVO) {
			req.setAttribute("member", result);
			goPage = "member/mypage";
		}else {
			redirect = true;
			session.setAttribute("message", "비밀번호 오류, 다시 입력하시오.");
			goPage = "/mypage.do";
		}
		
		if(redirect) {
			resp.sendRedirect(req.getContextPath() + goPage);
		}else {
			req.getRequestDispatcher("/"+goPage+".tiles").forward(req, resp);
		}
	}
	
}
















