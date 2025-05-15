package mkkg.fatec.esiii.domain.endereco;

import jakarta.persistence.*;
import mkkg.fatec.esiii.domain.EntidadeDominio;

@Entity
@Table(name = "enderecos")
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

    public Endereco() {
    }

    public Endereco(Integer id, String nomeIdentificador, Cidade cidade, TipoLogradouro tipoLogradouro, String logradouro, TipoResidencia tipoResidencia, String numero, String bairro, String cep, String observacao, Boolean cobranca, Boolean entrega) {
        this.id = id;
        this.nomeIdentificador = nomeIdentificador;
        this.cidade = cidade;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNomeIdentificador() {
        return nomeIdentificador;
    }

    public void setNomeIdentificador(String nomeIdentificador) {
        this.nomeIdentificador = nomeIdentificador;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

    public TipoLogradouro getTipoLogradouro() {
        return tipoLogradouro;
    }

    public void setTipoLogradouro(TipoLogradouro tipoLogradouro) {
        this.tipoLogradouro = tipoLogradouro;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public TipoResidencia getTipoResidencia() {
        return tipoResidencia;
    }

    public void setTipoResidencia(TipoResidencia tipoResidencia) {
        this.tipoResidencia = tipoResidencia;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Boolean getCobranca() {
        return cobranca;
    }

    public void setCobranca(Boolean cobranca) {
        this.cobranca = cobranca;
    }

    public Boolean getEntrega() {
        return entrega;
    }

    public void setEntrega(Boolean entrega) {
        this.entrega = entrega;
    }
}
