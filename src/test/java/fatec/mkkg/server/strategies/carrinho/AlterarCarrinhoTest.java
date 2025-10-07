package fatec.mkkg.server.strategies.carrinho;

import fatec.mkkg.server.domain.carrinho.AlteracaoCarrinhoRequest;
import fatec.mkkg.server.domain.carrinho.Carrinho;
import fatec.mkkg.server.domain.carrinho.ItemCarrinho;
import fatec.mkkg.server.domain.produto.Produto;
import fatec.mkkg.server.repositories.CarrinhoRepository;
import fatec.mkkg.server.strategies.estoque.ValidarQuantidadeProdutoDisponivelEmEstoque;
import fatec.mkkg.server.util.CarrinhoUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AlterarCarrinhoTest {

	@Autowired
	AlterarCarrinho strategy;

	@MockitoBean
	CarrinhoRepository carrinhoRepository;

	@MockitoBean
	ValidarQuantidadeProdutoDisponivelEmEstoque validarQuantidadeProdutoDisponivelEmEstoque;

	@Test
	@DisplayName("Deve adicionar item no carrinho")
	void test_deveAdicionarItemNoCarrinho_quandoProdutoNaoEstiverNoCarrinho() {
		AlteracaoCarrinhoRequest request = AlteracaoCarrinhoRequest.builder()
			.edits(List.of(ItemCarrinho.builder().produto(Produto.builder().id(1).build()).quantidade(2).build()))
			.build();

		Carrinho carrinho = Carrinho.builder()
			.itens(List.of(ItemCarrinho.builder().produto(Produto.builder().id(2).build()).build()))
			.build();

		Mockito.when(carrinhoRepository.findByCliente(Mockito.any())).thenReturn(Optional.ofNullable(carrinho));

		strategy.processar(request);

		List<Produto> produtos = CarrinhoUtil.obterProdutosNoCarrinho(carrinho);
		assertEquals(2, produtos.size());
		assertTrue(produtos.stream().anyMatch(p -> p.getId() == 1));
		assertTrue(produtos.stream().anyMatch(p -> p.getId() == 2));
	}

	@Test
	@DisplayName("Deve somar quantidade de item quando produto já estiver no carrinho")
	void test_deveSomarQuantidadeDoItemNoCarrinho_quandoProdutoJaEstiverNoCarrinho() {
		AlteracaoCarrinhoRequest request = AlteracaoCarrinhoRequest.builder()
			.edits(List.of(ItemCarrinho.builder().produto(Produto.builder().id(1).build()).quantidade(3).build()))
			.build();

		Carrinho carrinho = Carrinho.builder()
			.itens(List.of(ItemCarrinho.builder().produto(Produto.builder().id(1).build()).quantidade(2).build()))
			.build();

		Mockito.when(carrinhoRepository.findByCliente(Mockito.any())).thenReturn(Optional.ofNullable(carrinho));

		strategy.processar(request);

		assertEquals(5, carrinho.getItens().getFirst().getQuantidade());
	}

	@Test
	@DisplayName("Deve subtrair quantidade de item quando produto já estiver no carrinho")
	void test_deveSubtrairQuantidadeDoItemNoCarrinho_quandoProdutoJaEstiverNoCarrinho() {
		AlteracaoCarrinhoRequest request = AlteracaoCarrinhoRequest.builder()
			.edits(List.of(ItemCarrinho.builder().produto(Produto.builder().id(1).build()).quantidade(-1).build()))
			.build();

		Carrinho carrinho = Carrinho.builder()
			.itens(List.of(ItemCarrinho.builder().produto(Produto.builder().id(1).build()).quantidade(3).build()))
			.build();

		Mockito.when(carrinhoRepository.findByCliente(Mockito.any())).thenReturn(Optional.ofNullable(carrinho));

		strategy.processar(request);

		assertEquals(2, carrinho.getItens().getFirst().getQuantidade());
	}

	@Test
	@DisplayName("Deve remover item do carrinho quando nova quantidade for zero")
	void test_deveRemoverItemDoCarrinho_quandoNovaQuantidadeForZero() {
		AlteracaoCarrinhoRequest request = AlteracaoCarrinhoRequest.builder()
			.edits(List.of(ItemCarrinho.builder().produto(Produto.builder().id(1).build()).quantidade(-1).build()))
			.build();

		Carrinho carrinho = Carrinho.builder()
			.itens(List.of(ItemCarrinho.builder().produto(Produto.builder().id(1).build()).quantidade(1).build()))
			.build();

		Mockito.when(carrinhoRepository.findByCliente(Mockito.any())).thenReturn(Optional.ofNullable(carrinho));

		strategy.processar(request);

		assertTrue(carrinho.getItens().isEmpty());
	}

	@Test
	@DisplayName("Deve remover item do carrinho quando nova quantidade for negativa")
	void test_deveRemoverItemDoCarrinho_quandoNovaQuantidadeForNegativa() {
		AlteracaoCarrinhoRequest request = AlteracaoCarrinhoRequest.builder()
			.edits(List.of(ItemCarrinho.builder().produto(Produto.builder().id(1).build()).quantidade(-4).build()))
			.build();

		Carrinho carrinho = Carrinho.builder()
			.itens(List.of(ItemCarrinho.builder().produto(Produto.builder().id(1).build()).quantidade(1).build()))
			.build();

		Mockito.when(carrinhoRepository.findByCliente(Mockito.any())).thenReturn(Optional.ofNullable(carrinho));

		strategy.processar(request);

		assertTrue(carrinho.getItens().isEmpty());
	}

	@Test
	@DisplayName("Deve remover item do carrinho quando produto estiver no carrinho")
	void test_deveRemoverItemDoCarrinho_quandoProdutoEstiverNoCarrinho() {
		AlteracaoCarrinhoRequest request = AlteracaoCarrinhoRequest.builder().removes(List.of(1)).build();

		Carrinho carrinho = Carrinho.builder()
			.itens(List.of(ItemCarrinho.builder().produto(Produto.builder().id(1).build()).build(),
					ItemCarrinho.builder().produto(Produto.builder().id(2).build()).build()))
			.build();

		Mockito.when(carrinhoRepository.findByCliente(Mockito.any())).thenReturn(Optional.ofNullable(carrinho));

		strategy.processar(request);

		List<Produto> produtos = CarrinhoUtil.obterProdutosNoCarrinho(carrinho);
		assertEquals(1, produtos.size());
		assertTrue(produtos.stream().noneMatch(p -> p.getId() == 1));
		assertTrue(produtos.stream().anyMatch(p -> p.getId() == 2));
	}

	@Test
	@DisplayName("Não deve remover nenhum item do carrinho quando produto não estiver no carrinho")
	void test_naoDeveRemoverItensDoCarrinho_quandoProdutoNaoEstiverNoCarrinho() {
		AlteracaoCarrinhoRequest request = AlteracaoCarrinhoRequest.builder().removes(List.of(3)).build();

		Carrinho carrinho = Carrinho.builder()
			.itens(List.of(ItemCarrinho.builder().produto(Produto.builder().id(1).build()).build(),
					ItemCarrinho.builder().produto(Produto.builder().id(2).build()).build()))
			.build();

		Mockito.when(carrinhoRepository.findByCliente(Mockito.any())).thenReturn(Optional.ofNullable(carrinho));

		strategy.processar(request);

		List<Produto> produtos = CarrinhoUtil.obterProdutosNoCarrinho(carrinho);
		assertEquals(2, produtos.size());
		assertTrue(produtos.stream().anyMatch(p -> p.getId() == 1));
		assertTrue(produtos.stream().anyMatch(p -> p.getId() == 2));
	}

	@Test
	@DisplayName("Deve criar novo carrinho quando carrinho do cliente não existir")
	void test_deveCriarNovoCarrinho_quandoCarrinhoDoClienteNaoExistir() {
		AlteracaoCarrinhoRequest request = AlteracaoCarrinhoRequest.builder()
			.edits(List.of(ItemCarrinho.builder().produto(Produto.builder().id(1).build()).quantidade(2).build()))
			.build();

		Mockito.when(carrinhoRepository.findByCliente(Mockito.any())).thenReturn(Optional.empty());
		strategy.processar(request);
		Mockito.verify(carrinhoRepository, Mockito.times(1)).save(Mockito.any(Carrinho.class));
	}

	@Test
	@DisplayName("Deve processar request vazio sem alterar o carrinho")
	void test_deveProcessarRequestVazio_semAlterarCarrinho() {
		AlteracaoCarrinhoRequest request = AlteracaoCarrinhoRequest.builder().build();

		Carrinho carrinho = Carrinho.builder()
			.itens(List.of(ItemCarrinho.builder().produto(Produto.builder().id(1).build()).build(),
					ItemCarrinho.builder().produto(Produto.builder().id(2).build()).build()))
			.build();

		Mockito.when(carrinhoRepository.findByCliente(Mockito.any())).thenReturn(Optional.ofNullable(carrinho));

		strategy.processar(request);

		List<Produto> produtos = CarrinhoUtil.obterProdutosNoCarrinho(carrinho);
		assertEquals(2, produtos.size());
		assertTrue(produtos.stream().anyMatch(p -> p.getId() == 1));
		assertTrue(produtos.stream().anyMatch(p -> p.getId() == 2));
	}

	@Test
	@DisplayName("Deve processar request com edits e removes ao mesmo tempo")
	void test_deveProcessarRequestComEditsERemovesAoMesmoTempo() {
		AlteracaoCarrinhoRequest request = AlteracaoCarrinhoRequest.builder()
			.edits(List.of(ItemCarrinho.builder().produto(Produto.builder().id(1).build()).quantidade(2).build(),
					ItemCarrinho.builder().produto(Produto.builder().id(2).build()).quantidade(3).build()))
			.removes(List.of(3))
			.build();

		Carrinho carrinho = Carrinho.builder()
			.itens(List.of(ItemCarrinho.builder().produto(Produto.builder().id(2).build()).quantidade(1).build(),
					ItemCarrinho.builder().produto(Produto.builder().id(3).build()).quantidade(5).build()))
			.build();

		Mockito.when(carrinhoRepository.findByCliente(Mockito.any())).thenReturn(Optional.ofNullable(carrinho));

		strategy.processar(request);

		List<ItemCarrinho> itens = carrinho.getItens();
		assertEquals(2, obterItemCarrinhoPeloProdutoId(itens, 1).getQuantidade());
		assertEquals(4, obterItemCarrinhoPeloProdutoId(itens, 2).getQuantidade());
		assertNull(obterItemCarrinhoPeloProdutoId(itens, 3));
	}

	ItemCarrinho obterItemCarrinhoPeloProdutoId(List<ItemCarrinho> itens, int produtoId) {
		return itens.stream().filter(i -> i.getProduto().getId() == produtoId).findFirst().orElse(null);
	}

}