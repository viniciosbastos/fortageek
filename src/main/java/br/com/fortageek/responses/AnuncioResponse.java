package br.com.fortageek.responses;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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

	public AnuncioResponse(Set<Anuncio> anuncios) {
		super();
		this.anuncios = new ArrayList<>(anuncios);
	}

	public List getAnuncios() {
		return anuncios;
	}

	public void setAnuncios(List anuncios) {
		this.anuncios = anuncios;
	}

	
	
	
	
	
}
