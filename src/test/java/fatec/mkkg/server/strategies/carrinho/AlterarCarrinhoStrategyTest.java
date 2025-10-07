package fatec.mkkg.server.strategies.carrinho;

import fatec.mkkg.server.domain.carrinho.AlteracaoCarrinhoRequest;
import fatec.mkkg.server.domain.carrinho.Carrinho;
import fatec.mkkg.server.domain.carrinho.ItemCarrinho;
import fatec.mkkg.server.domain.venda.Produto;
import fatec.mkkg.server.repositories.CarrinhoRepository;
import fatec.mkkg.server.util.CarrinhoUtil;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AlterarCarrinhoStrategyTest {

	@Autowired
	AlterarCarrinhoStrategy strategy;

	@MockitoBean
	CarrinhoRepository carrinhoRepository;

	@Test
	void test_deveRemoverItemDoCarrinho_quandoProdutoEstiverNoCarrinho() {
		AlteracaoCarrinhoRequest request = AlteracaoCarrinhoRequest.builder()
				.removes(List.of(1))
				.build();

		Carrinho carrinho = Carrinho.builder()
				.itens(List.of(
						ItemCarrinho.builder().produto(Produto.builder().id(1).build()).build(),
						ItemCarrinho.builder().produto(Produto.builder().id(2).build()).build()
				))
				.build();

		Mockito.when(carrinhoRepository.findByCliente(Mockito.any())).thenReturn(Optional.ofNullable(carrinho));

		strategy.processar(request);

		List<Produto> produtos = CarrinhoUtil.obterProdutosNoCarrinho(carrinho);
		assertEquals(1, produtos.size());
		assertTrue(produtos.stream().noneMatch(p -> p.getId() == 1));
		assertTrue(produtos.stream().anyMatch(p -> p.getId() == 2));
	}

	@Test
	void test_naoDeveRemoverItensDoCarrinho_quandoProdutoNaoEstiverNoCarrinho() {
		AlteracaoCarrinhoRequest request = AlteracaoCarrinhoRequest.builder()
				.removes(List.of(3))
				.build();

		Carrinho carrinho = Carrinho.builder()
				.itens(List.of(
						ItemCarrinho.builder().produto(Produto.builder().id(1).build()).build(),
						ItemCarrinho.builder().produto(Produto.builder().id(2).build()).build()
				))
				.build();

		Mockito.when(carrinhoRepository.findByCliente(Mockito.any())).thenReturn(Optional.ofNullable(carrinho));

		strategy.processar(request);

		List<Produto> produtos = CarrinhoUtil.obterProdutosNoCarrinho(carrinho);
		assertEquals(2, produtos.size());
		assertTrue(produtos.stream().anyMatch(p -> p.getId() == 1));
		assertTrue(produtos.stream().anyMatch(p -> p.getId() == 2));
	}

}