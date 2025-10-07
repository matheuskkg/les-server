package fatec.mkkg.server.strategies.carrinho;

import fatec.mkkg.server.domain.EntidadeDominio;
import fatec.mkkg.server.domain.carrinho.AlteracaoCarrinhoRequest;
import fatec.mkkg.server.domain.carrinho.Carrinho;
import fatec.mkkg.server.domain.carrinho.ItemCarrinho;
import fatec.mkkg.server.domain.cliente.Cliente;
import fatec.mkkg.server.repositories.CarrinhoRepository;
import fatec.mkkg.server.strategies.IStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class AlterarCarrinhoStrategy implements IStrategy {

	@Autowired
	private CarrinhoRepository carrinhoRepository;

	@Override
	public List<String> processar(EntidadeDominio entidade) {
		AlteracaoCarrinhoRequest request = (AlteracaoCarrinhoRequest) entidade;

		Carrinho carrinho = obterCarrinho(request.getCliente());

		processarAlteracaoCarrinho(carrinho, request);

		return List.of();
	}

	private Carrinho obterCarrinho(Cliente cliente) {
		Optional<Carrinho> optionalCarrinho = carrinhoRepository.findByCliente(cliente);

		return optionalCarrinho.orElse(Carrinho.builder().cliente(cliente).build());
	}

	private void processarAlteracaoCarrinho(Carrinho carrinho, AlteracaoCarrinhoRequest request) {
		ArrayList<ItemCarrinho> itensNoCarrinho = new ArrayList<>(carrinho.getItens());

		List<ItemCarrinho> edits = request.getEdits();

		List<Integer> removes = request.getRemoves();
		itensNoCarrinho.removeIf(itemNoCarrinho -> removes.contains(itemNoCarrinho.getProduto().getId()));

		carrinho.setItens(itensNoCarrinho);
	}

}
