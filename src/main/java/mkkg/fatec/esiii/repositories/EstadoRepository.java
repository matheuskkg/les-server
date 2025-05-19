package mkkg.fatec.esiii.repositories;

import mkkg.fatec.esiii.domain.endereco.Estado;
import mkkg.fatec.esiii.domain.endereco.EstadoResponseDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Integer> {

    @Query("select new mkkg.fatec.esiii.domain.endereco.EstadoResponseDTO(e.nome) from Estado e join Pais p on p.id = e.pais.id where p.nome = ?1 order by e.nome")
    List<EstadoResponseDTO> findByPaisNome(String nome);

}
