package br.com.fortageek.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.fortageek.models.Usuario;
import br.com.fortageek.repositories.IUsuarioRepository;
import br.com.fortageek.responses.MessageResponse;
import br.com.fortageek.responses.Response;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioService {
	
	@Autowired
	IUsuarioRepository usuarioRepository;

	@RequestMapping(path = "update", method = RequestMethod.POST)
	public Response update(@RequestBody Usuario usuario) {
		if (usuario != null) {
			this.usuarioRepository.save(usuario);
			return new Response(true, new MessageResponse("Usuário atualizado com sucesso!"));
		}
		return new Response(false, new MessageResponse("Erro no envio dos dados."));
		
		
	}
	
	@RequestMapping(path = "delete/{id}", method = RequestMethod.DELETE)
	public Response delete(@PathVariable("id") Integer id) {
		//teste
		usuarioRepository.deleteById(id);
		return new Response(true, new MessageResponse("Usuário deletado com sucesso!"));
		
		
	}
}
