package br.com.fortageek.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.fortageek.models.Cidade;
import br.com.fortageek.repositories.ICidadeRepository;
import br.com.fortageek.responses.CidadeResponse;
import br.com.fortageek.responses.MessageResponse;
import br.com.fortageek.responses.Response;

@RestController
@RequestMapping("/api/cidade")
public class CidadeService {
	
	@Autowired
	private ICidadeRepository cidadeRepository;
	
	@RequestMapping(path = "", method = RequestMethod.GET)
	public Response getAll() {
		return new Response(true,new CidadeResponse(cidadeRepository.findAll()));
	}
	

	@RequestMapping(path = "", method = RequestMethod.POST)
	public Response newCidade(@RequestBody Cidade cidade) {
		if(cidade.getNome() != null && cidade.getUf() != null) {
			cidadeRepository.save(cidade);
			return new Response(true,new MessageResponse("Cidade adicionada com sucesso."));
		}
		else {
			return new Response(false,new MessageResponse("Cidade não pôde ser adicionada."));
		}
		
	}
	
	@RequestMapping(path = "delete/{id}", method = RequestMethod.DELETE)
	public Response delete(@PathVariable("id") Integer id) {
		//teste
		cidadeRepository.deleteById(id);
		return new Response(true,new MessageResponse("Cidade excluída com sucesso."));
		
		
	}

}
