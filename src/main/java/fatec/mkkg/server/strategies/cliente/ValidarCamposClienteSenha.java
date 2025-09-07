package fatec.mkkg.server.strategies.cliente;

import fatec.mkkg.server.domain.EntidadeDominio;
import fatec.mkkg.server.domain.cliente.Cliente;
import fatec.mkkg.server.domain.cliente.Senha;
import fatec.mkkg.server.strategies.IStrategy;
import fatec.mkkg.server.util.Validacao;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ValidarCamposClienteSenha implements IStrategy {

	@Override
	public String processar(EntidadeDominio entidade) {
		Senha senha;

		if (entidade instanceof Cliente cliente) {
			senha = cliente.getSenha();
		}
		else {
			senha = (Senha) entidade;
		}

		String prefixo = "Os campos ";
		String sufixo = "não foram devidamente preenchidos";

		List<String> camposNaoPreenchidos = new ArrayList<>();

		// TODO
		camposNaoPreenchidos.add(Validacao.validar(senha.getSenha(), "senha"));
		camposNaoPreenchidos.add(Validacao.validar(senha.getSenhaConfirmar(), "senhaConfirmar"));

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
