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

	private String validarSenha(String senha) {
		if (senha == null || senha.isBlank())
			return "A senha é obrigatória.";

		return "";
	}

	private String validarSenhaConfirmar(String senhaConfirmar) {
		if (senhaConfirmar == null || senhaConfirmar.isBlank())
			return "A confirmação de senha é obrigatória.";

		return "";
	}

	@Override
	public List<String> processar(EntidadeDominio entidade) {
		Senha senha;

		if (entidade instanceof Cliente cliente) {
			senha = cliente.getSenha();
		}
		else {
			senha = (Senha) entidade;
		}

		List<String> res = new ArrayList<>();

		Validacao.adicionarErro(res, validarSenha(senha.getSenha()));
		Validacao.adicionarErro(res, validarSenhaConfirmar(senha.getSenhaConfirmar()));

		return res;
	}

}
