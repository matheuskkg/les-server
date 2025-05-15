package mkkg.fatec.esiii.domain.cartao;

import jakarta.persistence.*;
import mkkg.fatec.esiii.domain.EntidadeDominio;

@Entity
@Table(name = "bandeiras")
public class Bandeira extends EntidadeDominio {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ban_id")
    private Integer id;

    @Column(name = "ban_bandeira")
    private String bandeira;

    public Bandeira() {
    }

    public Bandeira(Integer id, String bandeira) {
        this.id = id;
        this.bandeira = bandeira;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBandeira() {
        return bandeira;
    }

    public void setBandeira(String bandeira) {
        this.bandeira = bandeira;
    }
}
