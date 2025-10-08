package fatec.mkkg.server.strategies.pagamento.helpers;

import java.util.List;

public class ValidarPorcentagemSuficiente {

	public static List<String> processar(Double porcentagem) {
		if (porcentagem < 100.0) {
			return List.of("Pagamento insuficiente");
		}

		return List.of();
	}

}
