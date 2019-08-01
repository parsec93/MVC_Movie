package vo;

public class MemberBean {
	private int member_idx;
	private String member_name;
	private String member_id;
	private String member_password;
	private String member_gender;
	private String member_jumin;
	private String member_email;
	private String member_phone;
	
	public MemberBean() {}
	
	public MemberBean(int member_idx, String member_name, String member_id, String member_password,
			String member_gender, String member_jumin, String member_email, String member_phone) {
		super();
		this.member_idx = member_idx;
		this.member_name = member_name;
		this.member_id = member_id;
		this.member_password = member_password;
		this.member_gender = member_gender;
		this.member_jumin = member_jumin;
		this.member_email = member_email;
		this.member_phone = member_phone;
	}
	public int getMember_idx() {
		return member_idx;
	}
	public void setMember_idx(int member_idx) {
		this.member_idx = member_idx;
	}
	public String getMember_name() {
		return member_name;
	}
	public void setMember_name(String member_name) {
		this.member_name = member_name;
	}
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public String getMember_password() {
		return member_password;
	}
	public void setMember_password(String member_password) {
		this.member_password = member_password;
	}
	public String getMember_gender() {
		return member_gender;
	}
	public void setMember_gender(String member_gender) {
		this.member_gender = member_gender;
	}
	public String getMember_jumin() {
		return member_jumin;
	}
	public void setMember_jumin(String member_jumin) {
		this.member_jumin = member_jumin;
	}
	public String getMember_email() {
		return member_email;
	}
	public void setMember_email(String member_email) {
		this.member_email = member_email;
	}
	public String getMember_phone() {
		return member_phone;
	}
	public void setMember_phone(String member_phone) {
		this.member_phone = member_phone;
	}
	
}
