package fatec.mkkg.server.strategies.endereco;

import fatec.mkkg.server.daos.EnderecoDAO;
import fatec.mkkg.server.domain.EntidadeDominio;
import fatec.mkkg.server.domain.endereco.Endereco;
import fatec.mkkg.server.strategies.IStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ComplementarEnderecoParaExcluir implements IStrategy {

	@Autowired
	private EnderecoDAO enderecoDAO;

	@Override
	public String processar(EntidadeDominio entidade) {
		Endereco endereco = (Endereco) entidade;

		Endereco enderecoDados = new Endereco(endereco.getId());
		enderecoDados = enderecoDAO.complementarParaExcluir(enderecoDados);

		endereco.setCobranca(enderecoDados.getCobranca());
		endereco.setEntrega(enderecoDados.getEntrega());
		endereco.setCliente(enderecoDados.getCliente());

		return null;
	}

}
