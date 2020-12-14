package kr.or.ddit.listener;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;


public class CompositeConfigInitializer implements ServletContextListener {

    public void contextInitialized(ServletContextEvent sce)  { 
    	ServletContext application = sce.getServletContext();//target이 되는 reference
    	System.out.printf("%s 초기화\n", application.getContextPath());//어떤 context가 실행되었는지 보고싶으니 contextPath를 넣자
    	//클래스패스 리소스로 존재하기 때문에 getClassLoader사용
    	Properties properties = new Properties();
    	application.setAttribute("compositeConfig", properties); //scope를 통해 전달
    	//***xml파일 안에 있는 객체 읽자
    	try(
    			InputStream is = application.getClassLoader().getResourceAsStream("compositeConfig.xml"); //클래스패스 바로 안에 있음
    			) {
			properties.loadFromXML(is);
		} catch (IOException e) { //스트림 사용하므로 IOException. 여기서 오류 날 경우 페이지 모듈화 X , 어플 중단되어야 함//현재 어플 운영 주체인 톰캣에게 넘겨주자. 
			//throws하지 않은 상태에서 톰캣에서 넘기는 방법? 예외 종류 바꾸기(IOException)
			//checkedException 예외 처리하지 않으면 컴파일 에러 남, uncheckedException 예외 처리하지 않아도 컴파일 오류 발생하지 않음(uncheckedException 최상위:런타임 exception)
			throw new RuntimeException(e); //아무것도 하지 않아도 호출자에게 예외 넘어감. 최종적으로 virtual machine까지 전달됨
		}
    }

    public void contextDestroyed(ServletContextEvent sce)  { 
    	ServletContext application = sce.getServletContext();
    	System.out.printf("%s 소멸\n", application.getContextPath());
    }

	
}
