package br.com.fortageek.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.fortageek.models.Anuncio;
import br.com.fortageek.models.Item;
import br.com.fortageek.models.Proposta;
import br.com.fortageek.models.Usuario;
import br.com.fortageek.repositories.IAnuncioRepository;
import br.com.fortageek.repositories.IItemRepository;
import br.com.fortageek.repositories.IPropostaRepository;
import br.com.fortageek.repositories.IUsuarioRepository;
import br.com.fortageek.responses.MessageResponse;
import br.com.fortageek.responses.PropostaResponse;
import br.com.fortageek.responses.Response;

@RestController
@RequestMapping("/api/proposta")
public class PropostaService {
	
	@Autowired
	private IPropostaRepository propostaRepository;
	
	@Autowired
	private IUsuarioRepository usuarioRepository;
	
	@Autowired
	private IItemRepository itemRepository;
	
	@Autowired
	private IAnuncioRepository anuncioRepository;
	
	
	@RequestMapping(path = "", method = RequestMethod.GET)
	public Response getAll(Authentication authentication) {
		Usuario usuario = usuarioRepository.findByUsername(((User) authentication.getPrincipal()).getUsername());
		return new Response(true,new PropostaResponse(propostaRepository.findByUsuarioId(usuario.getId())));
	}
	
	@RequestMapping(path = "/anuncio/{anuncioId}", method = RequestMethod.GET)
	public Response getAllByAnuncio(Authentication authentication, @PathVariable("anuncioId") Integer anuncioId) {
		return new Response(true,new PropostaResponse(propostaRepository.findPropostasAnuncio(anuncioId)));
	}
	

	@RequestMapping(path = "", method = RequestMethod.POST)
	public Response newProposta(@RequestBody Proposta proposta, Authentication authentication) {
		if(	proposta.getAnuncio() != null 
				&& proposta.getAnuncio().getId() != null 
				&& proposta.getItem() != null 
				&& proposta.getItem().validate()) {
			
			Usuario usuario = usuarioRepository.findByUsername(((User) authentication.getPrincipal()).getUsername());
			Item item = proposta.getItem();
			item.setUsuario(usuario);
			itemRepository.save(item);
			
			proposta.setUsuario(usuario);
			proposta.setStatus(0);
			propostaRepository.save(proposta);
			
			Anuncio anuncio = proposta.getAnuncio();
			anuncio.setStatus(2);
			anuncioRepository.save(anuncio);
			return new Response(true,new MessageResponse("Proposta adicionada com sucesso!"));
		}
		else {
			return new Response(false,new MessageResponse("Proposta não adicionada!"));
		}
	}
	
	@RequestMapping(path = "aceitar/{id}", method = RequestMethod.GET)
	public Response aceitar(@PathVariable("id") Integer id) {
		Response response = null;
		Proposta p = (Proposta) propostaRepository.findById(id).get();		
		p.setStatus(1);
		propostaRepository.save(p); 
		
		Anuncio a = p.getAnuncio();
		a.setStatus(3);
		anuncioRepository.save(a);
		response = new Response(true,new MessageResponse("Proposta aceita."));
		
		return response;
	}
	
	@RequestMapping(path = "recusar/{id}", method = RequestMethod.GET)
	public Response recusar(@PathVariable("id") Integer id) {
		Response response = null;
		Proposta p = (Proposta) propostaRepository.findById(id).get();

		p.setStatus(2);
		propostaRepository.save(p); 
		response = new Response(true,new MessageResponse("Proposta recusada."));
		
		return response;
	}
	
	@RequestMapping(path = "delete/{id}", method = RequestMethod.DELETE)
	public Response delete(@PathVariable("id") Integer id) {
		Response response = null;
		Proposta p = (Proposta) propostaRepository.findById(id).get();
		if (p.getStatus() != 1) {
			propostaRepository.deleteById(id); 
			response = new Response(true,new MessageResponse("Proposta deletado com sucesso!"));
		} else {			
			response = new Response(false, new MessageResponse("Proposta não está mais como Enviada."));
		}
		
		return response;
	}
}
