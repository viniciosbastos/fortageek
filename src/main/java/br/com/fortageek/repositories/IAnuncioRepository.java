package br.com.fortageek.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fortageek.models.Anuncio;

public interface IAnuncioRepository extends JpaRepository<Anuncio, Integer> {

	public Anuncio findByIdAndStatus(Integer id, String status);
	
	public List<Anuncio> findByUsuarioId(Integer id);
}
 