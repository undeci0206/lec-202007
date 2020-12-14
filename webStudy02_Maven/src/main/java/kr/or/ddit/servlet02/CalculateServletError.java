package kr.or.ddit.servlet02;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//exprPtrn -1값으로 나타날 수 있음
//연산자 검증 안함
//연산자 기호 사용 안 함
//이 파일은 GetMessageServletError로 하고 원 파일을 수정한다.
//@WebServlet("/03/calculate.do")
public class CalculateServletError extends HttpServlet{ //service, doPost 오버라이드 가능
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
		if(opParam==null || opParam.isEmpty()) {
			status = HttpServletResponse.SC_BAD_REQUEST;
			message = "연산자 누락";
		}
		if(status!=HttpServletResponse.SC_OK) {
			resp.sendError(status, message);
			return; //제어가 밑으로 내려오면 안된다.
		}
		
		//parsing
		int leftOp = Integer.parseInt(left);
		int rightOp = Integer.parseInt(right);
		int result = -1;
		
		switch (opParam) {
		case "PLUS":
			result = leftOp + rightOp;
			break;
		case "MINUS":
			result = leftOp - rightOp;
			break;
		case "MULPLY":
			result = leftOp * rightOp;
			break;
		case "DIVIDE":
			result = leftOp / rightOp;
			break;
			
		default:
			break;
		}
		
		String exprPtrn = "%d %s %d = %d";
		String responseData = String.format(exprPtrn, leftOp, opParam, rightOp, result);
		try(
			PrintWriter out = resp.getWriter();	
		){
			out.println(responseData);
		}
	}
}
