package mkkg.fatec.esiii.repositories;

import mkkg.fatec.esiii.domain.cliente.Cliente;
import mkkg.fatec.esiii.domain.cliente.ClienteResponseDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

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

    @Query("""
        select new mkkg.fatec.esiii.domain.cliente.ClienteResponseDTO(
            c.id,
            c.nome,
            c.dataNascimento,
            c.genero,
            c.cpf,
            c.email,
            new mkkg.fatec.esiii.domain.telefone.TelefoneResponseDTO(t.ddd, tp.tipo, t.numero)
        )
        from Cliente c
        join Telefone t on t.cliente.id = c.id
        join TipoTelefone tp on tp.id = t.tipoTelefone.id
        where
            (:nome is null or LOWER(c.nome) like LOWER(CONCAT('%', :nome, '%')))
            and (:cpf is null or c.cpf = :cpf)
            and (:email is null or LOWER(c.email) = LOWER(:email))
    """)
    List<ClienteResponseDTO> buscarComFiltro(
            @Param("nome") String nome,
            @Param("cpf") String cpf,
            @Param("email") String email
    );

    @Query("""
        select new mkkg.fatec.esiii.domain.cliente.ClienteResponseDTO(
            c.id,
            c.nome,
            c.dataNascimento,
            c.genero,
            c.cpf,
            c.email,
            new mkkg.fatec.esiii.domain.telefone.TelefoneResponseDTO(t.ddd, tp.tipo, t.numero)
        )
        from Cliente c
        join Telefone t on t.cliente.id = c.id
        join TipoTelefone tp on tp.id = t.tipoTelefone.id
    """)
    List<ClienteResponseDTO> buscarTodos();
}
