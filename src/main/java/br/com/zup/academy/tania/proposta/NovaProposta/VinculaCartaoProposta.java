package br.com.zup.academy.tania.proposta.NovaProposta;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import br.com.zup.academy.tania.proposta.NovaProposta.CartaoClient.ConsultaCartaoResponse;
import feign.FeignException;

@Component
public class VinculaCartaoProposta {

	private CartaoClient cartaoClient;
	
	private NovaPropostaRepository propostaRepository;

	public VinculaCartaoProposta(CartaoClient cartaoClient, NovaPropostaRepository propostaRepository) {
		this.propostaRepository = propostaRepository;
		this.cartaoClient = cartaoClient;
	}

	@Transactional
	@Scheduled(fixedDelay = 5000)
	void vinculaCartaoProposta() {

		List<NovaProposta> lista = propostaRepository.findByStatusAndCartaoNull(EnumStatus.ELEGIVEL);
		for (NovaProposta proposta : lista) {
			try {
								
				ConsultaCartaoResponse resposta = cartaoClient.consulta(proposta.getIdProposta().toString());
				
				if (resposta.getId() != null) {
					proposta.vincularCartao(resposta);
					propostaRepository.save(proposta);
					
				}
			} catch (FeignException.InternalServerError e) {
				System.out.println("Cartão não existente para a proposta" + proposta.getIdProposta());
			}

		}

	}

}