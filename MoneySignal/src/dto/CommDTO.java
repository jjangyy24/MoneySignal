package dto;

public class CommDTO {
	private Long c_account;//계좌번호
	private int c_limit;// 저축 한도
	private String cf_user;//개설자 id
	private String cs_user;//참가자 id
	private String c_pwd;//
	private float c_save;// 저축비율
	
	
	public CommDTO() {
		super();
	}
	public CommDTO(Long c_account, int c_limit, String cf_user, String cs_user, String c_pwd, float c_save) {
		super();
		this.c_account = c_account;
		this.c_limit = c_limit;
		this.cf_user = cf_user;
		this.cs_user = cs_user;
		this.c_pwd = c_pwd;
		this.c_save = c_save;
	}
	public Long getC_account() {
		return c_account;
	}
	public void setC_account(Long c_account) {
		this.c_account = c_account;
	}
	public int getC_limit() {
		return c_limit;
	}
	public void setC_limit(int c_limit) {
		this.c_limit = c_limit;
	}
	public String getCf_user() {
		return cf_user;
	}
	public void setCf_user(String cf_user) {
		this.cf_user = cf_user;
	}
	public String getCs_user() {
		return cs_user;
	}
	public void setCs_user(String cs_user) {
		this.cs_user = cs_user;
	}
	public String getC_pwd() {
		return c_pwd;
	}
	public void setC_pwd(String c_pwd) {
		this.c_pwd = c_pwd;
	}
	public float getC_save() {
		return c_save;
	}
	public void setC_save(float c_save) {
		this.c_save = c_save;
	}


	
}
