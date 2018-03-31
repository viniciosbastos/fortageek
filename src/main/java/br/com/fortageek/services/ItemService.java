package br.com.fortageek.services;

import org.springframework.beans.factory.annotation.Autowired;
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
		iItemRepository.save(item);
		return new Response(true,new MessageResponse("Item adicionada com sucesso!"));
	}

}
