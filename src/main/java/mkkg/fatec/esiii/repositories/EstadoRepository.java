package mkkg.fatec.esiii.repositories;

import mkkg.fatec.esiii.domain.endereco.Estado;
import mkkg.fatec.esiii.domain.endereco.EstadoResponseDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Integer> {

    @Query("select new mkkg.fatec.esiii.domain.endereco.EstadoResponseDTO(e.id, e.nome, e.uf) from Estado e where e.pais.nome = ?1 order by e.nome")
    List<EstadoResponseDTO> findAllByPaisNomeOrderByEstadoNome(String nome);

}
