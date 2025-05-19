package mkkg.fatec.esiii.domain.telefone;

import jakarta.persistence.*;
import lombok.*;
import mkkg.fatec.esiii.domain.EntidadeDominio;
import mkkg.fatec.esiii.domain.cliente.Cliente;

@Entity
@Table(name = "telefones")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Telefone extends EntidadeDominio {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "tel_id")
    private Integer id;

    @Column(name = "tel_ddd", nullable = false)
    private String ddd;

    @ManyToOne
    @JoinColumn(name = "tel_tpt_id", referencedColumnName = "tpt_id", nullable = false)
    private TipoTelefone tipoTelefone;

    @Column(name = "tel_numero", nullable = false)
    private String numero;

    @OneToOne
    @JoinColumn(name = "tel_cli_id", referencedColumnName = "cli_id", nullable = false)
    private Cliente cliente;
}
