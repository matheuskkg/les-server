package fatec.mkkg.server.domain.pedido.troca;

import fatec.mkkg.server.domain.EntidadeDominio;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "status_pedidos_trocas")
@Data
public class StatusPedidoTroca extends EntidadeDominio {

	@Id
	@SequenceGenerator(name = "status_pedidos_trocas_seq_gen")
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "spt_id")
	private Integer id;

	@Column(name = "spt_status")
	private String status;

}
