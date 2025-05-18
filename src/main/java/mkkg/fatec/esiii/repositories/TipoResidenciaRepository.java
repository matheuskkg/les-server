package mkkg.fatec.esiii.repositories;

import mkkg.fatec.esiii.domain.endereco.TipoResidencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoResidenciaRepository extends JpaRepository<TipoResidencia, Integer> {

    TipoResidencia findByTipo(String tipo);

}
