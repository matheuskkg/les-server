package fatec.mkkg.server.domain.livro;

import jakarta.persistence.*;
import lombok.Data;

import java.time.Year;
import java.util.List;

@Entity
@Table(name = "livros_base")
@Data
public class LivroBase {

	@Id
	@SequenceGenerator(name = "livros_base_seq_gen")
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "lvb_id")
	private Integer id;

	@Column(name = "lvb_titulo")
	private String titulo;

	@Column(name = "lvb_sinopse")
	private String sinopse;

	@Column(name = "lvb_ano")
	private Year ano;

	@ManyToMany
	@JoinTable(name = "autores_livros",
			joinColumns = @JoinColumn(name = "aub_livro_id", referencedColumnName = "lvb_id"),
			inverseJoinColumns = @JoinColumn(name = "aub_autor_id", referencedColumnName = "aut_id"))
	private List<Autor> autores;

	@ManyToMany
	@JoinTable(name = "categorias_livro",
			joinColumns = @JoinColumn(name = "ctb_livro_id", referencedColumnName = "lvb_id"),
			inverseJoinColumns = @JoinColumn(name = "ctb_categoria_id", referencedColumnName = "ctl_id"))
	private List<CategoriaLivro> categorias;

}
