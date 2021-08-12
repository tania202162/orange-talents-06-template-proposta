package br.com.zup.academy.tania.proposta.Carteira;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import br.com.zup.academy.tania.proposta.Cartao.Cartao;

public interface CarteiraRepository extends JpaRepository<Carteira, Long> {

	Optional<Carteira> findByEnumCarteiraAndCartao(EnumCarteira enumCarteira, Cartao cartao);

}
