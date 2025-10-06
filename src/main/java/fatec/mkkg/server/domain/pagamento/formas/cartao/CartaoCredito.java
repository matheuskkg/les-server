package fatec.mkkg.server.domain.pagamento.formas.cartao;

import fatec.mkkg.server.domain.cliente.Cliente;
import fatec.mkkg.server.domain.pagamento.formas.FormaPagamento;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "cartoes_credito")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@PrimaryKeyJoinColumn(name = "ctc_id", referencedColumnName = "fpg_id")
public class CartaoCredito extends FormaPagamento {

	@ManyToOne
	@JoinColumn(name = "ctc_ban_id", referencedColumnName = "ban_id")
	private Bandeira bandeira;

	@Column(name = "ctc_nome_titular")
	private String nomeTitular;

	@Column(name = "ctc_numero")
	private String numero;

	@Column(name = "ctc_codigo_seguranca")
	private String codigoSeguranca;

	@Column(name = "ctc_preferencial")
	private Boolean preferencial;

	@ManyToOne
	@JoinColumn(name = "ctc_cli_id", referencedColumnName = "cli_id")
	private Cliente cliente;

	public CartaoCredito(Integer id) {
		this.id = id;
	}

	public CartaoCredito(Integer id, Bandeira bandeira, String nomeTitular, String numero, String codigoSeguranca,
			Boolean preferencial) {
		this.id = id;
		this.bandeira = bandeira;
		this.nomeTitular = nomeTitular;
		this.numero = numero;
		this.codigoSeguranca = codigoSeguranca;
		this.preferencial = preferencial;
	}

}