package br.com.fortageek.responses;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import br.com.fortageek.models.Comentario;

public class ComentarioResponse {

	private List comentarios;

	public ComentarioResponse() {
		super();
	}

	public ComentarioResponse(List<Comentario> comentarios) {
		super();
		this.comentarios = comentarios;
	}

	public ComentarioResponse(Set<Comentario> comentarios) {
		super();
		this.comentarios = new ArrayList<>(comentarios);
	}

	public List getComentarios() {
		return comentarios;
	}

	public void setComentarios(List comentarios) {
		this.comentarios = comentarios;
	};
	
	
}
