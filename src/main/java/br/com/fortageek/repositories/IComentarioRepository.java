package br.com.fortageek.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fortageek.models.Comentario;

public interface IComentarioRepository extends JpaRepository<Comentario, Integer>{

	List<Comentario> findByAnuncioIdOrderByData(Integer anuncioId);

}
