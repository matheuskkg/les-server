package fatec.mkkg.server.domain.cartao;

import fatec.mkkg.server.domain.EntidadeDominio;
import jakarta.persistence.*;
import lombok.*;

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