package br.com.fortageek.models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity(name = "anuncio")
public class Anuncio {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "status")
	private Integer status;
	
	@ManyToOne
	@JoinColumn(name = "id_usuario")
	private Usuario usuario;
	
	@ManyToOne
	@JoinColumn(name = "id_item")
	private Item item;
	
	@JsonIgnore
	@OneToMany(mappedBy = "anuncio", fetch = FetchType.LAZY)
	private Set<Proposta> propostas = new HashSet<Proposta>();

	@JsonIgnore
	@OneToMany(mappedBy = "anuncio", fetch = FetchType.LAZY)
	private Set<Comentario> comentarios = new HashSet<Comentario>();

	public Anuncio() {
		super();
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

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public Set<Proposta> getPropostas() {
		return propostas;
	}

	public void setPropostas(Set<Proposta> propostas) {
		this.propostas = propostas;
	}

	@Transient
	public Integer getNumeroPropostas() {
		return this.propostas.size();
	}
	
	@Transient
	public Integer getNumeroComentarios() {
		return this.comentarios.size();
	}
}
