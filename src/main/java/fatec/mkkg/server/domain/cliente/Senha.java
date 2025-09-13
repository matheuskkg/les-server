package fatec.mkkg.server.domain.cliente;

import fatec.mkkg.server.domain.EntidadeDominio;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "senhas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Senha extends EntidadeDominio {

	@Id
	@SequenceGenerator(
			name = "senhas_seq_gen"
	)
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "sen_id")
	private Integer id;

	@Column(name = "sen_senha")
	private String senha;

	@Transient
	private String senhaConfirmar;

	@Transient
	private Cliente cliente;

	public Senha(String senha) {
		this.senha = senha;
	}

}
