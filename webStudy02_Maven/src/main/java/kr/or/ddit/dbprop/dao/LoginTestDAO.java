package kr.or.ddit.dbprop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import kr.or.ddit.db.ConnectionFactory;
import kr.or.ddit.vo.DataBasePropertyVO;
import kr.or.ddit.vo.MemberVO;

public class LoginTestDAO {
	public MemberVO selectMember(String mem_id, String mem_pass) {
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT MEM_ID, MEM_NAME, MEM_REGNO1, MEM_REGNO2 ");
		sql.append(" FROM MEMBER ");
		sql.append(" WHERE MEM_ID = ? AND MEM_PASS= ? ");
		MemberVO member = null;
		//1. connection 받아오기
		try(
			Connection conn = ConnectionFactory.getConnection();
		//2. 쿼리 객체 생성(Statement쓰기)
		//	Statement stmt = conn.createStatement(); (X)
			//쿼리 객체가 생성하는 단계에서 컴파일을 함. 동적으로 예약어를 추가 X 동적으로 쿼리 추가 X
			//동적으로 넘길 수 있는 건 데이터뿐이다.
			PreparedStatement stmt = conn.prepareStatement(sql.toString());
		){
		//3. 쿼리 실행
		//4. 결과 집합의 데이터 꺼내서 memberVO객체 꺼내기
		//이름,생일, 주민번호 조회
		//결과집합 가지고 있으면 memberVO return
			stmt.setString(1,  mem_id);
			stmt.setString(2,  mem_pass);
			
		//ResultSet rs = stmt.executeQuery(sql.toString()); (X)
			ResultSet rs = stmt.executeQuery();
			if(rs.next()){ //한 줄 내려가서 검색해야 하므로..
				member = new MemberVO();
				member.setMem_id(rs.getString("MEM_ID"));
				member.setMem_name(rs.getString("MEM_NAME"));
				member.setMem_regno1(rs.getString("MEM_REGNO1"));
				member.setMem_regno2(rs.getString("MEM_REGNO2"));
			}
			return member;
		}catch(SQLException e) {
		//예외를 톰캣에게 넘김.
		//throw new RuntimeException(); 이렇게 쓰면 안됨(원본 예외를 전달해주기 위해) 
			throw new RuntimeException(e);
		}
	}
}
