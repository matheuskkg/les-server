package fatec.mkkg.server.repositories;

import fatec.mkkg.server.domain.cartao.Bandeira;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BandeiraRepository extends JpaRepository<Bandeira, Integer> {

}
