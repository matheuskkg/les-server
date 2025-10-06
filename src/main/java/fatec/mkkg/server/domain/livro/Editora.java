package fatec.mkkg.server.domain.livro;

import fatec.mkkg.server.domain.EntidadeDominio;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "editoras")
@Data
public class Editora extends EntidadeDominio {

	@Id
	@SequenceGenerator(name = "editoras_seq_gen")
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "edi_id")
	private Integer id;

	@Column(name = "edi_nome")
	private String nome;

}
