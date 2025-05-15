package mkkg.fatec.esiii.domain.endereco;

import jakarta.persistence.*;
import mkkg.fatec.esiii.domain.EntidadeDominio;

@Entity
@Table(name = "tipos_logradouro")
public class TipoLogradouro extends EntidadeDominio {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "tpl_id")
    private Integer id;

    @Column(name = "tpl_tipo", nullable = false)
    private String tipo;

    public TipoLogradouro() {
    }

    public TipoLogradouro(Integer id, String tipo) {
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
