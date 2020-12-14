package kr.or.ddit.dbprop.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.db.ConnectionFactory;
import kr.or.ddit.vo.DataBasePropertyVO;

public class DatabasePropertyDAO {
	public List<DataBasePropertyVO> selectDBProperties(DataBasePropertyVO paramVO) {
	// 3. Connection 생성 (리모컨을 사용해야 함.)
		List<DataBasePropertyVO> list = new ArrayList<>();
		try(
		Connection conn = ConnectionFactory.getConnection();
		
	//4. Query 객체 생성
//					1) Statement
//					2) PreparedStatement
//					3) CallableStatement
		Statement stmt = conn.createStatement();
		){
			
	//5. Query 실행
//					1) ResultSet executeQuery() : 데이터 조회 목적. 오라클-자바 데이터 표현 방식이 다른데 접점을 생성해 줌. 결과집합(ResultSet)을 가져올 수 있는 메서드 필요
//					2) int executeUpdate() : insert, delete, update. row count 확인 가능 
//					필드에서는 아스트릭스(*)쓰지 않음. 컬럼명 명확히 적어주기
		
		StringBuffer sql = new StringBuffer("SELECT PROPERTY_NAME, PROPERTY_VALUE, DESCRIPTION FROM DATABASE_PROPERTIES"); 
		//조건절 가질 수 있도록 버퍼
		StringBuffer conditions = new StringBuffer();
		if(StringUtils.isNotBlank(paramVO.getProperty_name())) {
			//이 컬럼안에 키워드의 위치를 인덱스로 돌려줘라(자바의 indexOf). 인덱스는 1이상일것
			conditions.append(" OR INSTR(PROPERTY_NAME, '"+ paramVO.getProperty_name()+"') > 0 "); 
		}
		if(StringUtils.isNotBlank(paramVO.getProperty_value())) {
			conditions.append(" OR INSTR(PROPERTY_VALUE, '"+ paramVO.getProperty_value()+"') > 0 "); 
			
		}
		if(StringUtils.isNotBlank(paramVO.getDescription())) {
			conditions.append(" OR INSTR(DESCRIPTION, '"+ paramVO.getDescription()+"') > 0 "); 
			
		}
		
		if(conditions.length()>0) {
			sql.append(" WHERE ");
			//첫번째 OR 빼자
			int index = conditions.indexOf("OR");
			conditions.delete(index, index+2);
			sql.append(conditions);
		}
		
		ResultSet rs = stmt.executeQuery(sql.toString());
		
	//6. ResultSet 활용 : Set 순서 인덱스 없다. 임의 접근 불가능. 포인터를 계속 이동해서 while문안에서 next()사용할 수밖에 없음. 
//					*set의 형태로 만든 이유? 데이터 조회 시 커서 데이터를 가져올 수 있게 하기 위해
			
			while(rs.next()){ //한 줄 내려가서 검색해야 하므로..
				DataBasePropertyVO propVO = DataBasePropertyVO.builder()
											.property_name(rs.getString("PROPERTY_NAME"))
											.property_value(rs.getString("PROPERTY_VALUE"))
											.description(rs.getString("DESCRIPTION"))
											.build();
				
				list.add(propVO);
			}
			
			return list;
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
		
	}
}
