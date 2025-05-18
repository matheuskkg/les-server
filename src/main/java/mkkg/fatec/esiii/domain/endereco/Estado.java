package mkkg.fatec.esiii.domain.endereco;

import jakarta.persistence.*;
import lombok.*;
import mkkg.fatec.esiii.domain.EntidadeDominio;

@Entity
@Table(name = "estados")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Estado extends EntidadeDominio {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "est_id")
    private Integer id;

    @Column(name = "est_nome", nullable = false)
    private String nome;

    @ManyToOne
    @JoinColumn(name = "est_pai_id", referencedColumnName = "pai_id", nullable = false)
    private Pais pais;

}
