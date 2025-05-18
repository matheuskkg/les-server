package mkkg.fatec.esiii.domain.endereco;

import jakarta.persistence.*;
import lombok.*;
import mkkg.fatec.esiii.domain.EntidadeDominio;

@Entity
@Table(name = "tipos_logradouro")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TipoLogradouro extends EntidadeDominio {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "tpl_id")
    private Integer id;

    @Column(name = "tpl_tipo", nullable = false)
    private String tipo;

}
