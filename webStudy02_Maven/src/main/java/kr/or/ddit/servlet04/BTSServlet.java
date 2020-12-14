package kr.or.ddit.servlet04;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.vo.BtsVO;

@WebServlet(urlPatterns="/bts", loadOnStartup=1)
public class BTSServlet extends HttpServlet{

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		System.out.println(this.getClass().getSimpleName()+"초기화됨.");
		Map<String, BtsVO> btsDB = new LinkedHashMap<>();
		getServletContext().setAttribute("btsDB", btsDB);
		btsDB.put("B001", BtsVO.getBuilder().code("B001").name("뷔").url("/bts/bui").build());
		btsDB.put("B002", BtsVO.getBuilder().code("B002").name("제이홉").url("/bts/jhop").build());
		btsDB.put("B003", BtsVO.getBuilder().code("B003").name("지민").url("/bts/jimin").build());
		btsDB.put("B004", BtsVO.getBuilder().code("B004").name("진").url("/bts/jin").build());
		btsDB.put("B005", BtsVO.getBuilder().code("B005").name("정국").url("/bts/jungkuk").build());
		btsDB.put("B006", BtsVO.getBuilder().code("B006").name("RM").url("/bts/rm").build());
		btsDB.put("B007", BtsVO.getBuilder().code("B007").name("슈가").url("/bts/suga").build());
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//Map도 함께 가져가야 함. 최소한의 공유영역에 Map을 넣자
		//모든 클라이언트가 변하지 않는 값(멤버정보)에 접근한다면 servletContext사용하자.
		//init에서 getServletContext().setAttribute("btsMap", btsDB); 설정
		
		req.getRequestDispatcher("/btsForm.tiles").forward(req, resp);  //forward에서 include로 바꾸기
		//어플리케이션 스코프를 통해 페이지로 Map이 넘어간 상태.
	
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		 String selected = req.getParameter("bts");
		 
		 //명령이 처리된 시간 
		 //최소한의 영역:request라는 스코프.. 계속 달라지는 데이터이므로 btsMap과 다르다.
		 Date now = new Date();
		 req.setAttribute("now", now);
		 
		 //클라이언트가 보내준 데이터 검증하기
		 if(StringUtils.isBlank(selected)) {
			 resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
			 return;
		 }
		 
		 //서블릿이 아니더라도 리퀘스트만 가지고 있으면 필요한 것 찾을 수 있다.
		 Map<String, BtsVO> btsDB = (Map) req.getServletContext().getAttribute("btsDB");
		
		 //만약 사용자가 B099를 호출했다면? 없는 값..
		 if(!btsDB.containsKey(selected)) {
			 resp.sendError(HttpServletResponse.SC_NOT_FOUND);
			 return;
		 }
		 
		 BtsVO selectedMember = btsDB.get(selected);
		 req.getSession().setAttribute("selected", selectedMember);
		 String logicalView = selectedMember.getUrl(); //멤버의 개인 페이지 주소가 나옴
//		 req.getRequestDispatcher("/WEB-INF/views"+view).forward(req, resp); //forward에서 include로 바꾸기
		 req.getRequestDispatcher(logicalView+".tiles").forward(req, resp); 
		 
	}
	
}
