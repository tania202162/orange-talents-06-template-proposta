package br.com.zup.academy.tania.proposta.NovaProposta;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import java.net.URI;

	@RestController
	public class PropostaController {
		
	@PersistenceContext
	private EntityManager manager;
		
	@PostMapping(value = "/proposta")
	@Transactional
	public ResponseEntity<?> cria(
	@RequestBody @Valid NovaPropostaRequest request,UriComponentsBuilder builder) {

		NovaProposta novaProposta = request.toModel();
		manager.persist(novaProposta);
			
		URI enderecoConsulta = builder.path("/proposta/{id}").build(novaProposta.getId());
		return ResponseEntity.created(enderecoConsulta).build();
		
		}
}