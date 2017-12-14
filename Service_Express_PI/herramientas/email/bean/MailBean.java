package email.bean;

public class MailBean {

	private String correoEmisor;
	private String correoReceptor;
	private String title;
	private String message;
	private String password;
	
	
	
	public MailBean(String correoEmisor, String correoReceptor, String title, String message, String password) {
		super();
		this.correoEmisor = correoEmisor;
		this.correoReceptor = correoReceptor;
		this.title = title;
		this.message = message;
		this.password = password;
	}
	
	public String getCorreoEmisor() {
		return correoEmisor;
	}
	public void setCorreoEmisor(String correoEmisor) {
		this.correoEmisor = correoEmisor;
	}
	public String getCorreoReceptor() {
		return correoReceptor;
	}
	public void setCorreoReceptor(String correoReceptor) {
		this.correoReceptor = correoReceptor;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
