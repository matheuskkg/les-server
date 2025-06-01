package fatec.mkkg.server.domain.endereco;

import fatec.mkkg.server.domain.EntidadeDominio;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "cidades")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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

    public Cidade(Estado estado) {
        this.estado = estado;
    }

    public Cidade(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
    }
}