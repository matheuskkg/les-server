package fatec.mkkg.server.domain.cliente;

import fatec.mkkg.server.domain.EntidadeDominio;
import fatec.mkkg.server.domain.endereco.Endereco;
import fatec.mkkg.server.domain.telefone.Telefone;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "clientes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cliente extends EntidadeDominio {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "cli_id")
	private Integer id;

	@Column(name = "cli_nome")
	private String nome;

	@Column(name = "cli_data_nascimento")
	private LocalDate dataNascimento;

	@Column(name = "cli_genero")
	private String genero;

	@Column(name = "cli_cpf")
	private String cpf;

	@Column(name = "cli_email")
	private String email;

	@OneToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST })
	@JoinColumn(name = "cli_sen_id", referencedColumnName = "sen_id")
	private Senha senha;

	@Column(name = "cli_cadastro_ativo")
	private Boolean cadastroAtivo;

	@Transient
	private Telefone telefone;

	@Transient
	private Endereco endereco;

	public Cliente(Integer id) {
		this.id = id;
	}

	public Cliente(Integer id, String nome, LocalDate dataNascimento, String genero, String cpf, String email,
			Telefone telefone) {
		this.id = id;
		this.nome = nome;
		this.dataNascimento = dataNascimento;
		this.genero = genero;
		this.cpf = cpf;
		this.email = email;
		this.telefone = telefone;
	}

	public Cliente(Integer id, String nome, String email) {
		this.id = id;
		this.nome = nome;
		this.email = email;
	}

}
