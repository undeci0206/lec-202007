package kr.or.ddit.prod.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.prod.dao.IOthersDAO;
import kr.or.ddit.prod.dao.OthersDAOImpl;
import kr.or.ddit.prod.service.IProdService;
import kr.or.ddit.prod.service.ProdServiceImpl;
import kr.or.ddit.utils.JsonResponseUtils;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ProdVO;
import kr.or.ddit.vo.SearchVO;

@WebServlet("/prod/prodList.do")
public class ProdListController extends HttpServlet{
	private static final Logger logger = LoggerFactory.getLogger(ProdListController.class);
	private IProdService service = ProdServiceImpl.getInstance();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		
		String pageParam = req.getParameter("page");
		ProdVO searchDetail = new ProdVO();
		try {
			BeanUtils.populate(searchDetail, req.getParameterMap());
		} catch (IllegalAccessException | InvocationTargetException e) {
			throw new RuntimeException(e);
		}
		
		int currentPage = 1;//첫 페이지 - 파라미터 없음. 대비해서 
		if(StringUtils.isNotBlank(pageParam) && StringUtils.isNumeric(pageParam)) {
			currentPage = Integer.parseInt(pageParam);
		}
		
		PagingVO<ProdVO> pagingVO = new PagingVO<>(7, 3);
		//목록 조회와 카운트 조회 시 동일 검색 조건 사용 - searchVO는 반드시 검색 전에 setting
		//pagingVO.setSearchVO(searchVO);
		pagingVO.setSearchDetail(searchDetail); //페이지 안에 상세조건까지 들어감

		
		int totalRecord = service.retrieveProdCount(pagingVO);
		pagingVO.setTotalRecord(totalRecord);
		pagingVO.setCurrentPage(currentPage);
		
		List<ProdVO> prodList = service.retrieveProdList(pagingVO);
		pagingVO.setDataList(prodList);
		
		req.setAttribute("pagingVO", pagingVO);
		
		String accept = req.getHeader("Accept");
		if(StringUtils.containsIgnoreCase(accept, "json")) {
			JsonResponseUtils.toJsonResponse(req, resp);
			
		}else {
			String goPage = "prod/prodList";
			
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
}









