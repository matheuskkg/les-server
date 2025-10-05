package fatec.mkkg.server.domain.pagamento;

import fatec.mkkg.server.domain.pagamento.formas.FormaPagamento;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "pagamentos")
@Data
public class Pagamento {

	@Id
	@SequenceGenerator(name = "pagamentos_seq_gen")
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "pag_id")
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "pag_fpg_id", referencedColumnName = "fpg_id")
	private FormaPagamento formaPagamento;

	@Column(name = "pag_porcentagem")
	private Double porcentagem;

}
