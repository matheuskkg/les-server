package mkkg.fatec.esiii.domain.telefone;

import jakarta.persistence.*;
import mkkg.fatec.esiii.domain.EntidadeDominio;

@Entity
@Table(name = "tipos_telefone")
public class TipoTelefone extends EntidadeDominio {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "tpt_id")
    private Integer id;

    @Column(name = "tpt_tipo", nullable = false)
    private String tipo;

    public TipoTelefone() {
    }

    public TipoTelefone(Integer id, String tipo) {
        this.id = id;
        this.tipo = tipo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
