package mkkg.fatec.esiii.repositories;

import mkkg.fatec.esiii.domain.cartao.Bandeira;
import mkkg.fatec.esiii.domain.cartao.BandeiraResponseDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BandeiraRepository extends JpaRepository<Bandeira, Integer> {

    Bandeira findByBandeira(String bandeira);

    @Query("select new mkkg.fatec.esiii.domain.cartao.BandeiraResponseDTO(b.bandeira) from Bandeira b order by b.bandeira")
    List<BandeiraResponseDTO> findAllAsBandeiraResponseDtoList();

}
