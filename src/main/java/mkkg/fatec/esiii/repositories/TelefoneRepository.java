package mkkg.fatec.esiii.repositories;

import mkkg.fatec.esiii.domain.telefone.Telefone;
import mkkg.fatec.esiii.domain.telefone.TipoTelefone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TelefoneRepository extends JpaRepository<Telefone, Integer> {

    @Modifying
    @Query("update Telefone t set t.ddd = ?2, t.tipoTelefone = ?3, t.numero = ?4 where t.cliente.id = ?1")
    void alterarCadastroTelefonePorClienteId(Integer clienteId, String ddd, TipoTelefone tipoTelefone, String numero);

}
