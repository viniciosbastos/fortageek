package br.com.fortageek.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.fortageek.models.Proposta;
import br.com.fortageek.repositories.IPropostaRepository;
import br.com.fortageek.responses.MessageResponse;
import br.com.fortageek.responses.PropostaResponse;
import br.com.fortageek.responses.Response;

@RestController
@RequestMapping("/api/proposta")
public class PropostaService {
	
	@Autowired
	private IPropostaRepository propostaRepository;
	
	@RequestMapping(path = "", method = RequestMethod.GET)
	public Response getAll() {
		return new Response(true,new PropostaResponse(propostaRepository.findAll()));
	}
	

	@RequestMapping(path = "", method = RequestMethod.POST)
	public Response newProposta(@RequestBody Proposta proposta) {
		if(	proposta.getAnuncio() != null 
				&& proposta.getAnuncio().getId() != null 
				&& proposta.getItem() != null 
				&& proposta.getItem().validate()) {
			
			propostaRepository.save(proposta);
			return new Response(true,new MessageResponse("Proposta adicionada com sucesso!"));
		}
		else {
			return new Response(false,new MessageResponse("Proposta não adicionada!"));
		}
	}
	
	@RequestMapping(path = "delete/{id}", method = RequestMethod.DELETE)
	public Response delete(@PathVariable("id") Integer id) {
		//teste
		propostaRepository.deleteById(id);
		return new Response(true,new MessageResponse("Proposta deletado com sucesso!"));
		
		
	}
}
