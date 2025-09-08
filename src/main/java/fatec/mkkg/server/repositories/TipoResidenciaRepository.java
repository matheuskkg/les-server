package fatec.mkkg.server.repositories;

import fatec.mkkg.server.domain.endereco.TipoResidencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TipoResidenciaRepository extends JpaRepository<TipoResidencia, Integer> {

	Optional<TipoResidencia> findByTipoIgnoreCase(String tipo);

}
