package fatec.mkkg.server.domain.pedido;

import fatec.mkkg.server.domain.EntidadeDominio;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "status_pedidos")
@Data
public class StatusPedido extends EntidadeDominio {

	@Id
	@SequenceGenerator(name = "status_pedidos_seq_gen")
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "stp_id")
	private Integer id;

	@Column(name = "stp_status")
	private String status;

}
