package fatec.mkkg.server.strategies.carrinho;

import fatec.mkkg.server.domain.EntidadeDominio;
import fatec.mkkg.server.domain.carrinho.AlteracaoCarrinhoRequest;
import fatec.mkkg.server.domain.carrinho.Carrinho;
import fatec.mkkg.server.domain.carrinho.ItemCarrinho;
import fatec.mkkg.server.domain.cliente.Cliente;
import fatec.mkkg.server.repositories.CarrinhoRepository;
import fatec.mkkg.server.strategies.IStrategy;
import fatec.mkkg.server.strategies.estoque.ValidarQuantidadeProdutoDisponivelEmEstoque;
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
			Optional<ItemCarrinho> optionalItemNoCarrinho = itensNoCarrinho.stream()
				.filter(i -> i.getProduto().getId().equals(itemAlteracao.getProduto().getId()))
				.findFirst();

			if (optionalItemNoCarrinho.isEmpty()) {
				ItemCarrinho novoItem = ItemCarrinho.builder().produto(itemAlteracao.getProduto()).build();
				itensNoCarrinho.add(novoItem);
				optionalItemNoCarrinho = Optional.of(novoItem);
			}

			ItemCarrinho itemNoCarrinho = optionalItemNoCarrinho.get();
			int quantidadeNoCarrinho = itemNoCarrinho.getQuantidade();
			int quantidadeAdicional = itemAlteracao.getQuantidade();

			if (quantidadeAdicional > 0) {
				if (!validarEstoque.processar(itemAlteracao).isEmpty()) {
					res.add("Estoque insuficiente para o produto " + itemAlteracao.getProduto().getId());
					return;
				}
			}

			int novaQuantidade = quantidadeNoCarrinho + quantidadeAdicional;
			if (novaQuantidade <= 0) {
				removerItensPeloProdutoId(itensNoCarrinho, List.of(itemNoCarrinho.getProduto().getId()));
				return;
			}

			itemNoCarrinho.setQuantidade(novaQuantidade);
		});

		List<Integer> removes = request.getRemoves();
		removerItensPeloProdutoId(itensNoCarrinho, removes);

		carrinho.setItens(itensNoCarrinho);
		carrinhoRepository.save(carrinho);

		return res;
	}

	private void removerItensPeloProdutoId(List<ItemCarrinho> itensNoCarrinho, List<Integer> idsParaRemover) {
		itensNoCarrinho.removeIf(itemNoCarrinho -> idsParaRemover.contains(itemNoCarrinho.getProduto().getId()));
	}

}
