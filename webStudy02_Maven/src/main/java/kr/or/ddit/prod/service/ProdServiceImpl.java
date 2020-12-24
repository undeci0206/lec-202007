package kr.or.ddit.prod.service;

import java.util.List;

import kr.or.ddit.CustomException;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.prod.dao.IProdDAO;
import kr.or.ddit.prod.dao.ProdDAOImpl;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ProdVO;

public class ProdServiceImpl implements IProdService {
	private ProdServiceImpl() { }
	private static ProdServiceImpl self;
	public static ProdServiceImpl getInstance() {
		if(self==null) self = new ProdServiceImpl();
		return self;
	}
	
	private IProdDAO dao = ProdDAOImpl.getInstance();

	@Override
	public ServiceResult createProd(ProdVO prod) {
		int rowcnt = dao.insertProd(prod);
		ServiceResult result = ServiceResult.FAILED;
		if(rowcnt>0) {
			result = ServiceResult.OK;
		}
		return result;
	}

	@Override
	public int retrieveProdCount(PagingVO<ProdVO> pagingVO) {
		return dao.selectProdCount(pagingVO);
	}

	@Override
	public List<ProdVO> retrieveProdList(PagingVO<ProdVO> pagingVO) {
		return dao.selectProdList(pagingVO);
	}

	@Override
	public ProdVO retrieveProd(String prod_id) {
		ProdVO prod = dao.selectProd(prod_id);
		if(prod == null) throw new CustomException(prod_id+"에 해당하는 상품이 없음.");
		return prod;
	}

	@Override
	public ServiceResult modifyProd(ProdVO prod) {
		int rowcnt = dao.updateProd(prod);
		ServiceResult result = ServiceResult.FAILED;
		if(rowcnt>0) {
			result = ServiceResult.OK;
		}
		return result;
	}

}
