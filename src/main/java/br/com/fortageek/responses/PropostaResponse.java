package br.com.fortageek.responses;

import java.util.List;

import br.com.fortageek.models.Proposta;

public class PropostaResponse {

	private List propostas;

	public PropostaResponse() {
		super();
	}

	public PropostaResponse(List<Proposta> propostas) {
		super();
		this.propostas = propostas;
	}

	public List getPropostas() {
		return propostas;
	}

	public void setPropostas(List propostas) {
		this.propostas = propostas;
	}
	
	
	
}
