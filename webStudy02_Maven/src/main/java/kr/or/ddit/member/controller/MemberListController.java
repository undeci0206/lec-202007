package kr.or.ddit.member.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.SearchVO;

@WebServlet("/member/memberList.do")
public class MemberListController extends HttpServlet {
	private IMemberService service = MemberServiceImpl.getInstance();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String pageParam = req.getParameter("page");
		String searchType = req.getParameter("searchType");	
		String searchWord = req.getParameter("searchWord");	
		
		SearchVO searchVO = new SearchVO(searchType,searchWord);
		
		int currentPage = 1;//첫 페이지 - 파라미터 없음. 대비해서 
		if(StringUtils.isNotBlank(pageParam) && StringUtils.isNumeric(pageParam)) {//있으면서 숫자가 맞으면
			currentPage = Integer.parseInt(pageParam);
		}
			
		PagingVO<MemberVO> pagingVO = new PagingVO<>(3,2);
		
		//목록 조회와 카운트 조회 시 동일 검색 조건 사용
		pagingVO.setSearchVO(searchVO);
		int totalRecord = service.retrieveMemberCount(pagingVO);
		pagingVO.setTotalRecord(totalRecord);
		pagingVO.setCurrentPage(currentPage);
		
		List<MemberVO> memberList = service.retrieveMemberList(pagingVO);
		pagingVO.setDataList(memberList);
		
		req.setAttribute("pagingVO", pagingVO);
		
		String goPage = "member/memberList";
		
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
