package br.com.zup.academy.tania.proposta.NovaProposta;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

public class ConsultaStatusResponse {
	
	private String nome;
	private String documento;
	private String idProposta;
	@Enumerated(EnumType.STRING)
	private EnumStatus status;

	

public ConsultaStatusResponse(String nome, String documento, String idProposta, EnumStatus status) {

	this.documento = documento;
	this.nome = nome;
	this.idProposta = idProposta;
	this.status = status;
	
}
}