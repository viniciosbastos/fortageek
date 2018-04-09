package br.com.fortageek.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.fortageek.models.Anuncio;
import br.com.fortageek.repositories.IAnuncioRepository;
import br.com.fortageek.responses.AnuncioResponse;
import br.com.fortageek.responses.MessageResponse;
import br.com.fortageek.responses.Response;

@RestController
@RequestMapping("/api/anuncio")
public class AnuncioService {

	@Autowired
	private IAnuncioRepository anuncioRepository;
	
	@RequestMapping(path = "", method = RequestMethod.GET)
	public Response getAll() {
		return new Response(true,new AnuncioResponse(anuncioRepository.findAll()));
	}
	
	@RequestMapping(path = "", method = RequestMethod.POST)
	public Response newAnuncio(@RequestBody Anuncio anuncio) {
		anuncioRepository.save(anuncio);
		return new Response(true,new MessageResponse("Anúncio adicionada com sucesso!"));
	}
	
	
}
