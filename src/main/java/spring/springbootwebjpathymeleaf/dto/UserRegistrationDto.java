package spring.springbootwebjpathymeleaf.dto;

public class UserRegistrationDto {
	private String FIO;
	private String post;
	private String email;
	private String password;
	
	public UserRegistrationDto(){
		
	}
	
	public UserRegistrationDto(String FIO, String post, String email, String password) {
		super();
		this.FIO = FIO;
		this.post = post;
		this.email = email;
		this.password = password;
	}
	
	public String getFIO() {
		return FIO;
	}
	public void setFIO(String FIO) {
		this.FIO = FIO;
	}
	public String getPost() {
		return post;
	}
	public void setPost(String post) {
		this.post = post;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
