package fatec.mkkg.server.repositories;

import fatec.mkkg.server.domain.cartao.CartaoCredito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartaoCreditoRepository extends JpaRepository<CartaoCredito, Integer> {

	@Query("""
		select new fatec.mkkg.server.domain.cartao.CartaoCredito(
			cc.id,
			cc.bandeira,
			cc.nomeTitular,
			cc.numero,
			cc.codigoSeguranca,
			cc.preferencial
		)
		from CartaoCredito cc
		where cc.cliente.id = :clienteId
	""")
	List<CartaoCredito> buscarPorClienteId(@Param("clienteId") Integer clienteId);

	@Query("""
		select new fatec.mkkg.server.domain.cartao.CartaoCredito(
			cc.id,
			cc.bandeira,
			cc.nomeTitular,
			cc.numero,
			cc.codigoSeguranca,
			cc.preferencial
		)
		from CartaoCredito cc
		where cc.id = :id
	""")
	CartaoCredito buscarPorId(@Param("id") Integer id);

}
