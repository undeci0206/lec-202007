package kr.or.ddit.servlet03;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;

import com.sun.swing.internal.plaf.metal.resources.metal;

import kr.or.ddit.dbprop.dao.LoginTestDAO;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.member.service.AuthenticateServiceImpl;
import kr.or.ddit.member.service.IAuthenticateService;
import kr.or.ddit.vo.MemberVO;

@WebServlet("/login/loginProcess.do")
public class LoginProcessServlet extends HttpServlet{
	IAuthenticateService service = new AuthenticateServiceImpl();
	
	//1.아이디 없음 2.비밀번호 오류 3.로그인 성공
	
//	private boolean validate(String mem_id, String mem_pass) {
		//있나없나확인
		
		/*//클라이언트가 보낸 데이터는 언제나 검증을 한다.
		//문자열 비어있는지 확인하는것 번거로우므로 라이브러리 쓰자.
		//아파치에서 lang3 라이브러리 다운받기
		if(StringUtils.isBlank(mem_id)||StringUtils.isBlank(mem_pass)) {
			//필수 파라미터 누락, 데이터의 타입이나 길이가 잘못되었을 때 400. 
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "아이디나 비밀번호 누락");
			return;
		}
*/
//	}
	
	private boolean validate(String mem_id, String mem_pass) {
		return true;
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String mem_id = req.getParameter("mem_id");
		String mem_pass = req.getParameter("mem_pass");
		
		if(validate(mem_id, mem_pass)) {
			
		}
		
		HttpSession session = req.getSession();
		Object result  = service.authenticate(MemberVO.builder()
												.mem_id(mem_id)
												.mem_pass(mem_pass)
												.build());
		if(result instanceof MemberVO) {
			MemberVO authMember = (MemberVO) result;
			session.setAttribute("authMember", authMember);
			resp.sendRedirect(req.getContextPath());
		}else {
			String message = null;
			if(ServiceResult.NOTEXIST.equals(result)) {
				message = "아이디 오류, 그런 사람 없음.";
			}else {
				message = "비번 오류, 다시 입력하셈.";
				session.setAttribute("mem_id", mem_id);
			}
			session.setAttribute("message", message);
			resp.sendRedirect(req.getContextPath() + "/login/loginForm.do");	
		}
	}
}

