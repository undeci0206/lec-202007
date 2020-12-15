package kr.or.ddit.explorer;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.enumpkg.FileWrapper;


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
		String realPath = application.getRealPath(base); //가상의 논리 경로라도 실제 systemPath 받아올 수 있다.
		File file = new File(realPath);//root에 해당하는 파일 객체 생성
		Set<String> resourcePaths = application.getResourcePaths(base); //주석으로 묶은 list와 동일한 내용 가짐
		List<FileWrapper> list = new ArrayList<>(); //파일들 담을 list 생성
		for(String relativePath : resourcePaths) {
			//path는 virtualPath (contextPath 이후의 path만 가짐)
			//돌아오는 fileRealPath : 진짜 경로
			String fileRealPath = application.getRealPath(relativePath); 
			File child = new File(fileRealPath);
			list.add(new FileWrapper(child, relativePath));
		}
//		File[] list = file.listFiles();// 리턴 타입으로 배열
		req.setAttribute("children", list);
		
		String logicalView = "others/explorer";
		req.getRequestDispatcher("/"+logicalView+".tiles").forward(req, resp);
	}
}
