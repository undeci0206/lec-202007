package kr.or.ddit.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.management.ImmutableDescriptor;
import javax.naming.spi.DirStateFactory.Result;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.db.ConnectionFactory;
import kr.or.ddit.db.mybatis.CustomSqlSessionFactoryBuilder;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PagingVO;

public class MemberDAOImpl implements IMemberDAO {    
	private MemberDAOImpl() { }
	private static MemberDAOImpl self;
	public static MemberDAOImpl getInstance() {
		if(self==null) self = new MemberDAOImpl();
		return self;
	}
	
	private SqlSessionFactory sqlSessionFactory = 
			CustomSqlSessionFactoryBuilder.getSqlSessionFactory();
	
	@Override
	public int insertMember(MemberVO member) {
		try(
				SqlSession sqlSession = sqlSessionFactory.openSession();
		){
//				return sqlSession.selectOne("kr.or.ddit.member.dao.IMemberDAO.selectMember", mem_id);
			
			// mapper proxy : 인터페이스의 qualified로 등록되어있는 namespace 찾고, 가짜 구현제 만듦 
			//오타 발생률 낮음
			IMemberDAO mapper = sqlSession.getMapper(IMemberDAO.class); 
			int rowcnt =  mapper.insertMember(member);
			sqlSession.commit();
			return rowcnt;
		}
	}
	@Override
	public int selectMemberCount(PagingVO<MemberVO> pagingVO) {
		try(
				SqlSession sqlSession = sqlSessionFactory.openSession();
		){
			IMemberDAO mapper = sqlSession.getMapper(IMemberDAO.class);
			return mapper.selectMemberCount(pagingVO);
		}
	}
	@Override
	public List<MemberVO> selectMemberList(PagingVO pagingVO) {
		try(
			SqlSession sqlSession = sqlSessionFactory.openSession();
		){		
			IMemberDAO mapper = sqlSession.getMapper(IMemberDAO.class);
			return mapper.selectMemberList(pagingVO);
		}
	}
	@Override
	public MemberVO selectMember(String mem_id) {
		try(
				SqlSession sqlSession = sqlSessionFactory.openSession();
		){
//			return sqlSession.selectOne("kr.or.ddit.member.dao.IMemberDAO.selectMember", mem_id);
			
			//** mapper proxy : 인터페이스의 qualified로 등록되어있는 namespace 찾고, 가짜 구현제 만듦 
			//오타 발생률 낮음
			IMemberDAO mapper = sqlSession.getMapper(IMemberDAO.class); 
			return mapper.selectMember(mem_id);
		}
	}
	@Override
	public int updateMember(MemberVO member) {
		try(
				SqlSession sqlSession = sqlSessionFactory.openSession();
		){
//				return sqlSession.selectOne("kr.or.ddit.member.dao.IMemberDAO.selectMember", mem_id);
			
			// mapper proxy : 인터페이스의 qualified로 등록되어있는 namespace 찾고, 가짜 구현제 만듦 
			//오타 발생률 낮음
			IMemberDAO mapper = sqlSession.getMapper(IMemberDAO.class); 
			int rowcnt =  mapper.updateMember(member);
			sqlSession.commit();
			return rowcnt;
		}
	}
	
	@Override
	public int deleteMember(String mem_id) {
		try(
				SqlSession sqlSession = sqlSessionFactory.openSession();
		){
//				return sqlSession.selectOne("kr.or.ddit.member.dao.IMemberDAO.selectMember", mem_id);
			
			// mapper proxy : 인터페이스의 qualified로 등록되어있는 namespace 찾고, 가짜 구현제 만듦 
			//오타 발생률 낮음
			IMemberDAO mapper = sqlSession.getMapper(IMemberDAO.class); 
			int rowcnt =  mapper.deleteMember(mem_id);
			sqlSession.commit();
			return rowcnt;
		}
	}
	
	
	
}
