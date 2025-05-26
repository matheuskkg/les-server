package mkkg.fatec.esiii.domain.endereco;

import jakarta.persistence.*;
import lombok.*;
import mkkg.fatec.esiii.domain.EntidadeDominio;
import mkkg.fatec.esiii.domain.cliente.Cliente;

@Entity
@Table(name = "enderecos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Endereco extends EntidadeDominio {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "end_id")
    private Integer id;

    @Column(name = "end_nome_identificador", nullable = false)
    private String nomeIdentificador;

    @ManyToOne
    @JoinColumn(name = "end_cid_id", referencedColumnName = "cid_id", nullable = false)
    private Cidade cidade;

    @ManyToOne
    @JoinColumn(name = "end_tpl_id", referencedColumnName = "tpl_id", nullable = false)
    private TipoLogradouro tipoLogradouro;

    @Column(name = "end_logradouro", nullable = false)
    private String logradouro;

    @ManyToOne
    @JoinColumn(name = "end_tpr_id", referencedColumnName = "tpr_id", nullable = false)
    private TipoResidencia tipoResidencia;

    @Column(name = "end_numero", nullable = false)
    private String numero;

    @Column(name = "end_bairro", nullable = false)
    private String bairro;

    @Column(name = "end_cep", nullable = false)
    private String cep;

    @Column(name = "end_observacao")
    private String observacao;

    @Column(name = "end_cobranca", nullable = false)
    private Boolean cobranca;

    @Column(name = "end_entrega", nullable = false)
    private Boolean entrega;

    @ManyToOne
    @JoinColumn(name = "end_cli_id", referencedColumnName = "cli_id", nullable = false)
    private Cliente cliente;

    public Endereco(Integer id) {
        this.id = id;
    }
}
