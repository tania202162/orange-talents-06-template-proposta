package br.com.zup.academy.tania.proposta.NovaProposta;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import feign.FeignException;

import java.net.URI;
import java.util.HashMap;
import java.util.Optional;

	@RestController
	public class NovaPropostaController {
		
	private final Logger logger = LoggerFactory.getLogger(NovaProposta.class);	
	private AnaliseClient analiseClient;
	private NovaPropostaRepository novaPropostaRepository;
	
	@Autowired 
	public NovaPropostaController(AnaliseClient analiseClient,NovaPropostaRepository novaPropostaRepository ) {
		this.analiseClient = analiseClient;
		this.novaPropostaRepository = novaPropostaRepository;
	}
	
	@PersistenceContext
	private EntityManager manager;
		
	@PostMapping(value = "/proposta")
	public ResponseEntity<?> cria(@RequestBody @Valid NovaPropostaRequest request,
			UriComponentsBuilder uriBuilder) {

		if (novaPropostaRepository.existsByDocumento(request.getDocumento())) {
			HashMap<String, Object> resposta = new HashMap<>();
			resposta.put("mensagem", "Já existe documento cadastrado");
			return ResponseEntity.unprocessableEntity().body(resposta);
		}
				
		NovaProposta novaProposta = request.toModel();
		novaPropostaRepository.save(novaProposta);
		//manager.persist(novaProposta);
	
		
		try {
			analiseClient.consulta(new AnaliseClient.ConsultaStatusRequest(novaProposta));
			novaProposta.atualizaStatus(EnumStatus.ELEGIVEL);
		} catch (FeignException.UnprocessableEntity ex) {
			novaProposta.atualizaStatus(EnumStatus.NAO_ELEGIVEL);
		}
		
		novaPropostaRepository.save(novaProposta);
		URI location = uriBuilder.path("/api/proposta/{id}").buildAndExpand(novaProposta.getIdProposta()).toUri();

		logger.info("Proposta documento={} salário={} criada com sucesso!", novaProposta.getDocumento(),novaProposta.getSalario());
		return ResponseEntity.created(location).build(); 
	}

	@GetMapping("/proposta/{idProposta}")
	public ResponseEntity<?> consulta(@PathVariable Long idProposta) {
		Optional<NovaProposta> novaProposta = novaPropostaRepository.findById(idProposta);
		if (novaProposta.isPresent()) {
			return ResponseEntity.ok(new NovaPropostaResponse(novaProposta.get()));
		}
		
		return ResponseEntity.notFound().build();
	} 
}