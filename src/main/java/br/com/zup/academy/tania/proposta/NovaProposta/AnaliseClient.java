package br.com.zup.academy.tania.proposta.NovaProposta;

import org.springframework.cloud.openfeign.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "analiseCliente", url = "http://localhost:9999")
public interface AnaliseClient {
	
	@PostMapping("/api/solicitacao")
	ConsultaStatusResponse consulta(@RequestBody ConsultaStatusRequest consultaStatusRequest);

	class ConsultaStatusRequest {
		private String documento;
		private String nome;
		private Long idProposta;

		@Deprecated
		public ConsultaStatusRequest() {
		}
	
		public ConsultaStatusRequest(NovaProposta novaProposta) {

			this.documento = novaProposta.getDocumento();
			this.nome = novaProposta.getNome();
			this.idProposta = novaProposta.getIdProposta();
		}
		public String getDocumento() {
			return documento;
		}
		public String getNome() {
			return nome;
		}
		public Long getIdProposta() {
			return idProposta;
		}
	}
}