package mkkg.fatec.esiii.domain.cliente;

import jakarta.persistence.*;
import lombok.*;
import mkkg.fatec.esiii.domain.EntidadeDominio;
import mkkg.fatec.esiii.domain.cartao.CartaoCredito;
import mkkg.fatec.esiii.domain.endereco.Endereco;
import mkkg.fatec.esiii.domain.telefone.Telefone;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "clientes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cliente extends EntidadeDominio {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "cli_id")
    private Integer id;

    @Column(name = "cli_nome", nullable = false)
    private String nome;

    @Column(name = "cli_data_nascimento")
    private LocalDate dataNascimento;

    @Column(name = "cli_genero", nullable = false, length = 1)
    private String genero;

    @Column(name = "cli_cpf", nullable = false)
    private String cpf;

    @Column(name = "cli_email", nullable = false)
    private String email;

    @Column(name = "cli_senha", nullable = false)
    private String senha;

    @Transient
    private String senhaConfirmar;

    @Column(name = "cli_cadastro_ativo", nullable = false)
    private Boolean cadastroAtivo;

    @OneToMany
    @JoinColumn(name = "tel_cli_id", referencedColumnName = "cli_id", nullable = false)
    private List<Telefone> telefones;

    @OneToMany
    @JoinColumn(name = "ctc_cli_id", referencedColumnName = "cli_id", nullable = false)
    private List<CartaoCredito> cartoesCredito;

    @OneToMany
    @JoinColumn(name = "end_cli_id", referencedColumnName = "cli_id", nullable = false)
    private List<Endereco> enderecos;

}
