package mkkg.fatec.esiii.repositories;

import mkkg.fatec.esiii.domain.endereco.Pais;
import mkkg.fatec.esiii.domain.endereco.PaisResponseDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaisRepository extends JpaRepository<Pais, Integer> {

    @Query("select new mkkg.fatec.esiii.domain.endereco.PaisResponseDTO(p.id, p.nome) from Pais p order by p.nome")
    List<PaisResponseDTO> findAllOrderByNome();

}
