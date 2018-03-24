package br.com.fortageek.responses;

import java.util.List;

import br.com.fortageek.models.Cidade;

public class CidadeResponse {
	
	private List cidades;

	public CidadeResponse() {
		super();
	}

	public CidadeResponse(List<Cidade> cidades) {
		super();
		this.cidades = cidades;
	}

	public List getCidades() {
		return cidades;
	}

	public void setCidades(List cidades) {
		this.cidades = cidades;
	}
	
	
}
