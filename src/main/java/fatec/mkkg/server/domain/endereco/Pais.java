package fatec.mkkg.server.domain.endereco;


import fatec.mkkg.server.domain.EntidadeDominio;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "paises")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Pais extends EntidadeDominio {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "pai_id")
    private Integer id;

    @Column(name = "pai_nome", nullable = false)
    private String nome;

    public Pais(String nome) {
        this.nome = nome;
    }
}