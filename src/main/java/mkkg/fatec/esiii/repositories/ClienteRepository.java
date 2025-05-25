package mkkg.fatec.esiii.repositories;

import mkkg.fatec.esiii.domain.cliente.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

    @Modifying
    @Query("update Cliente c set c.cadastroAtivo = false where c.id = ?1")
    void inativarCadastroCliente(Integer clienteId);

    @Modifying
    @Query("update Cliente c set c.nome = ?2, c.dataNascimento = ?3, c.genero = ?4, c.cpf = ?5, c.email = ?6 where c.id = ?1")
    void alterarCadastroCliente(Integer id, String nome, LocalDate dataNascimento, String genero, String cpf, String email);

    @Modifying
    @Query("update Cliente c set c.senha = ?2 where c.id = ?1")
    void alterarSenhaCadastroCliente(Integer id, String senha);

    Cliente findByCpf(String cpf);

    Cliente findByEmail(String email);
}
