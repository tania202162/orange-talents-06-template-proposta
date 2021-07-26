package br.com.zup.academy.tania.proposta.NovaProposta;

import java.math.BigDecimal;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

public class NovaPropostaResponse {
	
	private String documento;
	private String email;
	private String nome;
	private BigDecimal salario;
	private String endereco;
	@Enumerated(EnumType.STRING)
	private EnumStatus status;
	
	public NovaPropostaResponse(NovaProposta novaProposta) {
		this.documento = novaProposta.getDocumento();
		this.email = novaProposta.getEmail();
		this.nome = novaProposta.getNome();
		this.salario = novaProposta.getSalario();
		this.endereco = novaProposta.getEndereco();
		this.status = novaProposta.getStatus();
	}

	public String getDocumento() {
		return documento;
	}

	public String getEmail() {
		return email;
	}

	public String getNome() {
		return nome;
	}

	public BigDecimal getSalario() {
		return salario;
	}

	public String getEndereco() {
		return endereco;
	}

	public EnumStatus getStatus() {
		return status;
	}
}