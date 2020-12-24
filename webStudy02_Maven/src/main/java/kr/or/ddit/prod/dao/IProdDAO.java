package kr.or.ddit.prod.dao;

import java.util.List;

import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ProdVO;

/**
 * 상품관리 Persistance Layer
 */
public interface IProdDAO {
	public int insertProd(ProdVO prod);
	public int selectProdCount(PagingVO<ProdVO> pagingVO);
	public List<ProdVO> selectProdList(PagingVO<ProdVO> pagingVO);
	public ProdVO selectProd(String prod_id);
	public int updateProd(ProdVO prod);
}
