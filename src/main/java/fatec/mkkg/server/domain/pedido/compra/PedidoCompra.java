package fatec.mkkg.server.domain.pedido.compra;

import fatec.mkkg.server.domain.EntidadeDominio;
import fatec.mkkg.server.domain.carrinho.Carrinho;
import fatec.mkkg.server.domain.pedido.ItemPedido;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "pedidos_compras")
@Data
public class PedidoCompra extends EntidadeDominio {

	@Id
	@SequenceGenerator(name = "pedidos_compra_seq_gen")
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "pdc_id")
	private Integer id;

	@OneToOne
	@JoinColumn(name = "pdc_car_id", referencedColumnName = "car_id")
	private Carrinho carrinho;

	@OneToMany(mappedBy = "pedidoCompra")
	private List<ItemPedido> itens;

}
