package br.com.zup.academy.tania.proposta.NovaProposta;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class NovaProposta {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	@NotBlank
	private String documento; // cpf/cnpj

	@Column(nullable = false)
	@NotBlank
	private String email;

	@NotBlank
	@Column(nullable = false)
	private String nome;

	@Column(nullable = false)
	@NotBlank
	private String endereco;
		
	@Column(nullable = false)
	@NotNull
	private BigDecimal salario;
	
	public Long getId() {
		return id;
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

	public String getEndereco() {
		return endereco;
	}

	public BigDecimal getSalario() {
		return salario;
	}

	public NovaProposta(@NotBlank String documento, @Email @NotBlank String email, @NotBlank String nome,
			@NotBlank String endereco, @NotNull BigDecimal salario) {
		
		this.documento = documento;
		this.email = email;
		this.nome = nome;
		this.endereco = endereco;
		this.salario = salario;
	}

	@Override
	public String toString() {
		return "Proposta: [id= " + id + ",documento=" + documento + ",email=" + email + ",nome=" + nome + ",endereco=" + endereco + ", salario=" + salario + "]";
	}
}