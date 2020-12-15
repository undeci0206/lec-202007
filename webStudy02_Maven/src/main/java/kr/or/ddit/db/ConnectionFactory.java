package kr.or.ddit.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;

import oracle.jdbc.pool.OracleDataSource;

/**
 * Factory Object[Method] Pattern
 *
 */
public class ConnectionFactory {
	private static String url;
	private static String user;
	private static String password;
	private static DataSource dataSource;
	
	static {
		//bundle통해 properties파일에서 정보 꺼내기
		//BaseName : 1.classPath에서 시작. 2.확장자 정보 포함되지 않음 , 3.로케일 정보 포함되지 않음. 4.경로구조 . 이 되어야 함
		ResourceBundle bundle = ResourceBundle.getBundle("kr.or.ddit.db.dbInfo"); 
		String driverClassName = bundle.getString("driverClassName");
		url = bundle.getString("url");
		user = bundle.getString("user");
		password = bundle.getString("password");
		
		//dataSource를 통해 pulling 정책 결정. 어떻게 하면 쉽게 데이터를 설정할 수 있을까..
		int initialSize = Integer.parseInt(bundle.getString("initialSize"));
		long maxWait = Long.parseLong(bundle.getString("maxWait"));
		int maxActive = Integer.parseInt(bundle.getString("maxActive"));
		
		//BasicDataSource에서는 자체적으로 RuntimeException을 발생시키므로 예외처리구문 주석으로 묶음
//		try {
			BasicDataSource ds = new BasicDataSource();
			dataSource = ds;
			ds.setDriverClassName(driverClassName);
			ds.setUrl(url);
			ds.setUsername(user);
			ds.setPassword(password);
			ds.setInitialSize(initialSize);
			ds.setMaxWaitMillis(maxWait);
			ds.setMaxTotal(maxActive);
//		}catch (SQLException e) {
//			e.printStackTrace();
//		}
//		try {
//			OracleDataSource ds = new OracleDataSource();
//			dataSource = ds;
//			ds.setURL(url);
//			ds.setUser(user);
//			ds.setPassword(password);
//		}catch(SQLException e){
//			e.printStackTrace();
//		}
//		try {
//			Class.forName(driverClassName);
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		}
	}
	public static Connection getConnection() throws SQLException { //어떤 DB일지라도 코드를 수정하지 않게 하기 위해. 리턴 타입을 인터페이스로.(특정 코드 종속X)
		//드라이버를 따로 로딩하는 방식
		//Connection conn = DriverManager.getConnection(url, user, password);
		Connection conn = dataSource.getConnection();
		return conn;
	}
}
