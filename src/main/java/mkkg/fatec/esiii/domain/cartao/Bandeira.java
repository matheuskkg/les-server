package mkkg.fatec.esiii.domain.cartao;

import jakarta.persistence.*;
import lombok.*;
import mkkg.fatec.esiii.domain.EntidadeDominio;

@Entity
@Table(name = "bandeiras")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Bandeira extends EntidadeDominio {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ban_id")
    private Integer id;

    @Column(name = "ban_bandeira")
    private String bandeira;

}
