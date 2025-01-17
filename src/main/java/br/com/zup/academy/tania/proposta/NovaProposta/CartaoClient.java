package br.com.zup.academy.tania.proposta.NovaProposta;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.zup.academy.tania.proposta.Aviso.NovoAvisoRequest;
import br.com.zup.academy.tania.proposta.Bloqueio.BloqueioCartaoRequest;
import br.com.zup.academy.tania.proposta.Carteira.NovaCarteiraRequest;

@FeignClient(name = "cartaoCliente", url = "${cartoes.host}")
public interface CartaoClient {

	@GetMapping()
	ConsultaCartaoResponse consulta(@RequestParam String idProposta);
	
	@PostMapping(value = "{id}/bloqueios")
	void bloqueio(@PathVariable String id, BloqueioCartaoRequest bloqueioCartaoRequest);
	
	@PostMapping(value = "{id}/avisos")
	void aviso(@PathVariable String id, NovoAvisoRequest novoAvisoRequest);
	
	@PostMapping(value = "{id}/carteiras")
	void carteira(@PathVariable String id, NovaCarteiraRequest novaCarteiraRequest);
	
	class ConsultaCartaoResponse {
		private String id;

		private String numero;

		private LocalDateTime emitidoEm;

		private String titular;

		private BigDecimal limite;

		@Override
		public String toString() {
			return "ConsultaCartaoResponse [id=" + id + ", numero=" + numero + ", emitidoEm=" + emitidoEm + ", titular="
					+ titular + ", limite=" + limite + "]";
		}

		public String getId() {
			return id;
		}

		public String getNumero() {
			return numero;
		}

		public LocalDateTime getEmitidoEm() {
			return emitidoEm;
		}

		public String getTitular() {
			return titular;
		}

		public BigDecimal getLimite() {
			return limite;
		}
	}
}