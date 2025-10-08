package fatec.mkkg.server.util;

import fatec.mkkg.server.domain.carrinho.Carrinho;
import fatec.mkkg.server.domain.carrinho.ItemCarrinho;
import fatec.mkkg.server.domain.produto.Produto;

import java.util.List;
import java.util.Optional;

public class CarrinhoUtil {

	public static List<Produto> obterProdutosNoCarrinho(Carrinho carrinho) {
		return carrinho.getItens().stream().map(ItemCarrinho::getProduto).toList();
	}

	public static ItemCarrinho obterItemCarrinhoPeloProdutoEAdicionarSeNaoExistir(List<ItemCarrinho> itensCarrinho,
			Produto produto) {
		Optional<ItemCarrinho> optionalItemCarrinho = itensCarrinho.stream()
			.filter(i -> i.getProduto().getId().equals(produto.getId()))
			.findFirst();

		if (optionalItemCarrinho.isEmpty()) {
			ItemCarrinho novoItem = ItemCarrinho.builder().produto(produto).build();
			itensCarrinho.add(novoItem);
			return novoItem;
		}

		return optionalItemCarrinho.get();
	}

}
