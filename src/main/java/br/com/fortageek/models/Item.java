package br.com.fortageek.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "item")
public class Item {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "descricao")
	private String descricao;
	
	@Column(name = "nome")
	private String nome;
	
	@Column(name = "id_categoria")
	private Integer id_categoria;
	
	@Column(name = "foto")
	private Integer foto;

	public Item() {
		super();
	}

	public Item(Integer id, String descricao, String nome, Integer id_categoria, Integer foto) {
		super();
		this.id = id;
		this.descricao = descricao;
		this.nome = nome;
		this.id_categoria = id_categoria;
		this.foto = foto;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getId_categoria() {
		return id_categoria;
	}

	public void setId_categoria(Integer id_categoria) {
		this.id_categoria = id_categoria;
	}

	public Integer getFoto() {
		return foto;
	}

	public void setFoto(Integer foto) {
		this.foto = foto;
	}
	
	
}
