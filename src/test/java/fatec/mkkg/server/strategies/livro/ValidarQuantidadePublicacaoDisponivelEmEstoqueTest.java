package fatec.mkkg.server.strategies.livro;

import fatec.mkkg.server.domain.carrinho.ItemCarrinho;
import fatec.mkkg.server.domain.venda.Produto;
import fatec.mkkg.server.repositories.ControleEstoqueProdutoRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class ValidarQuantidadeProdutoDisponivelEmEstoqueTest {

	@Autowired
	ValidarQuantidadeProdutoDisponivelEmEstoque strategy;

	@MockitoBean
	ControleEstoqueProdutoRepository repository;

	@Test
	@DisplayName("Deve retornar lista vazia quando a quantidade disponível em estoque for suficiente")
	void test_quantidadeDisponivelEmEstoque_deveRetornarListaVazia() {
		ItemCarrinho request = ItemCarrinho.builder().quantidade(3).build();

		Mockito.when(repository.obterQuantidadeDisponivelEmEstoquePeloProduto(Mockito.any())).thenReturn(5);

		List<String> actual = strategy.processar(request);

		assertEquals(Set.of(), Set.copyOf(actual));
	}

	@Test
	@DisplayName("Deve retornar mensagem de erro quando a quantidade disponível em estoque for insuficiente")
	void test_quantidadeIndisponivelEmEstoque_deveRetornarMensagemDeErro() {
		ItemCarrinho request = ItemCarrinho.builder().quantidade(10).build();

		Mockito.when(repository.obterQuantidadeDisponivelEmEstoquePeloProduto(Mockito.any())).thenReturn(5);

		List<String> actual = strategy.processar(request);

		assertEquals(Set.of("Quantidade indisponível em estoque"), Set.copyOf(actual));
	}

	@Test
	@DisplayName("Deve retornar mensagem de erro quando a quantidade for menor ou igual a zero")
	void test_quantidadeInvalida_deveRetornarMensagemDeErro() {
		ItemCarrinho request = ItemCarrinho.builder().produto(Produto.builder().build()).quantidade(0).build();

		Mockito.when(repository.obterQuantidadeDisponivelEmEstoquePeloProduto(Mockito.any())).thenReturn(1);

		List<String> actual = strategy.processar(request);

		assertEquals(Set.of("Quantidade inválida"), Set.copyOf(actual));
	}

}