package br.com.fortageek.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fortageek.models.Cidade;

public interface ICidadeRepository extends JpaRepository<Cidade, Integer> {
	
	
	public Cidade findByIdAndNome(Integer id, String nome);

}
