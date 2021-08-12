package br.com.zup.academy.tania.proposta.Aviso;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zup.academy.tania.proposta.Bloqueio.BloqueioCartao;
import br.com.zup.academy.tania.proposta.Bloqueio.BloqueioCartaoRepository;
import br.com.zup.academy.tania.proposta.Cartao.Cartao;
import br.com.zup.academy.tania.proposta.Cartao.CartaoRepository;
import br.com.zup.academy.tania.proposta.NovaProposta.CartaoClient;
import feign.FeignException;

@RestController
@RequestMapping("/api/cartoes")
public class NovoAvisoController {

	private CartaoRepository cartaoRepository;

	private BloqueioCartaoRepository bloqueioCartaoRepository;

	private CartaoClient cartaoClient;

	private AvisoRepository avisoRepository;

	private final Logger logger = LoggerFactory.getLogger(Aviso.class);

	public NovoAvisoController(CartaoRepository cartaoRepository, BloqueioCartaoRepository bloqueioCartaoRepository,
			CartaoClient cartaoClient, AvisoRepository avisoRepository) {
		this.cartaoRepository = cartaoRepository;
		this.bloqueioCartaoRepository = bloqueioCartaoRepository;
		this.cartaoClient = cartaoClient;
		this.avisoRepository = avisoRepository;
	}

	@PostMapping("/{id}/avisos")
	public ResponseEntity<?> aviso(@PathVariable Long id, HttpServletRequest request,
			@RequestBody @Valid NovoAvisoRequest avisoRequest) {

		Optional<Cartao> cartao = cartaoRepository.findById(id);
		Optional<BloqueioCartao> cartoesBloqueados = bloqueioCartaoRepository.findById(id);

		if (cartao.isPresent()) {
			if (cartoesBloqueados.isPresent()) {
				return ResponseEntity.unprocessableEntity().body("Seu cartão está bloqueado!");
			}
			try {

				cartaoClient.aviso(cartao.get().getNumero(),
						new NovoAvisoRequest(avisoRequest.getDestino(), avisoRequest.getValidoAte()));

			} catch (FeignException.UnprocessableEntity ex) {
				Map<String, Object> badRequest = new HashMap<>();
				badRequest.put("Algum processo interno foi violado", badRequest);
				return ResponseEntity.unprocessableEntity().body(badRequest);
			}

			Aviso aviso = new Aviso(cartao.get(), avisoRequest.getDestino(), avisoRequest.getValidoAte(),
					request.getRemoteAddr(), request.getHeader("User-Agent"));
			cartao.get().atualizaAviso(aviso);
			
			avisoRepository.save(aviso);

			logger.info("Aviso realizado para o cartao={}!", cartao.get().getNumero());
			return ResponseEntity.ok().build();
		}
  		    return ResponseEntity.notFound().build();
	}
}