package fatec.mkkg.server.strategies.telefone;

import fatec.mkkg.server.domain.EntidadeDominio;
import fatec.mkkg.server.domain.cliente.Cliente;
import fatec.mkkg.server.domain.telefone.Telefone;
import fatec.mkkg.server.strategies.IStrategy;
import fatec.mkkg.server.util.Sanitizacao;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SanitizarCamposTelefone implements IStrategy {

	private void aplicarTrim(Telefone telefone) {
		telefone.setDdd(Sanitizacao.trim(telefone.getDdd()));
		telefone.setNumero(Sanitizacao.trim(telefone.getNumero()));

		if (telefone.getTipoTelefone() != null) {
			telefone.getTipoTelefone().setTipo(Sanitizacao.trim(telefone.getTipoTelefone().getTipo()));
		}
	}

	@Override
	public List<String> processar(EntidadeDominio entidade) {
		Telefone telefone = ((Cliente) entidade).getTelefone();

		aplicarTrim(telefone);

		return List.of();
	}
}
