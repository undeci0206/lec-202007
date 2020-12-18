package kr.or.ddit.member.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedHashMap;
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
		
		Map<String, String> errors = new LinkedHashMap<>();
		req.setAttribute("errors", errors);
		boolean valid = validate(member, errors);
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
	
	private boolean validate(MemberVO member, Map<String, String> errors) {
		boolean valid = true;
		if (StringUtils.isBlank(member.getMem_id())) {
			valid = false;
			errors.put("mem_id", "아이디는 필수입력");
		}
		if (StringUtils.isBlank(member.getMem_pass())) {
			valid = false;
			errors.put("mem_pass", "비밀번호는 필수입력");
		}else {
			Pattern regex = Pattern.compile("^(?=.*[0-9]+)(?=.*[a-z]+)(?=.*[A-Z]+).{5,12}$");
			Matcher matcher = regex.matcher(member.getMem_pass());
			valid = matcher.matches();
			if(!valid) errors.put("mem_pass", "비밀번호 형식 확인");
		}
		if (StringUtils.isBlank(member.getMem_name())) {
			valid = false;
			errors.put("mem_name", "이름는 필수입력");
		}
//		if (StringUtils.isBlank(member.getMem_regno1())) {
//			valid = false;
//			errors.put("mem_regno1", "주민번호1는 필수입력");
//		}
//		if (StringUtils.isBlank(member.getMem_regno2())) {
//			valid = false;
//			errors.put("mem_regno2", "주민번호2는 필수입력");
//		}
		if (StringUtils.isBlank(member.getMem_zip())) {
			valid = false;
			errors.put("mem_zip", "우편번호는 필수입력");
		}
		if (StringUtils.isBlank(member.getMem_add1())) {
			valid = false;
			errors.put("mem_add1", "주소1는 필수입력");
		}
		if (StringUtils.isBlank(member.getMem_add2())) {
			valid = false;
			errors.put("mem_add2", "주소2는 필수입력");
		}
		if (StringUtils.isBlank(member.getMem_hometel())) {
			valid = false;
			errors.put("mem_hometel", "집전번는 필수입력");
		}
		if (StringUtils.isBlank(member.getMem_comtel())) {
			valid = false;
			errors.put("mem_comtel", "회사전번는 필수입력");
		}
		if (StringUtils.isBlank(member.getMem_mail())) {
			valid = false;
			errors.put("mem_mail", "메일는 필수입력");
		}
		return valid;
	}
}
















