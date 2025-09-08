package fatec.mkkg.server.strategies.endereco;

import fatec.mkkg.server.domain.EntidadeDominio;
import fatec.mkkg.server.domain.cliente.Cliente;
import fatec.mkkg.server.domain.endereco.Endereco;
import fatec.mkkg.server.strategies.IStrategy;
import fatec.mkkg.server.util.Sanitizacao;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SanitizarCamposEndereco implements IStrategy {

	private void aplicarTrim(Endereco endereco) {
		endereco.setNomeIdentificador(Sanitizacao.trim(endereco.getNomeIdentificador()));
		endereco.setPais(Sanitizacao.trim(endereco.getPais()));
		endereco.setEstado(Sanitizacao.trim(endereco.getEstado()));
		endereco.setCidade(Sanitizacao.trim(endereco.getCidade()));
		endereco.setLogradouro(Sanitizacao.trim(endereco.getLogradouro()));
		endereco.setNumero(Sanitizacao.trim(endereco.getNumero()));
		endereco.setBairro(Sanitizacao.trim(endereco.getBairro()));
		endereco.setCep(Sanitizacao.trim(endereco.getCep()));
		endereco.setObservacao(Sanitizacao.trim(endereco.getObservacao()));

		if (endereco.getTipoLogradouro() != null) {
			endereco.getTipoLogradouro().setTipo(Sanitizacao.trim(endereco.getTipoLogradouro().getTipo()));
		}

		if (endereco.getTipoResidencia() != null) {
			endereco.getTipoResidencia().setTipo(Sanitizacao.trim(endereco.getTipoResidencia().getTipo()));
		}
	}

	@Override
	public List<String> processar(EntidadeDominio entidade) {
		Endereco endereco;

		if (entidade instanceof Cliente) {
			endereco = ((Cliente) entidade).getEndereco();
		}
		else {
			endereco = (Endereco) entidade;
		}

		aplicarTrim(endereco);

		return List.of();
	}
}
