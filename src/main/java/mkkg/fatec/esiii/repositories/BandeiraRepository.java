package mkkg.fatec.esiii.repositories;

import mkkg.fatec.esiii.domain.cartao.Bandeira;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BandeiraRepository extends JpaRepository<Bandeira, Integer> {

    Bandeira findByBandeira(String bandeira);

}
