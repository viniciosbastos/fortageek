package br.com.fortageek.services;

import java.util.Base64.Encoder;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.fortageek.models.Usuario;
import br.com.fortageek.repositories.IUsuarioRepository;
import br.com.fortageek.responses.MessageResponse;
import br.com.fortageek.responses.Response;

@RestController
@RequestMapping("/")
public class LoginService {

	@Autowired
	private IUsuarioRepository usuarioRepository;
	
	@RequestMapping(path = "/api/login", method = RequestMethod.POST)
	public Response doLogin(@RequestBody Usuario usuario) {
		Usuario u = usuarioRepository.findByUsernameAndPassword(usuario.getUsername(), usuario.getPassword());
		
		String responseString = null;
		if (u != null) {
			String auth = usuario.getUsername() + ":" + usuario.getPassword();
			responseString = new String(Base64.encodeBase64(auth.getBytes()));
		} else {
			responseString = "Usuário não encontrado.";
		}
		return new Response(true, new MessageResponse(responseString));
	}
}
