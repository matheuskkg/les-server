package fatec.mkkg.server.domain.cliente;

import fatec.mkkg.server.domain.EntidadeDominio;
import fatec.mkkg.server.domain.endereco.Endereco;
import fatec.mkkg.server.domain.telefone.Telefone;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(
        name = "clientes",
        uniqueConstraints = {
                @UniqueConstraint(name = "UNIQUE_CPF", columnNames = "cli_cpf"),
                @UniqueConstraint(name = "UNIQUE_EMAIL", columnNames = "cli_email")
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cliente extends EntidadeDominio {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "cli_id")
    private Integer id;

    @Column(name = "cli_nome", nullable = false)
    private String nome;

    @Column(name = "cli_data_nascimento", nullable = false)
    private LocalDate dataNascimento;

    @Column(name = "cli_genero", nullable = false)
    private String genero;

    @Column(name = "cli_cpf", nullable = false, unique = true)
    private String cpf;

    @Column(name = "cli_email", nullable = false, unique = true)
    private String email;

    @Column(name = "cli_senha", nullable = false)
    private String senha;

    @Transient
    private String senhaConfirmar;

    @Column(name = "cli_cadastro_ativo", nullable = false)
    private Boolean cadastroAtivo;

    @Transient
    private Telefone telefone;

    @Transient
    private Endereco endereco;

    public Cliente(Integer id) {
        this.id = id;
    }

    public Cliente(Integer id, String nome, LocalDate dataNascimento, String genero, String cpf, String email, Telefone telefone) {
        this.id = id;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.genero = genero;
        this.cpf = cpf;
        this.email = email;
        this.telefone = telefone;
    }
}
