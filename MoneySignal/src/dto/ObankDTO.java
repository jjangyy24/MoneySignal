package dto;

public class ObankDTO {
 private Long O_account;
 private String O_bank;
 private String O_name;
 
public ObankDTO() {
	super();
}
public ObankDTO(Long o_account, String o_bank, String o_name) {
	super();
	O_account = o_account;
	O_bank = o_bank;
	O_name = o_name;
}
public Long getO_account() {
	return O_account;
}
public void setO_account(Long o_account) {
	O_account = o_account;
}
public String getO_bank() {
	return O_bank;
}
public void setO_bank(String o_bank) {
	O_bank = o_bank;
}
public String getO_name() {
	return O_name;
}
public void setO_name(String o_name) {
	O_name = o_name;
}

}
