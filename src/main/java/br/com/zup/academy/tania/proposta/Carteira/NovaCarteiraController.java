package br.com.zup.academy.tania.proposta.Carteira;

import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zup.academy.tania.proposta.Bloqueio.BloqueioCartao;
import br.com.zup.academy.tania.proposta.Bloqueio.BloqueioCartaoRepository;
import br.com.zup.academy.tania.proposta.Cartao.Cartao;
import br.com.zup.academy.tania.proposta.Cartao.CartaoRepository;
import br.com.zup.academy.tania.proposta.NovaProposta.CartaoClient;
import feign.FeignException;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;

	@RestController
	@RequestMapping("/api/cartoes")
	public class NovaCarteiraController {

		private CarteiraRepository carteiraRepository;
		private CartaoRepository cartaoRepository;
		private BloqueioCartaoRepository bloqueioCartaoRepository;
		private CartaoClient cartaoClient;

		private final Logger logger = LoggerFactory.getLogger(Carteira.class);

		public NovaCarteiraController(CarteiraRepository carteiraRepository, CartaoRepository cartaoRepository,
				BloqueioCartaoRepository bloqueioCartaoRepository, CartaoClient cartaoClient) {

			this.carteiraRepository = carteiraRepository;
			this.cartaoRepository = cartaoRepository;
			this.bloqueioCartaoRepository = bloqueioCartaoRepository;
			this.cartaoClient = cartaoClient;

		}

		@PostMapping("/{id}/carteiras")
		public ResponseEntity<?> carteira(@PathVariable Long id, @RequestBody @Valid CarteiraRequest carteiraRequest,
				UriComponentsBuilder uriBuilder) {

			Optional<Cartao> cartao = cartaoRepository.findById(id);
			Optional<BloqueioCartao> cartoesBloqueados = bloqueioCartaoRepository.findById(id);

			if (cartao.isPresent()) {
				if (cartoesBloqueados.isPresent()) {
					return ResponseEntity.unprocessableEntity().body("Esse cartão está bloqueado!");
				}

				try {

					Optional<Carteira> carteiraExistente = carteiraRepository
							.findByEnumCarteiraAndCartao(carteiraRequest.getCarteira(), cartao.get());

					if (carteiraExistente.isPresent()) {
						return ResponseEntity.unprocessableEntity()
								.body("Esse cartão já está vinculado a carteira " + carteiraRequest.getCarteira() + " !");
					}

					cartaoClient.carteira(cartao.get().getNumero(), new NovaCarteiraRequest(carteiraRequest));
					Carteira carteira = carteiraRequest.toModel(cartao.get());
					cartao.get().adicionaCarteiraDigital(carteira);
					carteiraRepository.save(carteira);

					URI location = uriBuilder.path("/cartoes/{id}/carteiras/{id}").build(cartao.get().getId(),
							carteira.getId());

					logger.info("Carteira={} digital criada com sucesso para o email={}!", carteira.getEnumCarteira(),
							cartao.get().getNumero());

					return ResponseEntity.created(location).build();

				} catch (FeignException.UnprocessableEntity ex) {
					Map<String, Object> badRequest = new HashMap<>();
					badRequest.put("Algum processo interno foi violado", badRequest);
					return ResponseEntity.unprocessableEntity().body(badRequest);
				}

			}
			return ResponseEntity.notFound().build();

		}
	}
