package br.com.fortageek.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.fortageek.models.Item;
import br.com.fortageek.repositories.IItemRepository;
import br.com.fortageek.responses.ItemResponse;
import br.com.fortageek.responses.MessageResponse;
import br.com.fortageek.responses.Response;

@RestController
@RequestMapping("/api/item")
public class ItemService {
	
	@Autowired
	private IItemRepository iItemRepository;
	
	@RequestMapping(path = "", method = RequestMethod.GET)
	public Response getAll() {
		return new Response(true,new ItemResponse(iItemRepository.findAll()));
	}
	

	@RequestMapping(path = "", method = RequestMethod.POST)
	public Response newItem(@RequestBody Item item) {
		//teste
		if(item != null && item.validate()) {
			iItemRepository.save(item);
			return new Response(true,new MessageResponse("Item adicionada com sucesso!"));
		}
		else {
			//todo else false
			return new Response(false,new MessageResponse("Item não adicionado"));
		}
		
	}
	
	@RequestMapping(path = "delete/{id}", method = RequestMethod.DELETE)
	public Response delete(@PathVariable("id") Integer id) {
		//teste
		iItemRepository.deleteById(id);
		return new Response(true,new MessageResponse("Item deletado com sucesso!"));
		
		
	}

}
