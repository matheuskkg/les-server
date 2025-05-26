package mkkg.fatec.esiii.repositories;

import jakarta.transaction.Transactional;
import mkkg.fatec.esiii.domain.cartao.CartaoCredito;
import mkkg.fatec.esiii.domain.cartao.CartaoCreditoResponseDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartaoCreditoRepository extends JpaRepository<CartaoCredito, Integer> {

    @Query("""
        select new mkkg.fatec.esiii.domain.cartao.CartaoCreditoResponseDTO(
            c.id,
            c.bandeira.bandeira,
            c.nomeTitular,
            c.numero,
            c.codigoSeguranca,
            c.preferencial
        )
        from CartaoCredito c
        where c.cliente.id = ?1
    """)
    List<CartaoCreditoResponseDTO> findByClienteId(Integer id);

    @Transactional
    @Modifying
    @Query("update CartaoCredito c set c.preferencial = false where c.cliente.id = ?1")
    void setPreferencialToFalseByCliente(Integer clienteId);

    @Transactional
    @Modifying
    @Query("delete from CartaoCredito c where c.id = ?1")
    void deleteCartaoCreditoById(Integer id);

}
