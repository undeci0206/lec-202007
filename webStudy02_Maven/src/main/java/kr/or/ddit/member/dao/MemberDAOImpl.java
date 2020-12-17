package kr.or.ddit.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.or.ddit.db.ConnectionFactory;
import kr.or.ddit.vo.MemberVO;

public class MemberDAOImpl implements IMemberDAO {    
	private MemberDAOImpl() { }
	private static MemberDAOImpl self;
	public static MemberDAOImpl getInstance() {
		if(self==null) self = new MemberDAOImpl();
		return self;
	}
	
	@Override
	public MemberVO selectMember(String mem_id) {
		StringBuffer sql = new StringBuffer();                             
		sql.append(" SELECT                                                 										");
		sql.append("     MEM_ID,    MEM_PASS,    MEM_NAME,                 												 ");
		sql.append("     MEM_REGNO1,    MEM_REGNO2,    TO_CHAR(MEM_BIR, 'YYYY-MM-DD') MEM_BIR,          				   ");
		sql.append("     MEM_ZIP,    MEM_ADD1,    MEM_ADD2,                														 ");
		sql.append("     MEM_HOMETEL,    MEM_COMTEL,    MEM_HP,            												 ");
		sql.append("     MEM_MAIL,    MEM_JOB,    MEM_LIKE,               											  ");
		sql.append("     MEM_MEMORIAL,    TO_CHAR(MEM_MEMORIALDAY, 'YYYY-MM-DD') MEM_MEMORIALDAY,    MEM_MILEAGE,  ");
		sql.append("     MEM_DELETE   													 ");
		sql.append(" FROM    MEMBER                                        											 ");
		sql.append(" WHERE MEM_ID = ? 																				");
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

	@Override
	public int insertMember(MemberVO member) {
		StringBuffer sql = new StringBuffer();
		sql.append(" INSERT INTO member (                                  ");
	    sql.append(" MEM_ID, MEM_PASS,  MEM_NAME,                          ");
	    sql.append(" MEM_REGNO1,    MEM_REGNO2,    MEM_BIR,                ");
	    sql.append(" MEM_ZIP,    MEM_ADD1,    MEM_ADD2,                    ");
	    sql.append(" MEM_HOMETEL,    MEM_COMTEL,    MEM_HP,                ");
	    sql.append(" MEM_MAIL,    MEM_JOB,    MEM_LIKE,                    ");
	    sql.append(" MEM_MEMORIAL,    MEM_MEMORIALDAY,    MEM_MILEAGE,     ");
	    sql.append(" MEM_DELETE                                            ");
		sql.append(" ) VALUES (                                            ");
		sql.append(" ?,    ?,    ?,                                           ");
	    sql.append(" ?,    ?,    TO_DATE(?, 'YYYY-MM-DD'),                                           ");
	    sql.append(" ?,    ?,    ?,                                          ");
	    sql.append(" ?,    ?,    ?,                                          ");
	    sql.append(" ?,    ?,    ?,                                          ");
	    sql.append(" ?,    TO_DATE(?, 'YYYY-MM-DD'),    ?,                                           ");
	    sql.append(" ?                                                     ");

	    int successMem = 0;
	    
	    try(
				Connection conn = ConnectionFactory.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql.toString());	
			){
				int i = 1;
				stmt.setString(i++, member.getMem_id());
				stmt.setString(i++, member.getMem_pass());
				stmt.setString(i++, member.getMem_name());
				stmt.setString(i++, member.getMem_regno1());
				stmt.setString(i++, member.getMem_regno2());
				stmt.setString(i++, member.getMem_bir());
				stmt.setString(i++, member.getMem_zip());
				stmt.setString(i++, member.getMem_add1());
				stmt.setString(i++, member.getMem_add2());
				stmt.setString(i++, member.getMem_hometel());
				stmt.setString(i++, member.getMem_comtel());
				stmt.setString(i++, member.getMem_hp());
				stmt.setString(i++, member.getMem_mail());
				stmt.setString(i++, member.getMem_job());
				stmt.setString(i++, member.getMem_like());
				stmt.setString(i++, member.getMem_memorial());
				stmt.setString(i++, member.getMem_memorialday());
				stmt.setInt(i++, member.getMem_mileage());
				stmt.setString(i++, member.getMem_delete());
				
				return stmt.executeUpdate();
			}catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}

	@Override
	public List<MemberVO> selectMemberList() {
		List<MemberVO> memList = new ArrayList<MemberVO>();
		
		StringBuffer sql = new StringBuffer();                             
		sql.append(" SELECT                                                 										");
		sql.append("     MEM_ID,    MEM_PASS,    MEM_NAME,                 												 ");
		sql.append("     MEM_REGNO1,    MEM_REGNO2,    TO_CHAR(MEM_BIR, 'YYYY-MM-DD') MEM_BIR,          				   ");
		sql.append("     MEM_ZIP,    MEM_ADD1,    MEM_ADD2,                														 ");
		sql.append("     MEM_HOMETEL,    MEM_COMTEL,    MEM_HP,            												 ");
		sql.append("     MEM_MAIL,    MEM_JOB,    MEM_LIKE,               											  ");
		sql.append("     MEM_MEMORIAL,    TO_CHAR(MEM_MEMORIALDAY, 'YYYY-MM-DD') MEM_MEMORIALDAY,    MEM_MILEAGE,  ");
		sql.append("     MEM_DELETE   													 ");
		sql.append(" FROM    MEMBER                                        											 ");
		
		try(
			Connection conn = ConnectionFactory.getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql.toString());
		){
			MemberVO member = null;
			ResultSet rs = stmt.executeQuery();
			if(rs.next()){ 
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
						memList.add(member);
			}
			return memList;       
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public int updateMember(MemberVO member) {
		StringBuffer sql = new StringBuffer();    
		sql.append(" UPDATE member              ");
	    sql.append(" SET                        ");
	    sql.append(" mem_pass = ?               ");
	    sql.append(" AND   mem_name =  ?        ");
	    sql.append(" AND   mem_regno1 = ?       ");
	    sql.append(" AND   mem_regno2 = ?       ");
	    sql.append(" AND   mem_bir = ?          ");
	    sql.append(" AND   mem_zip = ?          ");
	    sql.append(" AND   mem_add1 = ?         ");
	    sql.append(" AND   mem_add2 = ?         ");
	    sql.append(" AND   mem_hometel = ?      ");
	    sql.append(" AND   mem_comtel = ?       ");
	    sql.append(" AND   mem_hp = ?           ");
	    sql.append(" AND   mem_mail = ?         ");
	    sql.append(" AND   mem_job = ?          ");
	    sql.append(" AND   mem_like = ?         ");
	    sql.append(" AND   mem_memorial = ?     ");
	    sql.append(" AND   mem_memorialday = ?  ");
	    sql.append(" WHERE mem_id = ?           ");     
		             
	    int successMem = 0;
	    
	    try(
			Connection conn = ConnectionFactory.getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql.toString());
		){
	    	stmt.setString(1, member.getMem_pass());
	    	stmt.setString(2, member.getMem_name());
	    	stmt.setString(3, member.getMem_regno1());
	    	stmt.setString(4, member.getMem_regno2());
	    	stmt.setString(5, member.getMem_bir());
	    	stmt.setString(6, member.getMem_zip());
	    	stmt.setString(7, member.getMem_add1());
	    	stmt.setString(8, member.getMem_add2());
	    	stmt.setString(9, member.getMem_hometel());
	    	stmt.setString(10, member.getMem_comtel());
	    	stmt.setString(11, member.getMem_hp());
	    	stmt.setString(12, member.getMem_mail());
	    	stmt.setString(13, member.getMem_job());
	    	stmt.setString(14, member.getMem_like());
	    	stmt.setString(15, member.getMem_memorial());
	    	stmt.setString(16, member.getMem_memorialday());
	    	stmt.setString(17, member.getMem_id());
	    	successMem = stmt.executeUpdate(sql.toString());
	    
	    	return successMem;
			
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
		
	}

	@Override
	public int deleteMember(String mem_id) {
		StringBuffer sql = new StringBuffer(); 
		sql.append(" DELETE FROM member  ");
		sql.append(" WHERE               ");
		sql.append(" mem_id = ?          ");
		
		int successMem = 0;
		
		try(
			Connection conn = ConnectionFactory.getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql.toString());
		){
	    	stmt.setString(1, mem_id);
			
	    	successMem = stmt.executeUpdate(sql.toString());
	    
	    	return successMem;
	    }catch(SQLException e) {
			throw new RuntimeException(e);
		}	
		
	}
}
