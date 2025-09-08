package fatec.mkkg.server.strategies.telefone;

import fatec.mkkg.server.domain.EntidadeDominio;
import fatec.mkkg.server.domain.cliente.Cliente;
import fatec.mkkg.server.domain.telefone.Telefone;
import fatec.mkkg.server.strategies.IStrategy;
import fatec.mkkg.server.util.Validacao;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ValidarCamposTelefone implements IStrategy {

	private String validarDdd(String ddd) {
		if (ddd == null || ddd.isBlank())
			return "O DDD é obrigatório.";

		if (!ddd.matches("\\d{2}"))
			return "O DDD deve conter exatamente 2 dígitos.";

		return "";
	}

	private String validarNumero(String numero) {
		if (numero == null || numero.isBlank())
			return "O número de telefone é obrigatório.";

		if (!numero.matches("\\d{8,9}"))
			return "O número de telefone deve conter 8 ou 9 dígitos.";

		return "";
	}

	@Override
	public List<String> processar(EntidadeDominio entidade) {
		Telefone telefone = ((Cliente) entidade).getTelefone();

		List<String> res = new ArrayList<>();

		Validacao.adicionarErro(res, validarDdd(telefone.getDdd()));
		Validacao.adicionarErro(res, validarNumero(telefone.getNumero()));

		return res;
	}

}
