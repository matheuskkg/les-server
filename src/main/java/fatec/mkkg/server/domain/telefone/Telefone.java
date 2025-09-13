package fatec.mkkg.server.domain.telefone;

import fatec.mkkg.server.domain.EntidadeDominio;
import fatec.mkkg.server.domain.cliente.Cliente;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "telefones")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Telefone extends EntidadeDominio {

	@Id
	@SequenceGenerator(
			name = "telefones_seq_gen"
	)
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "tel_id")
	private Integer id;

	@Column(name = "tel_ddd", nullable = false)
	private String ddd;

	@ManyToOne
	@JoinColumn(name = "tel_tpt_id", referencedColumnName = "tpt_id", nullable = false)
	private TipoTelefone tipoTelefone;

	@Column(name = "tel_numero", nullable = false)
	private String numero;

	@OneToOne
	@JoinColumn(name = "tel_cli_id", referencedColumnName = "cli_id", nullable = false)
	private Cliente cliente;

	public Telefone(Integer id, String ddd, TipoTelefone tipoTelefone, String numero) {
		this.id = id;
		this.ddd = ddd;
		this.tipoTelefone = tipoTelefone;
		this.numero = numero;
	}

}