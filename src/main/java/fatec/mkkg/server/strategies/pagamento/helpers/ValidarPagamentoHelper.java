package fatec.mkkg.server.strategies.pagamento.helpers;

import fatec.mkkg.server.domain.pagamento.formas.cupom.Cupom;
import fatec.mkkg.server.repositories.CupomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidarPagamentoHelper {

	@Autowired
	private CupomRepository repository;

	public Integer obterValorCupom(Cupom cupom) {
		return repository.obterValorDoCupom(cupom.getId(), cupom.getCliente().getId())
				.orElseThrow(() -> new RuntimeException("Cupom de ID " + cupom.getId() + " não encontrado"));
	}

	public Double obterPorcentagemCobertaPeloCupom(Integer valorCupom, Integer valorTotalCompra) {
		return (valorCupom.doubleValue() / valorTotalCompra.doubleValue()) * 100;
	}

	public boolean validarPorcentagemPositiva(Double porcentagem) {
		return porcentagem != null && porcentagem > 0.0;
	}

}
