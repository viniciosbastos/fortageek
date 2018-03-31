package br.com.fortageek.repositories;

import org.springframework.data.jpa.repository.JpaRepository;


import br.com.fortageek.models.Proposta;

public interface IPropostaRepository extends JpaRepository<Proposta, Integer>{		
	
}
