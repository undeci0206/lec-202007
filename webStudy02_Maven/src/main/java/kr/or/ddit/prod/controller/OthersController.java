package kr.or.ddit.prod.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.prod.dao.IOthersDAO;
import kr.or.ddit.prod.dao.OthersDAOImpl;
import kr.or.ddit.utils.JsonResponseUtils;
import kr.or.ddit.vo.BuyerVO;

@WebServlet("/prod/getOthers.do")
public class OthersController extends HttpServlet{
	private IOthersDAO dao = OthersDAOImpl.getInstance();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//json 데이터로 비동기 요청에 대한 응답의 형태로 bL/lL 제공
		List<BuyerVO> buyerList = dao.selectBuyerList();
		List<Map<String, Object>> lprodList = dao.selectLprodList();
		req.setAttribute("lprodList", lprodList);
		req.setAttribute("buyerList", buyerList);
	
		JsonResponseUtils.toJsonResponse(req, resp);//마샬링, 직렬화
		
	}
}
