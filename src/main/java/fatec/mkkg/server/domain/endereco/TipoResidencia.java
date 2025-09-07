package fatec.mkkg.server.domain.endereco;

import fatec.mkkg.server.domain.EntidadeDominio;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tipos_residencia")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TipoResidencia extends EntidadeDominio {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "tpr_id")
	private Integer id;

	@Column(name = "tpr_tipo", nullable = false)
	private String tipo;

}