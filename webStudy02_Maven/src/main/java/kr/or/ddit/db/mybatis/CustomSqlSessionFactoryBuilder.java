package kr.or.ddit.db.mybatis;

import java.io.IOException;
import java.io.Reader;

import javax.management.RuntimeErrorException;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**
 * singleton으로 오류 SqlSessionFactory 빌드
 * @author PC-NEW01
 */
public class CustomSqlSessionFactoryBuilder {
	private static SqlSessionFactory sqlSessionFactory;
	static {
		String resource = "kr/or/ddit/db/mybatis/Configurstion.xml";
		try(
				Reader reader = Resources.getResourceAsReader(resource);
		){
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
		}catch(IOException e) {
			//서버 stop. 예외를 unchecked로 바꿔서 서버에 넘겨줌
			throw new RuntimeException(e);
		}
	}
	
	public static SqlSessionFactory getSqlSessionFactory() {
		return sqlSessionFactory;
	}
	
}
