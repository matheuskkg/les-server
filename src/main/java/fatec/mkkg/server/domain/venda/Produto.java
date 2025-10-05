package fatec.mkkg.server.domain.venda;

import fatec.mkkg.server.domain.EntidadeDominio;
import fatec.mkkg.server.domain.livro.Publicacao;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "produtos")
@Data
public class Produto extends EntidadeDominio {

	@Id
	@SequenceGenerator(name = "produtos_seq_gen")
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "prd_id")
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "prd_pub_id", referencedColumnName = "pub_id")
	private Publicacao publicacao;

	@Column(name = "prd_preco")
	private Integer preco;

}
