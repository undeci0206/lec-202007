package kr.or.ddit.composite;

import java.io.IOException;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class CompositeDispatcherServlet extends HttpServlet {
   @Override
   public void init(ServletConfig config) throws ServletException {
      super.init(config);

   }

   @Override
   protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      String servletPath = req.getServletPath(); // ex) /login/loginForm.composite -> 정규식 표현 : /(\\S+)\\.composite
      Pattern ptrn = Pattern.compile("/(\\S+)\\.composite");// 	\\S+:모든 경로. 공백없이 한문자 이상

      Matcher matcher = ptrn.matcher(servletPath);
      matcher.find();
      String logicalView = matcher.group(1); //group(0): 패턴 전체에 매칭되는 문자열 들어옴
      System.out.println(logicalView);

      Properties properties = (Properties) req.getServletContext().getAttribute("compositeConfig");
      String view = properties.getProperty("template");
      String includePathPtrn = properties.getProperty("contents");
      String includePath = includePathPtrn.replaceAll("\\$\\{\\w+\\}", logicalView); //정규 표현식 사용

      req.setAttribute("includePath", includePath);
      req.getRequestDispatcher(view).forward(req, resp);
   }
}