package dto;

import java.sql.Date;

public class PayDTO {
	private long p_account;
	private int p_payment;
	private String p_detail;//카드인지 , 이체인지  구분하는 컬럼명 : 카드 -c, 이체-p
	private String p_memo;//이체-입출금자 , 카드- 내역
	private String p_category;
	private Date aco_date;
	
	public PayDTO() {
		super();
		// TODO Auto-generated constructor stub
	}


	public PayDTO(long p_account, int p_payment, String p_detail, String p_memo, String p_category,Date aco_date) {
		super();
		this.p_account = p_account;
		this.p_payment = p_payment;
		this.p_detail = p_detail;
		this.p_memo = p_memo;
		this.p_category = p_category;
		this.aco_date=aco_date;
	}


	public Date getAco_date() {
		return aco_date;
	}


	public void setAco_date(Date aco_date) {
		this.aco_date = aco_date;
	}


	public long getP_account() {
		return p_account;
	}
	public void setP_account(long p_account) {
		this.p_account = p_account;
	}
	public int getP_payment() {
		return p_payment;
	}
	public void setP_payment(int p_payment) {
		this.p_payment = p_payment;
	}
	public String getP_detail() {
		return p_detail;
	}
	public void setP_detail(String p_detail) {
		this.p_detail = p_detail;
	}
	public String getP_memo() {
		return p_memo;
	}
	public void setP_memo(String p_memo) {
		this.p_memo = p_memo;
	}


	public String getP_category() {
		return p_category;
	}


	public void setP_category(String p_category) {
		this.p_category = p_category;
	}
	

}
