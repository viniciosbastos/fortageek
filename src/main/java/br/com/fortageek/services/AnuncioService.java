package br.com.fortageek.services;

import java.util.Date;
import java.util.List;
import java.util.Set;

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
import br.com.fortageek.models.Comentario;
import br.com.fortageek.models.Item;
import br.com.fortageek.models.Proposta;
import br.com.fortageek.models.Usuario;
import br.com.fortageek.repositories.IAnuncioRepository;
import br.com.fortageek.repositories.IComentarioRepository;
import br.com.fortageek.repositories.IItemRepository;
import br.com.fortageek.repositories.IPropostaRepository;
import br.com.fortageek.repositories.IUsuarioRepository;
import br.com.fortageek.responses.AnuncioResponse;
import br.com.fortageek.responses.ComentarioResponse;
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
	
	@Autowired
	private IPropostaRepository propostaRepository;
	
	@Autowired
	private IComentarioRepository comentarioRepository;
	
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
	
	@RequestMapping(path = "/finalizar/{id}", method = RequestMethod.GET)
	public Response finalizar(@PathVariable("id") Integer id) {
		Response response = null;
		Anuncio anuncio = anuncioRepository.findById(id).get();
		anuncio.setStatus(4);
		anuncioRepository.save(anuncio);
		
		Proposta proposta = propostaRepository.findPropostaFinalizada(anuncio.getId());
		proposta.setStatus(3);
		propostaRepository.save(proposta);
		
		response = new Response(true, new MessageResponse("Anúncio finalizado com sucesso."));
		
		return response;
	}
	
	@RequestMapping(path = "/meus-anuncios", method = RequestMethod.GET)
	public Response getAnunciosUsuario(Authentication authentication) {
		Usuario usuario = usuarioRepository.findByUsername(((User) authentication.getPrincipal()).getUsername()); 
		return new Response(true, new AnuncioResponse(usuario.getAnuncios()));
	}
	
	@RequestMapping(path = "delete/{id}", method = RequestMethod.GET)
	public Response delete(@PathVariable("id") Integer id) {
		Response response = null;
		Anuncio anuncio = anuncioRepository.findById(id).get();
		if (anuncio.getStatus() < 3) {
			Set<Proposta> propostas = anuncio.getPropostas();
			for (Proposta p : propostas) {
				propostaRepository.delete(p);
			}
			
			anuncioRepository.delete(anuncio);
			response = new Response(true,new MessageResponse("Anúncio excluído com sucesso."));
		} else {
			response = new Response(false, new MessageResponse("Anúncio não pode mais ser deletado."));
		}
		return response;
	}
	
	@RequestMapping(path = "/{anuncioId}/comentario", method = RequestMethod.POST)
	public Response addComentario(@RequestBody String texto, @PathVariable("anuncioId") Integer anuncioId, Authentication authentication) {
		Response response = null;
		
		if (texto != null && !texto.equals("") && anuncioId != null) {
			Anuncio anuncio = anuncioRepository.findById(anuncioId).get();
			Usuario usuario = usuarioRepository.findByUsername(((User) authentication.getPrincipal()).getUsername());
			
			Comentario comentario = new Comentario();
			comentario.setUsuario(usuario);
			comentario.setData(new Date());
			comentario.setAnuncio(anuncio);
			comentario.setTexto(texto);
			comentarioRepository.save(comentario);
			
			response = new Response(true, new MessageResponse("Comentário adicionado com sucesso."));
		} else {
			response = new Response(false, new MessageResponse("Comentário não pode ser adicionado."));
		}
		
		
		return response;
	}
	
	@RequestMapping(path = "/{anuncioId}/comentario", method = RequestMethod.GET)
	public Response getComentarios(Authentication authentication, @PathVariable("anuncioId") Integer anuncioId) {		
		return new Response(true, new ComentarioResponse(comentarioRepository.findByAnuncioIdOrderByData(anuncioId)));
	}
	

}
