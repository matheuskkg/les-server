package fatec.mkkg.server.domain.livro;

import fatec.mkkg.server.domain.EntidadeDominio;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "autores")
@Data
public class Autor extends EntidadeDominio {

	@Id
	@SequenceGenerator(name = "autores_seq_gen")
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "aut_id")
	private Integer id;

	@Column(name = "aut_nome")
	private String nome;

}
