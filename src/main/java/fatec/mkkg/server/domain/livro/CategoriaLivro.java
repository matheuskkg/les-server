package fatec.mkkg.server.domain.livro;

import fatec.mkkg.server.domain.EntidadeDominio;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "categorias_livros")
@Data
public class CategoriaLivro extends EntidadeDominio {

	@Id
	@SequenceGenerator(name = "categorias_livros_seq_gen")
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ctl_id")
	private Integer id;

	@Column(name = "ctl_nome")
	private String nome;

}
