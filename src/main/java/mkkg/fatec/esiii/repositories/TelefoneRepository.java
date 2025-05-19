package mkkg.fatec.esiii.repositories;

import mkkg.fatec.esiii.domain.telefone.Telefone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TelefoneRepository extends JpaRepository<Telefone, Integer> {
}
