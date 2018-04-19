package br.com.fortageek.responses;

public class MessageResponse {
	private String message;

	public MessageResponse(String message) {
		super();
		this.message = message;
	}

	public String getAuth() {
		return message;
	}

	public void setAuth(String message) {
		this.message = message;
	}
		
}
