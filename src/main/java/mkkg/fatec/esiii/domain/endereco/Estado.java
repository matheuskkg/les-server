package mkkg.fatec.esiii.domain.endereco;

import jakarta.persistence.*;
import mkkg.fatec.esiii.domain.EntidadeDominio;

@Entity
@Table(name = "estados")
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

    public Estado() {
    }

    public Estado(Integer id, String nome, Pais pais) {
        this.id = id;
        this.nome = nome;
        this.pais = pais;
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

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }
}
