package dto;

public class UserDTO {
	
	//컬럼 변수 선언
	private String user_id;
	private String u_pwd;
	private String u_name;
	private long u_account;
	
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getU_pwd() {
		return u_pwd;
	}
	public void setU_pwd(String u_pwd) {
		this.u_pwd = u_pwd;
	}
	public String getU_name() {
		return u_name;
	}
	public void setU_name(String u_name) {
		this.u_name = u_name;
	}
	public long getU_account() {
		return u_account; 
	}
	public void setU_account(long u_account) {
		this.u_account = u_account;
	}
	
	
	
}
