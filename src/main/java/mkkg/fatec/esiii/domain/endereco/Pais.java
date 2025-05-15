package mkkg.fatec.esiii.domain.endereco;

import jakarta.persistence.*;
import mkkg.fatec.esiii.domain.EntidadeDominio;

@Entity
@Table(name = "paises")
public class Pais extends EntidadeDominio {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "pai_id")
    private Integer id;

    @Column(name = "pai_nome", nullable = false)
    private String nome;

    public Pais() {
    }

    public Pais(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
