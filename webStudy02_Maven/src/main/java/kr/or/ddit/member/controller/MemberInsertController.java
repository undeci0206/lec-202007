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
import kr.or.ddit.vo.MemberVO;

@WebServlet("/member/registMember.do")
public class MemberInsertController extends HttpServlet{
	private IMemberService service = MemberServiceImpl.getInstance();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String logicalView = "member/memberForm";
		req.getRequestDispatcher("/"+logicalView +".tiles").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		
		//파라미터 한꺼번에 받을 수 있어야 함
		MemberVO member = new MemberVO();
		req.setAttribute("member", member);
		
		//사실 아랫줄대로 하는게 정석인데, 번거로우므로 Beanutils 쓸 수 있음
		//member.setMem_id(req.getParameter("mem_id"));
		
		Map<String, String[]> parameterMap = req.getParameterMap();
		
		//reflection작업 이뤄짐
		try {
			BeanUtils.populate(member, parameterMap);
		} catch (IllegalAccessException | InvocationTargetException e) {
			//던질 수 있는 예외로 바꿔치기 한 것
			//protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException
			throw new ServletException(e);
		}//이제 MemberVO의 값이 채워졌다. 이제 validate로 이 값이 유효한지 검증하자
		
		//call-by-reference
		Map<String, List<String>> errors = new LinkedHashMap<>();
		req.setAttribute("errors", errors);
		CommonValidator<MemberVO> validator = new CommonValidator<>();
		boolean valid = validator.validate(member, errors, InsertGroup.class);
		String goPage = null;
		
		if(valid) {
			ServiceResult result = service.registMember(member);
			switch (result) {
			case PKDUPLICATED:
				goPage = "member/memberForm";
				break;
			case FAILED:
				goPage = "member/memberForm";
				break;
			case OK:
				goPage = "redirect:/login/loginForm.do";
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
		
		/*
		정석대로 하려면 원래 이렇게
		Class<MemberVO> memberType = MemberVO.class;
	
		//parameter명만 가지고 있는 iterator돌아옴
		//parameterMap.keySet().iterator();
		//윗줄과 같음. 단 iterator에서 enumeration으로 종류만 달라진 것
		Enumeration<String> names = req.getParameterNames();
		while(names.hasMoreElements()) {
			String parameterName = (String)names.nextElement();
			String parameterValue = req.getParameter(parameterName);
			try {
				PropertyDescriptor pd = new PropertyDescriptor(parameterName, memberType);
				//setter
				//member.setMem_id(req.getParameter("mem_id"));처럼 넘기자
				pd.getWriteMethod().invoke(member, parameterValue);
			
			//property가 없거나 getter,setter가 만들어지지 않았을 때
			} catch (Exception e) {
				//파라미터 버리면 됨
				continue;
			}
		}*/
	
	}
	
	
}

