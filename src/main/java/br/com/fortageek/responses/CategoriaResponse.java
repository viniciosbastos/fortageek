package br.com.fortageek.responses;

import java.util.List;

import br.com.fortageek.models.Categoria;

public class CategoriaResponse {
	
	private List categorias;

	public CategoriaResponse() {
		super();
	}

	public CategoriaResponse(List<Categoria> categorias) {
		super();
		this.categorias = categorias;
	}

	public List getCategorias() {
		return categorias;
	}

	public void setCategorias(List categorias) {
		this.categorias = categorias;
	}
	
	
	
}
