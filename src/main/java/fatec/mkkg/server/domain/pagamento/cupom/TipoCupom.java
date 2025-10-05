package fatec.mkkg.server.domain.pagamento.cupom;

import fatec.mkkg.server.domain.EntidadeDominio;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "tipos_cupom")
@Data
public class TipoCupom extends EntidadeDominio {

	@Id
	@SequenceGenerator(name = "tipos_cupom_seq_gen")
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "tpc_id")
	private Integer id;

	@Column(name = "tpc_tipo")
	private String tipo;

}
