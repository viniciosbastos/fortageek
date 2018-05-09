package br.com.fortageek.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.fortageek.models.Anuncio;

public interface IAnuncioRepository extends JpaRepository<Anuncio, Integer> {

	public Anuncio findByIdAndStatus(Integer id, String status);
	
	public List<Anuncio> findByUsuarioId(Integer id);

	@Query("select a from anuncio a where a.usuario.id <> :id")
	public List<Anuncio> findAllOthers(@Param("id") Integer id);

	@Query("select a from anuncio a where a.usuario.id <> :id and upper(a.item.nome) like '%'||upper(:nome)||'%'")
	public List<Anuncio> findAllOthersByName(@Param("nome")String nome, @Param("id") Integer id);
}
 