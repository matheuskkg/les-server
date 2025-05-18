package mkkg.fatec.esiii.domain.cartao;

import jakarta.persistence.*;
import lombok.*;
import mkkg.fatec.esiii.domain.EntidadeDominio;

@Entity
@Table(name = "cartoes_credito")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartaoCredito extends EntidadeDominio {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ctc_id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "ctc_ban_id", referencedColumnName = "ban_id", nullable = false)
    private Bandeira bandeira;

    @Column(name = "ctc_nome_titutar", nullable = false)
    private String nomeTitular;

    @Column(name = "ctc_numero", nullable = false)
    private String numero;

    @Column(name = "ctc_codigo_seguranca", nullable = false)
    private String codigoSeguranca;

    @Column(name = "ctc_preferencial", nullable = false)
    private Boolean preferencial;

    public CartaoCredito() {
    }

    public CartaoCredito(Integer id, Bandeira bandeira, String nomeTitular, String numero, String codigoSeguranca, Boolean preferencial) {
        this.id = id;
        this.bandeira = bandeira;
        this.nomeTitular = nomeTitular;
        this.numero = numero;
        this.codigoSeguranca = codigoSeguranca;
        this.preferencial = preferencial;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Bandeira getBandeira() {
        return bandeira;
    }

    public void setBandeira(Bandeira bandeira) {
        this.bandeira = bandeira;
    }

    public String getNomeTitular() {
        return nomeTitular;
    }

    public void setNomeTitular(String nomeTitular) {
        this.nomeTitular = nomeTitular;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getCodigoSeguranca() {
        return codigoSeguranca;
    }

}
