package kr.or.ddit.explorer;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;


@WebServlet("/server/explorer.do")
public class ServerSideExplorerServlet extends HttpServlet{
	private ServletContext application; 
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		application = getServletContext();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String base = req.getParameter("base"); //클라이언트가 선택한 경로
		if(StringUtils.isBlank(base)) {
			base = "/";
		}
		
		List<FileWrapper> list = makeData(base);
		
		//accept에 json 여부에 따라 경로 달라지게
		String accept = req.getHeader("Accept");
		
		//대소문자 구분없음. json포함되었는지 보기
		if(StringUtils.containsIgnoreCase(accept, "json")) { //마샬링해서 내보내기
			//마임 설정
			resp.setContentType("application/json;charset=UTF-8");
			//응답 데이터 기록할 stream 생성
			try(
				PrintWriter out = resp.getWriter();
			) {
				ObjectMapper mapper = new ObjectMapper();
				mapper.writeValue(out, list);
			}
		}else {
			req.setAttribute("children", list); //이동하는 경우에만 scope 사용
			String logicalView = "others/explorer";
			req.getRequestDispatcher("/"+logicalView+".tiles").forward(req, resp);
		}
	}

	private List<FileWrapper> makeData(String base) {
		String realPath = application.getRealPath(base); //가상의 논리 경로라도 실제 systemPath 받아올 수 있다.
		Set<String> resourcePaths = application.getResourcePaths(base); //주석으로 묶은 list와 동일한 내용 가짐
		List<FileWrapper> list = new ArrayList<>(); //파일들 담을 list 생성
		if(resourcePaths!=null) {
			TreeSet<String> treeSet = new TreeSet<>(resourcePaths);//생성자 안에 adaptee 넘겨줘야 함
			for(String relativePath : treeSet) {
				//path는 virtualPath (contextPath 이후의 path만 가짐)
				//돌아오는 fileRealPath : 진짜 경로
				String fileRealPath = application.getRealPath(relativePath); 
				File child = new File(fileRealPath);
				list.add(new FileWrapper(child, relativePath));
			}
		}
		Collections.sort(list);
		return list;
	}
	
	
}
