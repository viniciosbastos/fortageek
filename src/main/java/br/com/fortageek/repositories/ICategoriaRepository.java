package br.com.fortageek.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fortageek.models.Categoria;


public interface ICategoriaRepository extends JpaRepository<Categoria, Integer> {
	
	public Categoria findByIdAndNome(Integer id, String nome);
}
