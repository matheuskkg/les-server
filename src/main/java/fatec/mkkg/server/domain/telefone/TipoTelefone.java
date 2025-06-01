package fatec.mkkg.server.domain.telefone;

import fatec.mkkg.server.domain.EntidadeDominio;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tipos_telefone")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TipoTelefone extends EntidadeDominio {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "tpt_id")
    private Integer id;

    @Column(name = "tpt_tipo", nullable = false)
    private String tipo;

}