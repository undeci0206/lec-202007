package kr.or.ddit.prod.dao;

import java.util.List;
import java.util.Map;
import kr.or.ddit.vo.BuyerVO;

/**
 * option UI 생성용 persistence Layer
 */
public interface IOthersDAO {
	//VO 여러개 만들지 않고 데이터 가져올 수 있음(확장성 좋음)
	public List<Map<String, Object>> selectLprodList();
	public List<BuyerVO> selectBuyerList();
}
