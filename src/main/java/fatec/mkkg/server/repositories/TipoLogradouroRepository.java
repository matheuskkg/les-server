package fatec.mkkg.server.repositories;

import fatec.mkkg.server.domain.endereco.TipoLogradouro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TipoLogradouroRepository extends JpaRepository<TipoLogradouro, Integer> {

	Optional<TipoLogradouro> findByTipoIgnoreCase(String tipo);

}
