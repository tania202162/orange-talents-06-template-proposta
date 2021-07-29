package br.com.zup.academy.tania.proposta.Biometria;

import javax.validation.constraints.NotBlank;

import org.apache.commons.codec.binary.Base64;

public class NovaBiometriaRequest {

	@NotBlank
	private String biometria;

	@Deprecated
	public NovaBiometriaRequest() {
	}

	public NovaBiometriaRequest(@NotBlank String biometria) {
		this.biometria = biometria;
	}

	public String getBiometria() {
		return biometria;
	}

	public boolean estaEm64Bits() {

		return Base64.isBase64(biometria.getBytes());
	}
}