package fatec.mkkg.server.repositories;

import fatec.mkkg.server.domain.telefone.TipoTelefone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TipoTelefoneRepository extends JpaRepository<TipoTelefone, Integer> {

	Optional<TipoTelefone> findByTipoIgnoreCase(String tipo);

}
