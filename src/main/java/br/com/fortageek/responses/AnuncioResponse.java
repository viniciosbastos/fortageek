package br.com.fortageek.responses;

import java.util.List;

import br.com.fortageek.models.Anuncio;

public class AnuncioResponse {

	private List anuncios;

	public AnuncioResponse() {
		super();
	}

	public AnuncioResponse(List<Anuncio> anuncios) {
		super();
		this.anuncios = anuncios;
	}

	public List getAnuncios() {
		return anuncios;
	}

	public void setAnuncios(List anuncios) {
		this.anuncios = anuncios;
	}

	
	
	
	
	
}
