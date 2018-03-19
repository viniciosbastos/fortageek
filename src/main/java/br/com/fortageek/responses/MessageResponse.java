package br.com.fortageek.responses;

public class MessageResponse {
	private String auth;

	public MessageResponse(String auth) {
		super();
		this.auth = auth;
	}

	public String getAuth() {
		return auth;
	}

	public void setAuth(String auth) {
		this.auth = auth;
	}
		
}
