package br.com.fortageek.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.fortageek.models.Anuncio;
import br.com.fortageek.models.Item;
import br.com.fortageek.models.Usuario;
import br.com.fortageek.repositories.IAnuncioRepository;
import br.com.fortageek.repositories.IItemRepository;
import br.com.fortageek.repositories.IUsuarioRepository;
import br.com.fortageek.responses.AnuncioResponse;
import br.com.fortageek.responses.MessageResponse;
import br.com.fortageek.responses.Response;

@RestController
@RequestMapping("/api/anuncio")
public class AnuncioService {

	@Autowired
	private IAnuncioRepository anuncioRepository;
	
	@Autowired
	private IItemRepository itemRespository;
	
	@Autowired
	private IUsuarioRepository usuarioRepository;
	
	@RequestMapping(path = "", method = RequestMethod.GET)
	public Response getAll(@RequestParam("nome") String nome, @RequestParam(value = "categoria_id", defaultValue = "") Integer categoriaId, Authentication authentication) {
		Usuario usuario = usuarioRepository.findByUsername(((User) authentication.getPrincipal()).getUsername());
		List<Anuncio> anuncios = null;
		if (nome == null || nome.equals("")) {
			anuncios = anuncioRepository.findAllOthers(usuario.getId());
		} else {
			anuncios = anuncioRepository.findAllOthersByName(nome, usuario.getId());			
		}
		return new Response(true, new AnuncioResponse(anuncios));
	}
	
	@RequestMapping(path = "", method = RequestMethod.POST)
	public Response newAnuncio(@RequestBody Anuncio anuncio, Authentication authentication) {
		if(anuncio.getItem() != null && anuncio.getItem().validate()) {
			Usuario usuario = usuarioRepository.findByUsername(((User) authentication.getPrincipal()).getUsername());
			Item item = anuncio.getItem();
			item.setUsuario(usuario);
			
			itemRespository.save(item);
			anuncio.setUsuario(usuario);
			anuncio.setStatus(1);
			anuncioRepository.save(anuncio);
			return new Response(true,new MessageResponse("Anúncio adicionado com sucesso."));
		}
		else {
			return new Response(false,new MessageResponse("Anúncio não pôde ser adicionado."));
		}
		
	}
	
	@RequestMapping(path = "/meus-anuncios", method = RequestMethod.GET)
	public Response getAnunciosUsuario(Authentication authentication) {
		Usuario usuario = usuarioRepository.findByUsername(((User) authentication.getPrincipal()).getUsername()); 
		return new Response(true, new AnuncioResponse(usuario.getAnuncios()));
	}
	
	@RequestMapping(path = "delete/{id}", method = RequestMethod.DELETE)
	public Response delete(@PathVariable("id") Integer id) {
		//teste
		anuncioRepository.deleteById(id);
		return new Response(true,new MessageResponse("Anúncio deletado com sucesso!"));
	}
}
