package kr.or.ddit.commons.service;

import java.util.List;

import kr.or.ddit.vo.ZipVO;

public interface ISearchZipService {
	public List<ZipVO> retrieveZipList(String keyword);
}
