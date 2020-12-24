package kr.or.ddit.dbprop.service;

import java.util.List;

import kr.or.ddit.vo.DataBasePropertyVO;

public interface IDataBasePropertyService {
	//메서드의 시그니처만, 콘크리트 메서드가 들어가는 것 아님!
	
	//어떻게 만들어야한다는 결정하지 않음
	public List<DataBasePropertyVO> retrieveDataBaseProperty(DataBasePropertyVO paramVO);
}