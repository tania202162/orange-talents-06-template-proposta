package br.com.zup.academy.tania.proposta.NovaProposta;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import br.com.zup.academy.tania.proposta.Validator.UniqueValue;
import br.com.zup.academy.tania.proposta.Validator.ValidaCpfCnpj;


public class NovaPropostaRequest {
	
	@Column(nullable = false)
	@NotBlank
	@ValidaCpfCnpj
    @UniqueValue(domainClass = NovaProposta.class, fieldName = "documento")
	private String documento; // cpf/cnpj
	
	@Column(nullable = false)
	@NotBlank
	@Email
	@UniqueValue(domainClass = NovaProposta.class, fieldName = "email")
	private String email;

	@NotBlank
	@Column(nullable = false)
	private String nome;

	@Column(nullable = false)
	@NotBlank
	private String endereco;
	
	@Column(nullable = false)
	@NotNull
	@Positive
	private BigDecimal salario;
	
	
	public NovaPropostaRequest(@NotBlank String documento, @NotBlank String email, @NotBlank String nome,
			@NotBlank String endereco, @NotNull BigDecimal salario) {

		this.documento = documento;
		this.email = email;
		this.nome = nome;
		this.endereco = endereco;
		this.salario = salario;
	}

	public NovaProposta toModel() {
		return new NovaProposta(this.documento,this.email, this.nome, this.endereco, this.salario);
	}
}