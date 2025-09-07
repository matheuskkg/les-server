package fatec.mkkg.server.strategies.endereco;

import fatec.mkkg.server.daos.EnderecoDAO;
import fatec.mkkg.server.domain.EntidadeDominio;
import fatec.mkkg.server.domain.cliente.Cliente;
import fatec.mkkg.server.domain.endereco.Endereco;
import fatec.mkkg.server.strategies.IStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ValidarMinimoEnderecoCobranca implements IStrategy {

	@Autowired
	private EnderecoDAO enderecoDAO;

	@Override
	public List<String> processar(EntidadeDominio entidade) {
		List<Endereco> enderecos = new ArrayList<>();

		// Cadastro de novo cliente
		// Não possui endereços salvos no banco
		if (entidade instanceof Cliente cliente) {
			enderecos.add(cliente.getEndereco());
		}
		else {
			Endereco endereco = (Endereco) entidade;
			enderecos.add(endereco);

			Endereco filtro = new Endereco();
			filtro.setCliente(endereco.getCliente());
			filtro.setCobranca(true);

			List<Endereco> enderecosExistentes = enderecoDAO.buscarPorClienteCobranca(filtro)
				.stream()
				.map(Endereco::new)
				.toList();

			if (enderecosExistentes.size() > 1) {
				return null;
			}

			if (enderecosExistentes.size() == 1 && !enderecosExistentes.getFirst().getId().equals(endereco.getId())) {
				return null;
			}
		}

		for (Endereco e : enderecos) {
			if (e.getCobranca()) {
				return null;
			}
		}

		return List.of("Ao menos um endereço deve ser de cobrança");
	}

}
