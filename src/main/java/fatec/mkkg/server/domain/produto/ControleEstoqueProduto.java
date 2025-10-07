package fatec.mkkg.server.domain.produto;

import fatec.mkkg.server.domain.EntidadeDominio;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "controle_estoque_produtos")
@Data
public class ControleEstoqueProduto extends EntidadeDominio {

	@Id
	@SequenceGenerator(name = "controle_estoque_produtos_seq_gen")
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "esp_id")
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "esp_prd_id", referencedColumnName = "prd_id")
	private Produto produto;

	@Column(name = "esp_quantidade")
	private Integer quantidade;

}
