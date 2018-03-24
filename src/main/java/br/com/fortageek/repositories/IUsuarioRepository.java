package br.com.fortageek.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fortageek.models.Usuario;

public interface IUsuarioRepository extends JpaRepository<Usuario, Integer>{

	public Usuario findByUsernameAndPassword(String username, String password);
	
}
