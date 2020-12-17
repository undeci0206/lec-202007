package kr.or.ddit.commons.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.db.ConnectionFactory;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.ZipVO;

public class SearchZipDAOImpl implements ISearchZipDAO {
	private SearchZipDAOImpl() { }
	private static SearchZipDAOImpl self;
	public static SearchZipDAOImpl getInstance() {
		if(self==null) self = new SearchZipDAOImpl();
		return self;
	}

	@Override
	public List<ZipVO> selectZipList(String keyword) {
		List<ZipVO> zipList = new ArrayList<>();
		StringBuffer sql = new StringBuffer();                   
		sql.append(" SELECT	ZIPCODE,    SIDO,    GUGUN,			");
		sql.append(" 		DONG,    RI,    BLDG,               ");
		sql.append(" 		BUNJI,    SEQ                       ");
		sql.append(" FROM    TB_ZIP                             ");
		if(StringUtils.isNotBlank(keyword)) {
			sql.append(" WHERE INSTR(SIDO, ?) > 0 			");
			sql.append(" 		OR INSTR(GUGUN, ?) > 0		");
			sql.append(" 		OR INSTR(DONG, ?) > 0       ");
			sql.append(" 		OR INSTR(RI, ?) > 0         ");
		}
		try(
			Connection conn = ConnectionFactory.getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql.toString());	
		){
			if(StringUtils.isNotBlank(keyword)) {
				stmt.setString(1, keyword);
				stmt.setString(2, keyword);
				stmt.setString(3, keyword);
				stmt.setString(4, keyword);
			}
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				ZipVO zipVO = new ZipVO();
				zipList.add(zipVO);
				zipVO.setZipcode(rs.getString("ZIPCODE"));
				zipVO.setSido(rs.getString("SIDO"));
				zipVO.setGugun(rs.getString("GUGUN"));
				zipVO.setDong(rs.getString("DONG"));
				zipVO.setRi(rs.getString("RI"));
				zipVO.setBldg(rs.getString("BLDG"));
				zipVO.setBunji(rs.getString("BUNJI"));
				zipVO.setSeq(rs.getInt("SEQ"));
			}
			return zipList;
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
