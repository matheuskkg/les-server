package fatec.mkkg.server.strategies.cartao;

import fatec.mkkg.server.domain.EntidadeDominio;
import fatec.mkkg.server.domain.cartao.CartaoCredito;
import fatec.mkkg.server.strategies.IStrategy;
import fatec.mkkg.server.util.Validacao;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ValidarCamposCartaoCredito implements IStrategy {

	private String validarNomeTitular(String nomeTitular) {
		if (nomeTitular == null || nomeTitular.isBlank())
			return "O nome do titular é obrigatório.";

		if (nomeTitular.length() > 100)
			return "O nome do titular não pode exceder 100 caracteres.";

		return "";
	}

	private String validarNumero(String numero) {
		if (numero == null || numero.isBlank())
			return "O número do cartão é obrigatório.";

		if (!numero.matches("\\d{16}"))
			return "O número do cartão deve conter exatamente 16 caracteres.";

		return "";
	}

	private String validarCodigoSeguranca(String codigoSeguranca) {
		if (codigoSeguranca == null || codigoSeguranca.isBlank())
			return "O código de segurança é obrigatório.";

		if (!codigoSeguranca.matches("\\d{3,4}"))
			return "O código de segurança deve conter 3 ou 4 dígitos.";

		return "";
	}

	@Override
	public List<String> processar(EntidadeDominio entidade) {
		CartaoCredito cartaoCredito = (CartaoCredito) entidade;

		List<String> res = new ArrayList<>();

		Validacao.adicionarErro(res, validarNomeTitular(cartaoCredito.getNomeTitular()));
		Validacao.adicionarErro(res, validarNumero(cartaoCredito.getNumero()));
		Validacao.adicionarErro(res, validarCodigoSeguranca(cartaoCredito.getCodigoSeguranca()));

		return res;
	}

}
