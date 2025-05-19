package mkkg.fatec.esiii.domain.endereco;

import jakarta.persistence.*;
import lombok.*;
import mkkg.fatec.esiii.domain.EntidadeDominio;

@Entity
@Table(name = "cidades")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cidade extends EntidadeDominio {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "cid_id")
    private Integer id;

    @Column(name = "cid_nome", nullable = false)
    private String nome;

    @ManyToOne
    @JoinColumn(name = "cid_est_id", referencedColumnName = "est_id", nullable = false)
    private Estado estado;

    public Cidade(Integer id) {
        this.id = id;
    }
}
