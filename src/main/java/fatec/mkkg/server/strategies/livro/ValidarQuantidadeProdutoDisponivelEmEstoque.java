package fatec.mkkg.server.strategies.livro;

import fatec.mkkg.server.domain.EntidadeDominio;
import fatec.mkkg.server.domain.carrinho.ItemCarrinho;
import fatec.mkkg.server.repositories.ControleEstoqueProdutoRepository;
import fatec.mkkg.server.strategies.IStrategy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class ValidarQuantidadeProdutoDisponivelEmEstoque implements IStrategy {

	@Autowired
	private ControleEstoqueProdutoRepository controleEstoquePublicacaoRepository;

	@Override
	public List<String> processar(EntidadeDominio entidade) {
		ItemCarrinho itemCarrinho = (ItemCarrinho) entidade;

		if (itemCarrinho.getQuantidade() <= 0) {
			log.info("Quantidade inválida ao validar disponibilidade no estoque\nQuantidade: {}\nID do Produto: {}",
					itemCarrinho.getQuantidade(), itemCarrinho.getProduto().getId());
			return List.of("Quantidade inválida");
		}

		Integer quantidadeDisponivel = controleEstoquePublicacaoRepository
			.obterQuantidadeDisponivelEmEstoquePeloProduto(itemCarrinho.getProduto());
		if (quantidadeDisponivel == null || itemCarrinho.getQuantidade() > quantidadeDisponivel) {
			return List.of("Quantidade indisponível em estoque");
		}

		return List.of();
	}

}
