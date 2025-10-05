package fatec.mkkg.server.domain.pagamento.formas.cupom;

import fatec.mkkg.server.domain.cliente.Cliente;
import fatec.mkkg.server.domain.pagamento.formas.FormaPagamento;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "cupons")
@Data
@PrimaryKeyJoinColumn(name = "cps_id", referencedColumnName = "fpg_id")
public class Cupom extends FormaPagamento {

	@Column(name = "cps_codigo")
	private String codigo;

	@Column(name = "cps_valor")
	private Integer valor;

	@ManyToOne
	@JoinColumn(name = "cps_cli_id", referencedColumnName = "cli_id")
	private Cliente cliente;

	@ManyToOne
	@JoinColumn(name = "cps_tpc_id", referencedColumnName = "tpc_id")
	private TipoCupom tipo;

}
