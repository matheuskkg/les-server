package fatec.mkkg.server.domain.livro;

import fatec.mkkg.server.domain.EntidadeDominio;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "publicacoes")
@Data
public class Publicacao extends EntidadeDominio {

	@Id
	@SequenceGenerator(name = "publicacoes_seq_gen")
	@GeneratedValue(strategy = jakarta.persistence.GenerationType.AUTO)
	@Column(name = "pub_id")
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "pub_lvb_id", referencedColumnName = "lvb_id")
	private LivroBase livro;

	@Column(name = "pub_isbn")
	private String isbn;

	@Column(name = "pub_codigo_barras")
	private String codigoBarras;

	@Column(name = "pub_numero_paginas")
	private Integer numeroPaginas;

	@Embedded
	@AttributeOverrides({ @AttributeOverride(name = "altura", column = @Column(name = "pub_altura")),
			@AttributeOverride(name = "largura", column = @Column(name = "pub_largura")),
			@AttributeOverride(name = "peso", column = @Column(name = "pub_peso")),
			@AttributeOverride(name = "profundidade", column = @Column(name = "pub_profundidade")) })
	private Medidas medidas;

	@Column(name = "pub_edicao")
	private String edicao;

	@ManyToOne
	@JoinColumn(name = "pub_edi_id", referencedColumnName = "edi_id")
	private Editora editora;

}
