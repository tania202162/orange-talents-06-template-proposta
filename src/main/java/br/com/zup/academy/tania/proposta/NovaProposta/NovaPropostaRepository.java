package br.com.zup.academy.tania.proposta.NovaProposta;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface NovaPropostaRepository extends JpaRepository<NovaProposta, Long>, JpaSpecificationExecutor<NovaProposta> {

	boolean existsByDocumento(String documento);

	List<NovaProposta> findByStatusAndCartaoNull(EnumStatus status);

}