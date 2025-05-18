package mkkg.fatec.esiii.domain.endereco;

import jakarta.persistence.*;
import lombok.*;
import mkkg.fatec.esiii.domain.EntidadeDominio;

@Entity
@Table(name = "tipos_residencia")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TipoResidencia extends EntidadeDominio {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "tpr_id")
    private Integer id;

    @Column(name = "tpr_tipo", nullable = false)
    private String tipo;

}
