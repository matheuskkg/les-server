package fatec.mkkg.server.strategies.cliente;

import fatec.mkkg.server.domain.EntidadeDominio;
import fatec.mkkg.server.domain.cliente.Senha;
import fatec.mkkg.server.strategies.IStrategy;
import fatec.mkkg.server.util.Sanitizacao;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SanitizarCamposClienteSenha implements IStrategy {

	private void aplicarTrim(Senha senha) {
		senha.setSenha(Sanitizacao.trim(senha.getSenha()));
		senha.setSenhaConfirmar(Sanitizacao.trim(senha.getSenhaConfirmar()));
	}

	@Override
	public List<String> processar(EntidadeDominio entidade) {
		Senha senha = (Senha) entidade;

		aplicarTrim(senha);

		return List.of();
	}

}
