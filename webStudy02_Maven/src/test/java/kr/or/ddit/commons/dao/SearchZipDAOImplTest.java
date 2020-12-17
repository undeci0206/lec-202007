package kr.or.ddit.commons.dao;

import static org.junit.Assert.*;

import java.util.List;
import org.junit.Test;
import kr.or.ddit.vo.ZipVO;

public class SearchZipDAOImplTest {

	private ISearchZipDAO dao = SearchZipDAOImpl.getInstance();
	
	@Test
	void testSelectZipList() {
		List<ZipVO> zipList = dao.selectZipList(null);
		//성공했다면 size 0이상. assert계열 메서드 사용 -> static import로 가져온 메서드
		assertNotEquals(0, zipList.size());
	}
}
