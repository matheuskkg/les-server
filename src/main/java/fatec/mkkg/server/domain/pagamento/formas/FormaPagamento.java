package fatec.mkkg.server.domain.pagamento.formas;

import fatec.mkkg.server.domain.EntidadeDominio;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "formas_pagamento")
@Inheritance(strategy = InheritanceType.JOINED)
@Data
public class FormaPagamento extends EntidadeDominio {

	@Id
	@SequenceGenerator(name = "formas_pagamento_seq_gen")
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "fpg_id")
	protected Integer id;

}
