package br.com.fortageek.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "proposta")
public class Proposta {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "status")
	private Integer status;
	
	@Column(name = "id_anuncio")
	private Integer id_anuncio;
	
	@Column(name = "id_item")
	private Integer id_item;
	
	@Column(name = "id_usuario")
	private Integer id_usuario;

	public Proposta() {
		super();
	}

	public Proposta(Integer id, Integer status, Integer id_anuncio, Integer id_item, Integer id_usuario) {
		super();
		this.id = id;
		this.status = status;
		this.id_anuncio = id_anuncio;
		this.id_item = id_item;
		this.id_usuario = id_usuario;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getId_anuncio() {
		return id_anuncio;
	}

	public void setId_anuncio(Integer id_anuncio) {
		this.id_anuncio = id_anuncio;
	}

	public Integer getId_item() {
		return id_item;
	}

	public void setId_item(Integer id_item) {
		this.id_item = id_item;
	}

	public Integer getId_usuario() {
		return id_usuario;
	}

	public void setId_usuario(Integer id_usuario) {
		this.id_usuario = id_usuario;
	}
	
	
	
	
}
