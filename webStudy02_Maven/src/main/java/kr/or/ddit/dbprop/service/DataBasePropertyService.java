package kr.or.ddit.dbprop.service;

import java.util.List;

import kr.or.ddit.dbprop.dao.DatabasePropertyDAO;
import kr.or.ddit.vo.DataBasePropertyVO;

//business logic만들기
public class DataBasePropertyService implements IDataBasePropertyService{
	//1. 의존해야 하는 객체를 받아와야 함 -> 의존관계! 결합력 발생 (**직접 의존관계 생성하는 건 결합력이 높은 구조이다.)
	DatabasePropertyDAO dao = new DatabasePropertyDAO();

	@Override
	public List<DataBasePropertyVO> retrieveDataBaseProperty(DataBasePropertyVO paramVO) {
		List<DataBasePropertyVO> list = dao.selectDBProperties(paramVO);
		
		for(DataBasePropertyVO tmp : list) {
			String tName = Thread.currentThread().getName();
			//thread name을 붙여서 가공해달라는 요청에 따라
			//raw data가 content로 가공되었다!
			tmp.setProperty_value(String.format("%s[%s]", tmp.getProperty_value(), tName));
			System.out.println("메일 발송");
		}
		
		return list;
	}
	
	
}
