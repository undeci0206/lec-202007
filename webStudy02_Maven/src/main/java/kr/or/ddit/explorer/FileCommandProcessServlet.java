package kr.or.ddit.explorer;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet("/server/fileCommand.do")
public class FileCommandProcessServlet extends HttpServlet{
	private ServletContext application;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		application = getServletContext();
	}
	
	//인터페이스가 단 하나의 추상메서드 가짐. 1:1 구조
	@FunctionalInterface 
	public static interface FileCommandProcessor{
		//파일 이동 또는 복사. 파라미터 2개
		public boolean process(File src, File destFolder) throws IOException;
	}
	public static enum FileCommand {
		COPY((src, destFolder)->{ //stream copy
			FileUtils.copyFileToDirectory(src, destFolder) ;
			return true;
		}), 
		DELETE((src, destFolder)->{
			return FileUtils.deleteQuietly(src);
		}),
		MOVE((src, destFolder)->{ //stream copy 후 지우기
			boolean result = false;
			//복사 성공
			if(COPY.fileCommand(src, destFolder)) {
				//삭제
				result = DELETE.fileCommand(src, destFolder);
			}
			return result;
		});
		private FileCommandProcessor processor;
		FileCommand(FileCommandProcessor processor) {
			this.processor = processor;
		}
		
		public boolean fileCommand(File src, File destFolder) throws IOException{
			return processor.process(src, destFolder);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String cmdParam = (String)req.getParameter("command");
		String srcParam = (String)req.getParameter("srcFile");
		String destParam = (String)req.getParameter("destFolder");

		//파라미터 검증
		int status = 200;
		status = validate(cmdParam, srcParam, destParam);
		
		if(status!=200) {
			resp.sendError(status);
			return;
		}
		
		//검증 통과함. 실제 command에 대한 처리 이루어져야 함
		//파일 처리할 수 있는 객체 형태로 만들기
		File src = new File(application.getRealPath(srcParam));
		File destFolder = new File(application.getRealPath(destParam));
		FileCommand command = FileCommand.valueOf(cmdParam);
		boolean result = command.fileCommand(src, destFolder);
		String message = result?"SUCCESS":"FAIL";
		
		
		resp.setContentType("text/plain;charset=UTF-8");
		try(
			PrintWriter out = resp.getWriter();
		){
			out.print(message);
		}
	
	}

	//검증 작업 수행
	private int validate(String cmdParam, String srcParam, String destParam) {
		int status = 200;
		FileCommand command = null;
		try {
			//enum에 포함되어 있는지 확인
			command = FileCommand.valueOf(cmdParam);
		}catch(Exception e) {
			status = 400;
		}
		
		if(status==200) {
			if(StringUtils.isBlank(srcParam)) {
				status = 400;
			}else {
	           File srcFile = new File(application.getRealPath(srcParam));
	           if(!srcFile.exists() || !srcFile.isFile()) status = 400;
	        }
			
			switch (command) {
			case COPY:
			case MOVE:
				if(StringUtils.isBlank(destParam)) {
					status = 400;
				}else {
					//폴더 있는지 확인
					File destFolder = new File(application.getRealPath(destParam));
					if(!destFolder.exists()||!destFolder.isDirectory()) status = 400;
				}
				break;
			}
		}
		return status;
	}
}
