package mkkg.fatec.esiii.repositories;

import mkkg.fatec.esiii.domain.telefone.TipoTelefone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoTelefoneRepository extends JpaRepository<TipoTelefone, Integer> {

    TipoTelefone findByTipo(String tipo);

}
