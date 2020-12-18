package kr.or.ddit.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.vo.MemberVO;

@WebServlet("/member/removeMember.do")
public class MemberDeleteController extends HttpServlet{
	private IMemberService service = MemberServiceImpl.getInstance();
			
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
		ServiceResult result = service.removeMember(MemberVO.builder()
															.mem_id(mem_id)
															.mem_pass(mem_pass)
															.build());
		
		String goPage = null;
		switch (result) {
		case INVALIDPASSWORD:
			session.setAttribute("message", "비번 오류:");
			goPage = "redirect:/mypage.do";
			break;
		case FAILED:
			session.setAttribute("message", "서버 오류:");
			goPage = "redirect:/mypage.do";
			break;
		default:
			goPage = "forward:/login/logout.do";
			break;
		}
		
		
		boolean redirect = goPage.startsWith("redirect:");
		boolean forward = goPage.startsWith("forward:");
		if(redirect) {
			resp.sendRedirect(req.getContextPath() + goPage.substring("redirect:".length()));
		}else if(forward){
			req.getRequestDispatcher(goPage.substring("forward:".length())).forward(req, resp);
		}else{	
			req.getRequestDispatcher("/"+goPage+".tiles").forward(req, resp);
		}
		
	}
}





