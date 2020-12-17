package kr.or.ddit.commons.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.commons.service.ISearchZipService;
import kr.or.ddit.commons.service.SearchZipServiceImpl;
import kr.or.ddit.utils.JsonResponseUtils;
import kr.or.ddit.vo.ZipVO;

@WebServlet("/commons/searchZip.do")
public class SearchZipController extends HttpServlet{
	private ISearchZipService service = SearchZipServiceImpl.getInstance();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<ZipVO> zipList = service.retrieveZipList(null);
		req.setAttribute("zipList", zipList);
		
		JsonResponseUtils.toJsonResponse(req, resp);
	
	}
}













