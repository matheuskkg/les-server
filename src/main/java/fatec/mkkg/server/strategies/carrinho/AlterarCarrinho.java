package fatec.mkkg.server.strategies.carrinho;

import fatec.mkkg.server.domain.EntidadeDominio;
import fatec.mkkg.server.domain.carrinho.AlteracaoCarrinhoRequest;
import fatec.mkkg.server.domain.carrinho.Carrinho;
import fatec.mkkg.server.domain.carrinho.ItemCarrinho;
import fatec.mkkg.server.domain.cliente.Cliente;
import fatec.mkkg.server.repositories.CarrinhoRepository;
import fatec.mkkg.server.strategies.IStrategy;
import fatec.mkkg.server.strategies.estoque.ValidarQuantidadeProdutoDisponivelEmEstoque;
import fatec.mkkg.server.util.CarrinhoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class AlterarCarrinho implements IStrategy {

	@Autowired
	private CarrinhoRepository carrinhoRepository;

	@Autowired
	private ValidarQuantidadeProdutoDisponivelEmEstoque validarEstoque;

	@Override
	public List<String> processar(EntidadeDominio entidade) {
		AlteracaoCarrinhoRequest request = (AlteracaoCarrinhoRequest) entidade;

		Carrinho carrinho = obterCarrinho(request.getCliente());

		return processarAlteracaoCarrinho(carrinho, request);
	}

	private Carrinho obterCarrinho(Cliente cliente) {
		Optional<Carrinho> optionalCarrinho = carrinhoRepository.findByCliente(cliente);

		return optionalCarrinho.orElse(Carrinho.builder().cliente(cliente).build());
	}

	private List<String> processarAlteracaoCarrinho(Carrinho carrinho, AlteracaoCarrinhoRequest request) {
		List<String> res = new ArrayList<>();

		ArrayList<ItemCarrinho> itensNoCarrinho = new ArrayList<>(carrinho.getItens());

		List<ItemCarrinho> edits = request.getEdits();
		edits.forEach(itemAlteracao -> {
			ItemCarrinho itemNoCarrinho = CarrinhoUtil
				.obterItemCarrinhoPeloProdutoEAdicionarSeNaoExistir(itensNoCarrinho, itemAlteracao.getProduto());

			int quantidadeNoCarrinho = itemNoCarrinho.getQuantidade();
			int quantidadeAdicional = itemAlteracao.getQuantidade();
			int novaQuantidadeTotal = quantidadeNoCarrinho + quantidadeAdicional;

			if (quantidadeAdicional == 0) {
				return;
			}

			if (novaQuantidadeTotal <= 0) {
				removerItensPeloProdutoId(itensNoCarrinho, List.of(itemNoCarrinho.getProduto().getId()));
				return;
			}

			if (quantidadeAdicional > 0 && !validarEstoque.processar(itemAlteracao).isEmpty()) {
				res.add("Estoque insuficiente para o produto " + itemAlteracao.getProduto().getId());
				return;
			}

			itemNoCarrinho.setQuantidade(novaQuantidadeTotal);
		});

		removerItensPeloProdutoId(itensNoCarrinho, request.getRemoves());

		carrinho.setItens(itensNoCarrinho);
		carrinhoRepository.save(carrinho);

		return res;
	}

	private void removerItensPeloProdutoId(List<ItemCarrinho> itensNoCarrinho, List<Integer> produtoIdsParaRemover) {
		itensNoCarrinho.removeIf(itemNoCarrinho -> produtoIdsParaRemover.contains(itemNoCarrinho.getProduto().getId()));
	}

}
