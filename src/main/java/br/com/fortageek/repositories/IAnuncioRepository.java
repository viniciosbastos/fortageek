package br.com.fortageek.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fortageek.models.Anuncio;

public interface IAnuncioRepository extends JpaRepository<Anuncio, Integer> {

	public Anuncio findByIdAndStatus(Integer id, String status);
}
