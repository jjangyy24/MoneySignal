package dbmanager;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//���� �ڹٸ� �������ֱ� ���ؼ� ����� Ŭ����
public class JdbcConnect {
	
	//drivermanager ��ü�� �������� �Լ�
	//static�� ȣ���� ������ ���� �����ö����� ��ü�� ���� �ʿ䰡 ���� ������
	public static Connection getConnection() {
		Connection conn=null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn=DriverManager.getConnection(
					"jdbc:oracle:thin:@192.168.113.61:1521:XE","edu","1234");
			//192.168.113.61 : �츮��� �ִ� ip�ּ�
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn; // ������� conn��ü�� ��ȯ��
	}
	
	//���Ῡ�� Ȯ�� �Լ�
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
	
	//�����ͺ��̽� ������������ ����� ��� �ﰢ�ﰢ �������ִ� �Լ�
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
	
	//�����·� ���ư��� ���� �Լ�
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
