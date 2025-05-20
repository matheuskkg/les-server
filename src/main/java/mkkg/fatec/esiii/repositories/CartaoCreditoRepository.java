package mkkg.fatec.esiii.repositories;

import mkkg.fatec.esiii.domain.cartao.CartaoCredito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartaoCreditoRepository extends JpaRepository<CartaoCredito, Integer> {

    List<CartaoCredito> findByClienteId(Integer id);

}
