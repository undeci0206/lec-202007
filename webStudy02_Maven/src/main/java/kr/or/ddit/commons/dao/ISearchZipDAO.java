package kr.or.ddit.commons.dao;

import java.util.List;

import kr.or.ddit.vo.ZipVO;

public interface ISearchZipDAO {
	public List<ZipVO> selectZipList(String keyword);
}
