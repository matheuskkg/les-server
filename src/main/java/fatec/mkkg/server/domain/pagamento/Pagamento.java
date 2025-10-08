package fatec.mkkg.server.domain.pagamento;

import fatec.mkkg.server.domain.EntidadeDominio;
import fatec.mkkg.server.domain.pagamento.formas.FormaPagamento;
import jakarta.persistence.*;
import lombok.*;

import java.util.Map;

@Entity
@Table(name = "pagamentos")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pagamento extends EntidadeDominio {

	@Id
	@SequenceGenerator(name = "pagamentos_seq_gen")
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "pag_id")
	private Integer id;

	@Column(name = "pag_valor_total")
	private Integer valorTotal;

	@ElementCollection
	@CollectionTable(name = "divisoes_formas_pagamento",
			joinColumns = @JoinColumn(name = "dfp_pag_id", referencedColumnName = "pag_id"))
	@MapKeyJoinColumn(name = "dfp_fpg_id", referencedColumnName = "fpg_id")
	@Column(name = "dfp_porcentagem")
	private Map<FormaPagamento, Double> divisaoFormasPagamento;

}
