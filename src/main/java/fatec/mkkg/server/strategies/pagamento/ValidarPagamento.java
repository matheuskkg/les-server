package fatec.mkkg.server.strategies.pagamento;

import fatec.mkkg.server.domain.EntidadeDominio;
import fatec.mkkg.server.domain.pagamento.Pagamento;
import fatec.mkkg.server.domain.pagamento.formas.FormaPagamento;
import fatec.mkkg.server.domain.pagamento.formas.cartao.CartaoCredito;
import fatec.mkkg.server.domain.pagamento.formas.cupom.Cupom;
import fatec.mkkg.server.strategies.IStrategy;
import fatec.mkkg.server.strategies.pagamento.helpers.ValidarMaximoCupomPromocional;
import fatec.mkkg.server.strategies.pagamento.helpers.ValidarPagamentoHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class ValidarPagamento implements IStrategy {

	@Autowired
	private ValidarPagamentoHelper helper;

	@Override
	public List<String> processar(EntidadeDominio entidade) {
		Pagamento pagamento = (Pagamento) entidade;
		List<Cupom> cupons = new ArrayList<>();
		List<CartaoCredito> cartoes = new ArrayList<>();

		Integer valorTotalCompra = pagamento.getValorTotal();
		double somaPorcentagem = 0.0;

		List<String> res = new ArrayList<>();

		Map<FormaPagamento, Double> divisaoFormasPagamento = pagamento.getDivisaoFormasPagamento();
		for (Map.Entry<FormaPagamento, Double> entry : divisaoFormasPagamento.entrySet()) {
			FormaPagamento formaPagamento = entry.getKey();
			double porcentagem = entry.getValue();

			if (formaPagamento instanceof CartaoCredito cartao) {
				boolean porcentagemPositiva = helper.validarPorcentagemPositiva(porcentagem);
				if (!porcentagemPositiva) {
					continue;
				}

				cartoes.add(cartao);
			}

			if (formaPagamento instanceof Cupom cupom) {
				Integer valorCupom = helper.obterValorCupom(cupom);
				porcentagem += helper.obterPorcentagemCobertaPeloCupom(valorCupom, valorTotalCompra);
				cupons.add(cupom);
			}

			somaPorcentagem += porcentagem;
		}

		if (somaPorcentagem < 100.0) {
			res.add("Pagamento insuficiente");
		}

		res.addAll(ValidarMaximoCupomPromocional.processar(cupons));

		return res;
	}

}
