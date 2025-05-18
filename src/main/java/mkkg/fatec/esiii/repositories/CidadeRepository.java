package mkkg.fatec.esiii.repositories;

import mkkg.fatec.esiii.domain.endereco.Cidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Integer> {

    Cidade findByNome(String nome);

}
