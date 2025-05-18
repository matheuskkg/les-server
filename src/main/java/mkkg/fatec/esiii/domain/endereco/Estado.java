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
@Builder
public class Estado extends EntidadeDominio {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "est_id")
    private Integer id;

    @Column(name = "est_nome", nullable = false)
    private String nome;

    @Column(name = "est_uf", nullable = false, length = 2)
    private String uf;

    @ManyToOne
    @JoinColumn(name = "est_pai_id", referencedColumnName = "pai_id")
    private Pais pais;

}
