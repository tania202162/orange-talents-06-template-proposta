package br.com.zup.academy.tania.proposta.Carteira;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.zup.academy.tania.proposta.Cartao.Cartao;

@Valid
public class CarteiraRequest {
	
	@Email
	@NotBlank
	private String email;

	@NotNull
	private EnumCarteira carteira;

	@Deprecated
	public CarteiraRequest() {
	}

	public CarteiraRequest(@Email @NotBlank String email, @NotNull EnumCarteira carteira) {
		this.email = email;
		this.carteira = carteira;
	}

	public String getEmail() {
		return email;
	}

	public EnumCarteira getCarteira() {
		return carteira;
	}

	public Carteira toModel(Cartao cartao) {
		return new Carteira(cartao, this.email, this.carteira);
	}
	
}
