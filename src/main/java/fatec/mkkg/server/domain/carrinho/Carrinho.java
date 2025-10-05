package fatec.mkkg.server.domain.carrinho;

import fatec.mkkg.server.domain.EntidadeDominio;
import fatec.mkkg.server.domain.cliente.Cliente;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "carrinhos")
@Data
public class Carrinho extends EntidadeDominio {

	@Id
	@SequenceGenerator(name = "carrinhos_seq_gen")
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "car_id")
	private Integer id;

	@OneToOne
	@JoinColumn(name = "car_cli_id", referencedColumnName = "cli_id")
	private Cliente cliente;

	@OneToMany(mappedBy = "carrinho")
	private List<ItemCarrinho> itens;

}
