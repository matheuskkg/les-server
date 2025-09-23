package fatec.mkkg.server.strategies.endereco;

import fatec.mkkg.server.daos.EnderecoDAO;
import fatec.mkkg.server.domain.EntidadeDominio;
import fatec.mkkg.server.domain.endereco.Endereco;
import fatec.mkkg.server.strategies.IStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ValidarEnderecoPodeSerExcluido implements IStrategy {

	@Autowired
	private EnderecoDAO enderecoDAO;

	@Override
	public List<String> processar(EntidadeDominio entidade) {
		Endereco endereco = (Endereco) entidade;

		if (!endereco.getCobranca() && !endereco.getEntrega()) {
			return null;
		}

		Endereco filtroCobranca = new Endereco();
		filtroCobranca.setId(endereco.getId());
		filtroCobranca.setCliente(endereco.getCliente());
		filtroCobranca.setCobranca(true);

		Endereco filtroEntrega = new Endereco();
		filtroEntrega.setId(endereco.getId());
		filtroEntrega.setCliente(endereco.getCliente());
		filtroEntrega.setEntrega(true);

		List<Endereco> enderecosCobranca = enderecoDAO.buscarPorClienteCobrancaIdDiferente(filtroCobranca);
		List<Endereco> enderecosEntrega = enderecoDAO.buscarPorClienteEntregaIdDiferente(filtroEntrega);

		boolean existsByCobranca = !enderecosCobranca.isEmpty();
		boolean existsByEntrega = !enderecosEntrega.isEmpty();

		if (!existsByCobranca && !existsByEntrega) {
			return List.of("Deve existir ao menos um endereço de entrega e um de cobrança");
		}

		if (!existsByCobranca) {
			return List.of("Deve existir ao menos um endereço de cobrança");
		}

		if (!existsByEntrega) {
			return List.of("Deve existir ao menos um endereço de entrega");
		}

		return null;
	}

}
