package fatec.mkkg.server.strategies.cliente;

import fatec.mkkg.server.domain.EntidadeDominio;
import fatec.mkkg.server.domain.cliente.Cliente;
import fatec.mkkg.server.domain.cliente.Senha;
import fatec.mkkg.server.strategies.IStrategy;
import org.springframework.stereotype.Component;

@Component
public class ValidarConfirmarSenha implements IStrategy {

	@Override
	public String processar(EntidadeDominio entidade) {
		Senha senha;

		if (entidade instanceof Cliente cliente) {
			senha = cliente.getSenha();
		}
		else {
			senha = (Senha) entidade;
		}

		if (!senha.getSenha().equals(senha.getSenhaConfirmar())) {
			return "Senhas não coincidem";
		}

		return null;
	}

}