package fatec.mkkg.server.util;

import fatec.mkkg.server.domain.carrinho.Carrinho;
import fatec.mkkg.server.domain.carrinho.ItemCarrinho;
import fatec.mkkg.server.domain.venda.Produto;

import java.util.List;

public class CarrinhoUtil {

	public static List<Produto> obterProdutosNoCarrinho(Carrinho carrinho) {
		return carrinho.getItens().stream().map(ItemCarrinho::getProduto).toList();
	}

}
