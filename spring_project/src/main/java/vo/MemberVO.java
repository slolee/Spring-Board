package vo;

public class MemberVO {
	private String id;
	private String nickname;
	private String pw;
	private String email;
	private String phone;
	
	public MemberVO() {
		
	}

	public MemberVO(String id, String nickname, String pw, String email, String phone) {
		this.id = id;
		this.nickname = nickname;
		this.pw = pw;
		this.email = email;
		this.phone = phone;
	}

	@Override
	public String toString() {
		return "memberVO [id=" + id + ", nickname=" + nickname + ", pw=" + pw + ", email=" + email + ", phone=" + phone
				+ "]";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email1, String email2) {
		this.email = email1 + email2;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone1, String phone2, String phone3) {
		this.phone = phone1 + "-" + phone2 + "-" + phone3;
	}
}
