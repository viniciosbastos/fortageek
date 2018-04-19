package br.com.fortageek.responses;

import br.com.fortageek.models.Usuario;

public class LoginSucessoResponse {
	private String auth;
	
	private Usuario usuario;
	
	public LoginSucessoResponse() {
		super();
	}

	public LoginSucessoResponse(String auth, Usuario usuario) {
		super();
		this.auth = auth;
		this.usuario = usuario;
	}

	public String getAuth() {
		return auth;
	}

	public void setAuth(String auth) {
		this.auth = auth;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}
