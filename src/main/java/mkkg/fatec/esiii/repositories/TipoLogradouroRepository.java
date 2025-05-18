package mkkg.fatec.esiii.repositories;

import mkkg.fatec.esiii.domain.endereco.TipoLogradouro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoLogradouroRepository extends JpaRepository<TipoLogradouro, Integer> {

    TipoLogradouro findByTipo(String tipo);

}
