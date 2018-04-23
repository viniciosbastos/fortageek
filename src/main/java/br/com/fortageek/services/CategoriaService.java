package br.com.fortageek.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.fortageek.models.Categoria;
import br.com.fortageek.repositories.ICategoriaRepository;
import br.com.fortageek.responses.CategoriaResponse;
import br.com.fortageek.responses.MessageResponse;
import br.com.fortageek.responses.Response;

@RestController
@RequestMapping("/api/categoria")
public class CategoriaService {
	
	@Autowired
	private ICategoriaRepository categoriaRepository;
	
	@RequestMapping(path = "", method = RequestMethod.GET)
	public Response getAll() {
		return new Response(true,new CategoriaResponse(categoriaRepository.findAll()));
	}
	

	@RequestMapping(path = "", method = RequestMethod.POST)
	public Response newCategoria(@RequestBody Categoria categoria) {
		if(categoria.getNome()!=null){
			categoriaRepository.save(categoria);
			return new Response(true,new MessageResponse("Categoria adicionada com sucesso!"));
		}
		else {
			return new Response(false,new MessageResponse("Categoria não adicionada!"));
		}
		
	}
	
	@RequestMapping(path = "delete/{id}", method = RequestMethod.DELETE)
	public Response delete(@PathVariable("id") Integer id) {
		//teste
		categoriaRepository.deleteById(id);
		return new Response(true,new MessageResponse("Categoria deletado com sucesso!"));
		
		
	}
}
