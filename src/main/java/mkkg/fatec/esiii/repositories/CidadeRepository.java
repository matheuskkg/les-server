package mkkg.fatec.esiii.repositories;

import mkkg.fatec.esiii.domain.endereco.Cidade;
import mkkg.fatec.esiii.domain.endereco.CidadeResponseDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Integer> {

    Cidade findByNome(String nome);

    @Query("select new mkkg.fatec.esiii.domain.endereco.CidadeResponseDTO(c.nome) from Cidade c join Estado e on e.id = c.estado.id where e.nome = ?1")
    List<CidadeResponseDTO> findByEstadoNome(String nome);

}
