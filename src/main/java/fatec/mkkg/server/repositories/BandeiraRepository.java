package fatec.mkkg.server.repositories;

import fatec.mkkg.server.domain.pagamento.cartao.Bandeira;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BandeiraRepository extends JpaRepository<Bandeira, Integer> {

}
