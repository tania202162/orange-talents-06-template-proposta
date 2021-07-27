package br.com.zup.academy.tania.proposta.NovaProposta;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.zup.academy.tania.proposta.Cartao.Cartao;
import br.com.zup.academy.tania.proposta.NovaProposta.CartaoClient.ConsultaCartaoResponse;

@Entity
public class NovaProposta {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idProposta;
	
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
	
	@Enumerated(EnumType.STRING)
	private EnumStatus status;
	
	private String idCartao;

	@OneToOne(mappedBy = "proposta", cascade = CascadeType.ALL)
	private Cartao cartao;	
	
	public EnumStatus getStatus() {
		return status;
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
		
		public Long getIdProposta() {
		return idProposta;
	}
		
		public String getIdCartao() {
			return idCartao;
		}

		public Cartao getCartao() {
			return cartao;
		}	
		

	public NovaProposta(@NotBlank String documento, @Email @NotBlank String email, @NotBlank String nome,
			@NotBlank String endereco, @NotNull BigDecimal salario) {
		
		this.documento = documento;
		this.email = email;
		this.nome = nome;
		this.endereco = endereco;
		this.salario = salario;
	}

	public NovaProposta() {}
		
	@Override
	public String toString() {
		return "Proposta: [id=" + idProposta + ", documento=" + documento + ", email=" + email + ", nome=" + nome + ", salario=" + salario + ", endereco=" + endereco + ", status=" + status + ", idCartao=" + idCartao + ", cartao=" + cartao + "]";
	}

	public void atualizaStatus(EnumStatus status) {
		this.status = status;
	}

	public void vincularCartao(ConsultaCartaoResponse response) {
		this.cartao = new Cartao(response, this);
		this.idCartao = response.getId();
	}
			
		}