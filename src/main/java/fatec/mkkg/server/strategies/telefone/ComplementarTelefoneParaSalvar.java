package fatec.mkkg.server.strategies.telefone;

import fatec.mkkg.server.domain.EntidadeDominio;
import fatec.mkkg.server.domain.cliente.Cliente;
import fatec.mkkg.server.domain.telefone.Telefone;
import fatec.mkkg.server.domain.telefone.TipoTelefone;
import fatec.mkkg.server.repositories.TipoTelefoneRepository;
import fatec.mkkg.server.strategies.IStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.NoSuchElementException;

@Component
public class ComplementarTelefoneParaSalvar implements IStrategy {

	@Autowired
	private TipoTelefoneRepository tipoTelefoneRepository;

	@Override
	public List<String> processar(EntidadeDominio entidade) {
		Telefone telefone = ((Cliente) entidade).getTelefone();
		TipoTelefone tipoTelefone = telefone.getTipoTelefone();

		try {
			if (tipoTelefone != null && tipoTelefone.getTipo() != null && !tipoTelefone.getTipo().isBlank()) {
				tipoTelefone = tipoTelefoneRepository.findByTipoIgnoreCase(tipoTelefone.getTipo()).orElseThrow();
				telefone.setTipoTelefone(tipoTelefone);
			}

			return List.of();
		}
		catch (NoSuchElementException e) {
			return List.of("Tipo de telefone inválido.");
		}
	}

}
