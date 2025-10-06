package fatec.mkkg.server.domain.pedido.compra;

import fatec.mkkg.server.domain.EntidadeDominio;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "status_pedidos_compras")
@Data
public class StatusPedidoCompra extends EntidadeDominio {

	@Id
	@SequenceGenerator(name = "status_pedidos_compras_seq_gen")
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "spc_id")
	private Integer id;

	@Column(name = "spc_status")
	private String status;

}
