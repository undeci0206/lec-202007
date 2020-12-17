package kr.or.ddit.member.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.vo.MemberVO;

@WebServlet("/member/idCheck.do")
public class IdCheckController extends HttpServlet{
	private IMemberService service = MemberServiceImpl.getInstance();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String inputId = req.getParameter("inputId");
		if(StringUtils.isBlank(inputId)) {
			resp.sendError(400);
			return;
		}
		
		boolean canUse= service.retrieveMember(inputId)==null;
		resp.setContentType("text/plain");
		try(
			PrintWriter out = resp.getWriter();	
		){
			out.println(canUse?ServiceResult.OK.name():ServiceResult.FAILED.name());
		}
	}
}











