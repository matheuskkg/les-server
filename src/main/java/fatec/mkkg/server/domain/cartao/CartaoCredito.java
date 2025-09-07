package fatec.mkkg.server.domain.cartao;

import fatec.mkkg.server.domain.EntidadeDominio;
import fatec.mkkg.server.domain.cliente.Cliente;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "cartoes_credito")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartaoCredito extends EntidadeDominio {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ctc_id")
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "ctc_ban_id", referencedColumnName = "ban_id", nullable = false)
	private Bandeira bandeira;

	@Column(name = "ctc_nome_titutar", nullable = false)
	private String nomeTitular;

	@Column(name = "ctc_numero", nullable = false)
	private String numero;

	@Column(name = "ctc_codigo_seguranca", nullable = false)
	private String codigoSeguranca;

	@Column(name = "ctc_preferencial", nullable = false)
	private Boolean preferencial;

	@ManyToOne
	@JoinColumn(name = "ctc_cli_id", referencedColumnName = "cli_id", nullable = false)
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