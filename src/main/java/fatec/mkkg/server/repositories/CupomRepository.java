package fatec.mkkg.server.repositories;

import fatec.mkkg.server.domain.pagamento.formas.cupom.Cupom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CupomRepository extends JpaRepository<Cupom, Integer> {

	@Query("select c.valor from Cupom c where c.cliente.id = :clienteId and c.id = :cupomId")
	Optional<Integer> obterValorDoCupom(@Param("cupomId") Integer cupomId, @Param("clienteId") Integer clienteId);

}
