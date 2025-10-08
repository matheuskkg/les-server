package fatec.mkkg.server.strategies.pagamento.helpers;

import fatec.mkkg.server.domain.pagamento.formas.cupom.Cupom;
import fatec.mkkg.server.domain.pagamento.formas.cupom.TipoCupom;

import java.util.List;

public class ValidarMaximoCupomPromocional {

	public static List<String> processar(List<Cupom> cupons) {
		long count = cupons.stream().filter(c -> c.getTipo().equals(TipoCupom.PROMOCIONAL)).count();

		if (count > 1) {
			return List.of("Apenas um cupom promocional pode ser utilizado por compra");
		}

		return List.of();
	}

}
