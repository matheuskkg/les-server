package fatec.mkkg.server.util;

import java.util.List;

public class Validacao {

	public static String validar(Object input, String nomeCampo) {
		if (input == null || input.toString().isBlank()) {
			return nomeCampo;
		}

		return "";
	}

	public static void adicionarErro(List<String> erros, String erro) {
		if (erro != null && !erro.isBlank()) {
			erros.add(erro);
		}
	}

}
