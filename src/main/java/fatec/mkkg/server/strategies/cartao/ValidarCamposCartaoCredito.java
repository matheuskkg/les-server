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

	@Override
	public String processar(EntidadeDominio entidade) {
		CartaoCredito cartaoCredito = (CartaoCredito) entidade;

		String prefixo = "Os campos ";
		String sufixo = "não foram devidamente preenchidos";

		List<String> camposNaoPreenchidos = new ArrayList<>();

		camposNaoPreenchidos.add(Validacao.validar(cartaoCredito.getBandeira().getId(), "bandeira.id"));
		camposNaoPreenchidos.add(Validacao.validar(cartaoCredito.getNomeTitular(), "nomeTitular"));
		camposNaoPreenchidos.add(Validacao.validar(cartaoCredito.getNumero(), "numero"));
		camposNaoPreenchidos.add(Validacao.validar(cartaoCredito.getCodigoSeguranca(), "codigoSeguranca"));
		camposNaoPreenchidos.add(Validacao.validar(cartaoCredito.getPreferencial(), "preferencial"));
		if (cartaoCredito.getCliente() != null) {
			camposNaoPreenchidos.add(Validacao.validar(cartaoCredito.getCliente().getId(), "cliente.id"));
		}
		else {
			camposNaoPreenchidos.add("cliente.id");
		}

		StringBuilder sb = new StringBuilder();
		for (String campo : camposNaoPreenchidos) {
			if (!campo.isEmpty()) {
				sb.append("'").append(campo).append("' ");
			}
		}

		if (!sb.isEmpty()) {
			return prefixo + sb.toString() + sufixo;
		}

		return null;
	}

}
