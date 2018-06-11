package br.com.fortageek.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.fortageek.models.Proposta;

public interface IPropostaRepository extends JpaRepository<Proposta, Integer>{
	
//	Proposta findById(Integer id);
	
	List<Proposta> findByUsuarioId(Integer id);
	
	@Query("select p from proposta p where p.anuncio.id = :id and p.status <> 2")
	List<Proposta> findPropostasAnuncio(@Param("id") Integer id);

	@Query("select p from proposta p where p.anuncio.id = :id and p.status = 1")
	Proposta findPropostaFinalizada(@Param("id") Integer id);
}
