/*package kr.or.ddit.servlet02;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.enumpkg.OperateType;


//CalculateServletError를 수정한 것 
//enum 사용하자
@WebServlet("/03/calculate.do")
public class CalculateServlet extends HttpServlet{ //service, doPost 오버라이드 가능
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
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
		try(
			PrintWriter out = resp.getWriter();	
		){
			out.println(responseData);
		}
	}
}
*/