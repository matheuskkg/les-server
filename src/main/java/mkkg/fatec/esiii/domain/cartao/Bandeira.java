package mkkg.fatec.esiii.domain.cartao;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mkkg.fatec.esiii.domain.EntidadeDominio;

@Entity
@Table(name = "bandeiras")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Bandeira extends EntidadeDominio {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ban_id")
    private Integer id;

    @Column(name = "ban_bandeira")
    private String bandeira;

}
