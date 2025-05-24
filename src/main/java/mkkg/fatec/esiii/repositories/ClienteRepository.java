package mkkg.fatec.esiii.repositories;

import mkkg.fatec.esiii.domain.cliente.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

    @Modifying
    @Query("update Cliente c set c.cadastroAtivo = false where c.id = ?1")
    void inativarCadastroCliente(Integer clienteId);

}
