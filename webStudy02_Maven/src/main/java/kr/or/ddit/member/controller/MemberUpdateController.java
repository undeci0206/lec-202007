package kr.or.ddit.member.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.validate.CommonValidator;
import kr.or.ddit.validate.groups.InsertGroup;
import kr.or.ddit.validate.groups.UpdateGroup;
import kr.or.ddit.vo.MemberVO;

@WebServlet("/member/modifyMember.do")
public class MemberUpdateController extends HttpServlet{
	private IMemberService service = MemberServiceImpl.getInstance();
	
	private void addCommandAttribute(HttpServletRequest req) {
		req.setAttribute("command", "MODIFY");
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		addCommandAttribute(req);
		MemberVO authMember = (MemberVO) req.getSession().getAttribute("authMember");
		MemberVO member = service.retrieveMember(authMember.getMem_id());
		req.setAttribute("member", member);
		String goPage = "member/memberForm";
		
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
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		addCommandAttribute(req);
		req.setCharacterEncoding("UTF-8");
		MemberVO member = new MemberVO();
		req.setAttribute("member", member);
		Map<String, String[]> parameterMap = req.getParameterMap();
		try {
			BeanUtils.populate(member, parameterMap);
		} catch (IllegalAccessException | InvocationTargetException e) {
			throw new ServletException(e);
		}
		
		Map<String, List<String>> errors = new LinkedHashMap<>();
		req.setAttribute("errors", errors);
		CommonValidator<MemberVO> validator = new CommonValidator<>();
		boolean valid = validator.validate(member, errors, UpdateGroup.class);
		String goPage = null;
		
		if(valid) {
			ServiceResult result = service.modifyMember(member);
			switch (result) {
			case INVALIDPASSWORD:
				goPage = "member/memberForm";
				req.setAttribute("message", "비번 오류");
				break;
			case FAILED:
				goPage = "member/memberForm";
				req.setAttribute("message", "서버 오류");
				break;
			default:
				goPage =  "redirect:/mypage.do";
				MemberVO authMember =(MemberVO) req.getSession().getAttribute("authMember");
				try {
					BeanUtils.copyProperties(authMember, member);
				} catch (IllegalAccessException | InvocationTargetException e) {
					throw new RuntimeException(e);
				}
				break;
			}
		}else {
			goPage = "member/memberForm";
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
















