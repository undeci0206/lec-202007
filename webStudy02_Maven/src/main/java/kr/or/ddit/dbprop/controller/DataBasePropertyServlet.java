package kr.or.ddit.dbprop.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.db.ConnectionFactory;
import kr.or.ddit.dbprop.dao.DatabasePropertyDAO;
import kr.or.ddit.dbprop.service.DataBasePropertyService;
import kr.or.ddit.dbprop.service.IDataBasePropertyService;
import kr.or.ddit.utils.JsonResponseUtils;
import kr.or.ddit.vo.DataBasePropertyVO;

@WebServlet("/08/jdbcDesc.do")
public class DataBasePropertyServlet extends HttpServlet{
	//DatabasePropertyDAO dao = new DatabasePropertyDAO();
	//다형성 원리에 따라..
	IDataBasePropertyService service = new DataBasePropertyService();
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String property_name = request.getParameter("property_name");
		String property_value = request.getParameter("property_value");
		String description = request.getParameter("description");

		DataBasePropertyVO paramVO = DataBasePropertyVO.builder()
									.property_name(property_name)
									.property_value(property_value)
									.description(description)
									.build();
		
		List<DataBasePropertyVO> list = service.retrieveDataBaseProperty(paramVO);
		
		request.setAttribute("dbProps", list);
		
		String accept = request.getHeader("Accept");
		
		//json 포함하면 마샬링, 포함하지 않으면 forward
		if(accept.contains("json")) {
			JsonResponseUtils.toJsonResponse(request, response);
			
		/**
		위 한줄로 대체 가능. JsonResponseUtils 쓰지 않고 이 단계들도 직접 연습해보자
		json 포함하면 마샬링, 포함하지 않으면 forward
		if(accept.contains("json")) {
			//request안의 scope(map)을 대상으로
			//마임 세팅-> 출력 -> 마샬링-> 직렬화
			response.setContentType("application/json;charset=UTF-8");
			try(
				PrintWriter out = response.getWriter();
			){
				//request안의 map을 가져와야 함. 통째로 가져올 수는 없다.
				//우선 맵 형태를 가져와야 함
				Map<String, Object> requestScope = new HashMap<>();
				
				//안에 어떤 이름들이 있는지 찾아보자
				Enumeration<String> names = request.getAttributeNames();
				
				while (names.hasMoreElements()) {
					String name = (String) names.nextElement();
					Object value = request.getAttribute(name);
					requestScope.put(name, value);
				}
				ObjectMapper mapper = new ObjectMapper();
				mapper.writeValue(out, requestScope); //마샬링과 직렬화 동시에! 
			}
		 */
		}else {
			String logicalView = "08/jdbcDesc";
			request.getRequestDispatcher("/WEB-INF/views/"+logicalView +".jsp").forward(request, response);
		}
		
	}
}
