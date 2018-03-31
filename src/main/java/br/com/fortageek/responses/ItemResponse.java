package br.com.fortageek.responses;

import java.util.List;

import br.com.fortageek.models.Item;

public class ItemResponse {
	
	private List itens;

	public ItemResponse() {
		super();
	}

	public ItemResponse(List<Item> itens) {
		super();
		this.itens = itens;
	}

	public List getItens() {
		return itens;
	}

	public void setItens(List itens) {
		this.itens = itens;
	}
	
	

}
