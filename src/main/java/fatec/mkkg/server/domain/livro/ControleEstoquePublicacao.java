package fatec.mkkg.server.domain.livro;

import fatec.mkkg.server.domain.EntidadeDominio;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "estoque_publicacoes")
@Data
public class ControleEstoquePublicacao extends EntidadeDominio {

	@Id
	@SequenceGenerator(name = "estoque_publicacoes_seq_gen")
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "esp_id")
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "esp_pub_id", referencedColumnName = "pub_id")
	private Publicacao publicacao;

	@Column(name = "esp_quantidade")
	private Integer quantidade;

}
