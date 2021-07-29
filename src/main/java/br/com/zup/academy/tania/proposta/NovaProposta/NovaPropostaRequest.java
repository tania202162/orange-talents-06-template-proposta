package br.com.zup.academy.tania.proposta.NovaProposta;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import br.com.zup.academy.tania.proposta.Validator.UniqueValue;
import br.com.zup.academy.tania.proposta.Validator.ValidaCpfCnpj;


public class NovaPropostaRequest {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idProposta;
	
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

	@Enumerated(EnumType.STRING)
	private EnumStatus status;
	
	private String idCartao;
		
	
	public String getDocumento() {
		return documento;
	}

	public String getEmail() {
		return email;
	}

	public String getNome() {
		return nome;
	}

	public String getEndereco() {
		return endereco;
	}

	public BigDecimal getSalario() {
		return salario;
	}

	public EnumStatus getStatus() {
		return status;
	}

	public NovaPropostaRequest( Long idProposta, @NotBlank String documento, @NotBlank String email, @NotBlank String nome,
			@NotBlank String endereco, @NotNull BigDecimal salario,EnumStatus status,String idCartao) {

		this.idProposta = idProposta;
		this.documento = documento;
		this.email = email;
		this.nome = nome;
		this.endereco = endereco;
		this.salario = salario;
		this.status = status;
		this.idCartao = idCartao;
		
	}

	public NovaProposta toModel() {
		return new NovaProposta(this.idProposta,this.documento,this.email, this.nome, this.endereco, this.salario, this.status, this.idCartao);
	}
}