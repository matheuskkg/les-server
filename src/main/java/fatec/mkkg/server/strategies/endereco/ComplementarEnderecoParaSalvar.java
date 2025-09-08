package fatec.mkkg.server.strategies.endereco;

import fatec.mkkg.server.domain.EntidadeDominio;
import fatec.mkkg.server.domain.cliente.Cliente;
import fatec.mkkg.server.domain.endereco.Endereco;
import fatec.mkkg.server.domain.endereco.TipoLogradouro;
import fatec.mkkg.server.domain.endereco.TipoResidencia;
import fatec.mkkg.server.repositories.TipoLogradouroRepository;
import fatec.mkkg.server.repositories.TipoResidenciaRepository;
import fatec.mkkg.server.strategies.IStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Component
public class ComplementarEnderecoParaSalvar implements IStrategy {

	@Autowired
	private TipoLogradouroRepository tipoLogradouroRepository;

	@Autowired
	private TipoResidenciaRepository tipoResidenciaRepository;

	@Override
	public List<String> processar(EntidadeDominio entidade) {
		Endereco endereco;

		if (entidade instanceof Cliente) {
			endereco = ((Cliente) entidade).getEndereco();
		}
		else {
			endereco = (Endereco) entidade;
		}

		List<String> res = new ArrayList<>();

		TipoLogradouro tipoLogradouro = endereco.getTipoLogradouro();
		TipoResidencia tipoResidencia = endereco.getTipoResidencia();

		try {
			if (tipoLogradouro != null && tipoLogradouro.getTipo() != null && !tipoLogradouro.getTipo().isBlank()) {
				tipoLogradouro = tipoLogradouroRepository.findByTipoIgnoreCase(tipoLogradouro.getTipo()).orElseThrow();
				endereco.setTipoLogradouro(tipoLogradouro);
			}
		}
		catch (NoSuchElementException e) {
			res.add("Tipo de logradouro inválido.");
		}

		try {
			if (tipoResidencia != null && tipoResidencia.getTipo() != null && !tipoResidencia.getTipo().isBlank()) {
				tipoResidencia = tipoResidenciaRepository.findByTipoIgnoreCase(tipoResidencia.getTipo()).orElseThrow();
				endereco.setTipoResidencia(tipoResidencia);
			}
		}
		catch (NoSuchElementException e) {
			res.add("Tipo de residência inválido.");
		}

		return res;
	}

}
