package mkkg.fatec.esiii.domain.cartao;

import jakarta.persistence.*;
import lombok.*;
import mkkg.fatec.esiii.domain.EntidadeDominio;
import mkkg.fatec.esiii.domain.cliente.Cliente;

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

    @ManyToOne
    @JoinColumn(name = "ctc_cli_id", referencedColumnName = "cli_id", nullable = false)
    private Cliente cliente;

}
