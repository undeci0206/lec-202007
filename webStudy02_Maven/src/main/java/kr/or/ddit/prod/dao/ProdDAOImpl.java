package kr.or.ddit.prod.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.db.mybatis.CustomSqlSessionFactoryBuilder;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ProdVO;

public class ProdDAOImpl implements IProdDAO {
	private ProdDAOImpl() { }
	private static ProdDAOImpl self;
	public static ProdDAOImpl getInstance() {
		if(self==null) self = new ProdDAOImpl();
		return self;
	}
	
	private SqlSessionFactory sqlSessionFactory = 
				CustomSqlSessionFactoryBuilder.getSqlSessionFactory();

	@Override
	public int insertProd(ProdVO prod) {
		try(
			SqlSession sqlSession = sqlSessionFactory.openSession(true);	
		){
			return sqlSession.insert("kr.or.ddit.prod.dao.IProdDAO.insertProd", prod);
		}
	}

	@Override
	public int selectProdCount(PagingVO<ProdVO> pagingVO) {
		try(
			SqlSession sqlSession = sqlSessionFactory.openSession();	
		){
			IProdDAO mapper = sqlSession.getMapper(IProdDAO.class);
			return mapper.selectProdCount(pagingVO);
		}
	}

	@Override
	public List<ProdVO> selectProdList(PagingVO<ProdVO> pagingVO) {
		try(
			SqlSession sqlSession = sqlSessionFactory.openSession();	
		){
			IProdDAO mapper = sqlSession.getMapper(IProdDAO.class);
			return mapper.selectProdList(pagingVO);
		}
	}

	@Override
	public ProdVO selectProd(String prod_id) {
		try(
			SqlSession sqlSession = sqlSessionFactory.openSession();	
		){
			IProdDAO mapper = sqlSession.getMapper(IProdDAO.class);
			return mapper.selectProd(prod_id);
		}
	}

	@Override
	public int updateProd(ProdVO prod) {
		try(
			SqlSession sqlSession = sqlSessionFactory.openSession(true);	
		){
			return sqlSession.update("kr.or.ddit.prod.dao.IProdDAO.updateProd", prod);
		}
	}

}
