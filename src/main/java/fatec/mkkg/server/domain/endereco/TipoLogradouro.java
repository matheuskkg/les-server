package fatec.mkkg.server.domain.endereco;

import fatec.mkkg.server.domain.EntidadeDominio;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tipos_logradouro")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TipoLogradouro extends EntidadeDominio {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "tpl_id")
	private Integer id;

	@Column(name = "tpl_tipo")
	private String tipo;

}