package fatec.mkkg.server.strategies.cliente;

import fatec.mkkg.server.domain.EntidadeDominio;
import fatec.mkkg.server.domain.cliente.Cliente;
import fatec.mkkg.server.strategies.IStrategy;
import fatec.mkkg.server.util.Sanitizacao;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SanitizarCamposCliente implements IStrategy {

	private void aplicarTrim(Cliente cliente) {
		cliente.setNome(Sanitizacao.trim(cliente.getNome()));
		cliente.setGenero(Sanitizacao.trim(cliente.getGenero()));
		cliente.setCpf(Sanitizacao.trim(cliente.getCpf()));
		cliente.setEmail(Sanitizacao.trim(cliente.getEmail()));
	}

	@Override
	public List<String> processar(EntidadeDominio entidade) {
		Cliente cliente = (Cliente) entidade;

		aplicarTrim(cliente);

		return List.of();
	}

}
