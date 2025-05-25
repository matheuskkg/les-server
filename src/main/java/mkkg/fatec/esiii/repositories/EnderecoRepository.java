package mkkg.fatec.esiii.repositories;

import mkkg.fatec.esiii.domain.endereco.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Integer> {

    Boolean existsByClienteIdAndCobrancaIsTrue(Integer clienteId);

    Boolean existsByClienteIdAndEntregaIsTrue(Integer clienteId);

}
