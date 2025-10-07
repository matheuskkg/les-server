package fatec.mkkg.server.domain.pedido;

import fatec.mkkg.server.domain.EntidadeDominio;
import fatec.mkkg.server.domain.pedido.compra.PedidoCompra;
import fatec.mkkg.server.domain.produto.Produto;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "itens_pedido")
@Data
public class ItemPedido extends EntidadeDominio {

	@Id
	@SequenceGenerator(name = "itens_pedido_seq_gen")
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "itp_id")
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "itp_prd_id", referencedColumnName = "prd_id")
	private Produto produto;

	@Column(name = "itp_quantidade")
	private Integer quantidade;

	@Column(name = "itp_valor_unitario_produto")
	private Integer valorUnitario;

	@ManyToOne
	@JoinColumn(name = "itp_pdc_id", referencedColumnName = "pdc_id")
	private PedidoCompra pedidoCompra;

}
