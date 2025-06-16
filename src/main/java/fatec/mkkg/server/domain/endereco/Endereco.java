package fatec.mkkg.server.domain.endereco;

import fatec.mkkg.server.domain.EntidadeDominio;
import fatec.mkkg.server.domain.cliente.Cliente;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "enderecos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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

    //O body da request contém somente a cidade, caso a cidade/estado não seja no Brasil a informação do país é perdida
    //?Solução:
    //  1. Normalizar o restante das cidades/estados
    @ManyToOne
    @JoinColumn(name = "end_pai_id", referencedColumnName = "pai_id", nullable = false)
    private Pais pais;

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

    public Endereco(Integer id, Boolean cobranca, Boolean entrega, Cliente cliente) {
        this.id = id;
        this.cobranca = cobranca;
        this.entrega = entrega;
        this.cliente = cliente;
    }

    public Endereco(Integer id, String nomeIdentificador, Cidade cidade, Pais pais, TipoLogradouro tipoLogradouro, String logradouro, TipoResidencia tipoResidencia, String numero, String bairro, String cep, String observacao, Boolean cobranca, Boolean entrega) {
        this.id = id;
        this.nomeIdentificador = nomeIdentificador;
        this.cidade = cidade;
        this.pais = pais;
        this.tipoLogradouro = tipoLogradouro;
        this.logradouro = logradouro;
        this.tipoResidencia = tipoResidencia;
        this.numero = numero;
        this.bairro = bairro;
        this.cep = cep;
        this.observacao = observacao;
        this.cobranca = cobranca;
        this.entrega = entrega;
    }

    public Endereco(EntidadeDominio entidade) {
        Endereco endereco = (Endereco) entidade;

        this.id = endereco.getId();
    }
}