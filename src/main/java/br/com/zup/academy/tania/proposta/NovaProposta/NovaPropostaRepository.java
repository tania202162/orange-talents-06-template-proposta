package br.com.zup.academy.tania.proposta.NovaProposta;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import java.util.List;

public interface NovaPropostaRepository extends JpaRepository<NovaProposta, Long>, JpaSpecificationExecutor<NovaProposta> {

	boolean existsByDocumento(String documento);

	List<NovaProposta> findByStatus(EnumStatus status);

}