package mkkg.fatec.esiii.repositories;

import mkkg.fatec.esiii.domain.endereco.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Integer> {

    @Query("select new mkkg.fatec.esiii.domain.endereco.Endereco(e.id) from Endereco e where e.cliente.id = ?1 and e.cobranca = true")
    List<Endereco> findByClienteIdAndCobrancaIsTrue(Integer clienteId);

    @Query("select new mkkg.fatec.esiii.domain.endereco.Endereco(e.id) from Endereco e where e.cliente.id = ?1 and e.entrega = true")
    List<Endereco> findByClienteIdAndEntregaIsTrue(Integer clienteId);

}
