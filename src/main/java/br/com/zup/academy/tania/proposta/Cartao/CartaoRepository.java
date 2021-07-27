package br.com.zup.academy.tania.proposta.Cartao;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CartaoRepository extends JpaRepository<Cartao, Long> {

	Cartao findAllById(Long id);

}
