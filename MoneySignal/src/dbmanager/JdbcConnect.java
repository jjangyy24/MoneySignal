package dbmanager;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//디비와 자바를 연결해주기 위해서 만드는 클래스
public class JdbcConnect {
	
	//drivermanager 객체를 가져오는 함수
	//static로 호출한 이유는 굳이 가져올때마다 객체를 만들 필요가 없기 때문에
	public static Connection getConnection() {
		Connection conn=null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn=DriverManager.getConnection(
					"jdbc:oracle:thin:@192.168.113.61:1521:XE","edu","1234");
			//192.168.113.61 : 우리디비 있는 ip주소
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn; // 만들어진 conn객체를 반환함
	}
	
	//연결여부 확인 함수
	public static boolean isConnection(Connection conn) {
		boolean valid=true;
		
		try {
			if(conn==null || conn.isClosed()) {
				valid=false;
			}
		} catch (SQLException e) {
			valid=false;
			e.printStackTrace();
		}
		return valid;
	}
	
	//데이터베이스 여러군데에서 사용할 경우 즉각즉각 갱신해주는 함수
	public static void commit(Connection conn) {
		if(isConnection(conn)) {
			try {
				conn.commit();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	//전상태로 돌아가기 위한 함수
	public static void rollback(Connection conn) {
		if(isConnection(conn)) {
			try {
				conn.rollback();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static void close(Connection conn) {
		if(isConnection(conn)) {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static void close(Statement stmt) {
		try {
			if(stmt!=null) 
				stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void close(ResultSet rs) {
		try {
			if(rs!=null)
				rs.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
}
