package kr.or.ddit.servlet02;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.enumpkg.MimeType;
import kr.or.ddit.enumpkg.OperateType;
import kr.or.ddit.utils.JsonResponseUtils;

//model2구조로 jsp사용함
//CalculateServletError를 수정한 것 
//enum 사용하자
@WebServlet("/03/calculate.do")
public class CalculateServletAjax extends HttpServlet{ //service, doPost 오버라이드 가능

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		MimeType mime = MimeType.parseAcceptToMimeType(req);
		resp.setContentType(mime.toString());
		
		//03-calculateForm에서 required했어도 또 검증해야 함-> 못 믿어..!
		String left = req.getParameter("leftOp");
		String right = req.getParameter("rightOp");
		String opParam = req.getParameter("operator");
		int status = HttpServletResponse.SC_OK; //200
		String message = null;

		//검증
		if(left==null || !left.matches("[0-9]+")) { //+ : 1개 이상
			status = HttpServletResponse.SC_BAD_REQUEST;
			message = "좌측 피연산자 누락";
		}
		if(right==null || !left.matches("[0-9]+")) { 
			status = HttpServletResponse.SC_BAD_REQUEST;
			message = "우측 피연산자 누락";
		}
		OperateType operator = null;
		if(opParam==null || opParam.isEmpty()) {
			status = HttpServletResponse.SC_BAD_REQUEST;
			message = "연산자 누락";
		}else {
			//operator = OperateType.valueOf("sdfasg"); -> exception. 500발생 우려-> try문으로 예외 잡자!
			try {
				operator = OperateType.valueOf(opParam);
			}catch(IllegalArgumentException e) {
				status = HttpServletResponse.SC_BAD_REQUEST;
			}
		}

		//opParam에 대한 검증 필요
		if(status!=HttpServletResponse.SC_OK) {
			resp.sendError(status, message);
			return; //제어가 밑으로 내려오면 안된다.
		}

		//parsing
		int leftOp = Integer.parseInt(left);
		int rightOp = Integer.parseInt(right);
		int result = operator.operator(leftOp, rightOp);

		//이까지 넘어왔다면 operator가 정보 가지고 있을 것
		//연산을 위에 있는 enum에서 수행하는걸로 바꿔보자.
		switch (operator) {
		case PLUS:
			result = leftOp + rightOp;
			break;
		case MINUS:
			result = leftOp - rightOp;
			break;
		case MULTIPLY:
			result = leftOp * rightOp;
			break;
		case DIVIDE:
			result = leftOp / rightOp;
			break;
		default:
			break;//default는 존재할 수 없다(enum에서 그렇게 만들었기 때문에)
		}
		
		String exprPtrn = "%d %s %d = %d";
		String responseData = String.format(exprPtrn, leftOp, operator.getSign(), rightOp, result);
		Date now = new Date();
		//model2구조에서 response의 url담는 jsp있어야 함
		//jsp가 사용할 수 있도록 공유 영역(scope)만들어주자. scope의 실체는 Map이다.
		req.setAttribute("data", responseData);
		req.setAttribute("now", now);
		
		if(MimeType.HTML==mime) {
			//공유되고 있는 데이터를 누가 꺼내서 결정할지. view를 결정하고 이동시켜야함. calculate.jsp의 qualified name복사
			String view = "/WEB-INF/views/calculate.jsp";
			req.getRequestDispatcher(view).forward(req, resp); //state가 유지된다.
//			resp.sendRedirect(req.getContextPath() + view); //안됨
		}else if(MimeType.JSON==mime) {
			//json으로 응답데이터 내보낼 때, 모델2방식(서블릿안에 응답과 관련된 책임은 거의 없다. 책임은 Utils가)
			//금고 안에 데이터를 대신 받아줄 수 있는 것은 서블릿뿐만이 아니라 jsp도 될 수 있음
			JsonResponseUtils.toJsonResponse(req, resp); 
		}
		
	}

	
}