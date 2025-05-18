package mkkg.fatec.esiii.domain.telefone;

import jakarta.persistence.*;
import lombok.*;
import mkkg.fatec.esiii.domain.EntidadeDominio;

@Entity
@Table(name = "tipos_telefone")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TipoTelefone extends EntidadeDominio {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "tpt_id")
    private Integer id;

    @Column(name = "tpt_tipo", nullable = false)
    private String tipo;

}
