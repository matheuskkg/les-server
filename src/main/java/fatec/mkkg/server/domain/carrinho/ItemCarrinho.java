package fatec.mkkg.server.domain.carrinho;

import fatec.mkkg.server.domain.EntidadeDominio;
import fatec.mkkg.server.domain.venda.Produto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "itens")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemCarrinho extends EntidadeDominio {

	@Id
	@SequenceGenerator(name = "itens_seq_gen")
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "its_id")
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "its_prd_id", referencedColumnName = "prd_id")
	private Produto produto;

	@Column(name = "its_quantidade")
	@Builder.Default
	private Integer quantidade = 0;

	@ManyToOne
	@JoinColumn(name = "its_car_id", referencedColumnName = "car_id")
	private Carrinho carrinho;

	// TODO: adicionar expiracao do item no carrinho e bloquear no estoque

}
