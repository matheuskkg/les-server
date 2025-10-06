package fatec.mkkg.server.domain.pedido.troca;

import fatec.mkkg.server.domain.EntidadeDominio;
import fatec.mkkg.server.domain.pedido.ItemPedido;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "pedidos_trocas")
@Data
public class PedidoTroca extends EntidadeDominio {

	@Id
	@SequenceGenerator(name = "pedidos_trocas_seq_gen")
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "pdt_id")
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "pdt_spt_id", referencedColumnName = "spt_id")
	private StatusPedidoTroca status;

	@OneToOne
	@JoinColumn(name = "pdt_itp_id", referencedColumnName = "itp_id")
	private ItemPedido itemATrocar;

	@Column(name = "pdt_quantidade_troca")
	private Integer quantidadeATrocar;

}
