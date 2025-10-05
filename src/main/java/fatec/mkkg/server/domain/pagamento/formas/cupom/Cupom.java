package fatec.mkkg.server.domain.pagamento.formas.cupom;

import fatec.mkkg.server.domain.cliente.Cliente;
import fatec.mkkg.server.domain.pagamento.formas.FormaPagamento;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "cupons")
@Data
public class Cupom extends FormaPagamento {

	@Id
	@SequenceGenerator(name = "cupons_seq_gen")
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "cps_id")
	private Integer id;

	@Column(name = "cps_codigo")
	private String codigo;

	@Column(name = "cps_valor")
	private Integer valor;

	@ManyToOne
	@JoinColumn(name = "cps_cli_id", referencedColumnName = "cli_id")
	private Cliente cliente;

	@ManyToOne
	@JoinColumn(name = "cps_tcp_id", referencedColumnName = "id")
	private TipoCupom tipo;

}
