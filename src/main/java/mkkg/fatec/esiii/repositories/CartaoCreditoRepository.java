package mkkg.fatec.esiii.repositories;

import jakarta.transaction.Transactional;
import mkkg.fatec.esiii.domain.cartao.CartaoCredito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartaoCreditoRepository extends JpaRepository<CartaoCredito, Integer> {

    List<CartaoCredito> findByClienteId(Integer id);

    @Transactional
    @Modifying
    @Query("update CartaoCredito c set c.preferencial = false where c.cliente.id = ?1")
    void setPreferencialToFalseByCliente(Integer clienteId);

}
