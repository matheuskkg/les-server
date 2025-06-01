package fatec.mkkg.server.domain.endereco;

import fatec.mkkg.server.domain.EntidadeDominio;
import jakarta.persistence.*;
import lombok.*;

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

    @Column(name = "est_uf", nullable = false, length = 2)
    private String uf;

    @ManyToOne
    @JoinColumn(name = "est_pai_id", referencedColumnName = "pai_id")
    private Pais pais;

    public Estado(String nome) {
        this.nome = nome;
    }

    public Estado(Pais pais) {
        this.pais = pais;
    }

    public Estado(Integer id, String nome, String uf) {
        this.id = id;
        this.nome = nome;
        this.uf = uf;
    }
}