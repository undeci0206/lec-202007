package kr.or.ddit.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import kr.or.ddit.db.ConnectionFactory;
import kr.or.ddit.vo.MemberVO;

public class MemberDAOImpl implements IMemberDAO {

	@Override
	public MemberVO selectMember(String mem_id) {
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT MEM_ID ,MEM_PASS      ,MEM_NAME        ,MEM_REGNO1      ,MEM_REGNO2      ,TO_CHAR(MEM_BIR, 'YYYY-MM-DD') MEM_BIR,       "
				+ ",MEM_ZIP         ,MEM_ADD1        ,MEM_ADD2        ,MEM_HOMETEL     ,MEM_COMTEL"
				+ " ,MEM_HP          ,MEM_MAIL        ,MEM_JOB         ,MEM_LIKE     "
				+ " ,MEM_MEMORIAL    ,TO_CHAR(MEM_MEMORIALDAY, 'YYYY-MM-DD') MEM_MEMORAILDAY, "
				+ ",MEM_MILEAGE     ,MEM_DELETE       ");
		sql.append(" FROM MEMBER ");
		sql.append(" WHERE MEM_ID = ? ");
		
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
			
		//ResultSet rs = stmt.executeQuery(sql.toString()); (X)
			ResultSet rs = stmt.executeQuery();
			if(rs.next()){ //한 줄 내려가서 검색해야 하므로..
				member = MemberVO.builder()
						.mem_id(rs.getString("MEM_ID"))
						.mem_pass(rs.getString("MEM_PASS"))
						.mem_name(rs.getString("MEM_NAME"))
						.mem_regno1(rs.getString("MEM_REGNO1"))
						.mem_regno2(rs.getString("MEM_REGNO2"))
						.mem_bir(rs.getString("MEM_BIR"))
						.mem_zip(rs.getString("MEM_ZIP"))
						.mem_add1(rs.getString("MEM_ADD1"))
						.mem_add2(rs.getString("MEM_ADD2"))
						.mem_hometel(rs.getString("MEM_HOMETEL"))
						.mem_comtel(rs.getString("MEM_COMTEL"))
						.mem_hp(rs.getString("MEM_HP"))
						.mem_mail(rs.getString("MEM_MAIL"))
						.mem_job(rs.getString("MEM_JOB"))
						.mem_like(rs.getString("MEM_LIKE"))
						.mem_memorial(rs.getString("MEM_MEMORIAL"))
						.mem_memorialday(rs.getString("MEM_MEMORIALDAY"))
						.mem_mileage(rs.getInt("MEM_MILEAGE"))
						.mem_delete(rs.getString("MEM_DELETE"))
						.build();
			}
			return member;
		}catch(SQLException e) {
		//예외를 톰캣에게 넘김.
		//throw new RuntimeException(); 이렇게 쓰면 안됨(원본 예외를 전달해주기 위해) 
			throw new RuntimeException(e);
		}
	}

}
