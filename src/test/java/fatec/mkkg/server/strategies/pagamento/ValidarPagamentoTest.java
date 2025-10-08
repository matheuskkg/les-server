package fatec.mkkg.server.strategies.pagamento;

import fatec.mkkg.server.domain.cliente.Cliente;
import fatec.mkkg.server.domain.pagamento.Pagamento;
import fatec.mkkg.server.domain.pagamento.formas.cupom.Cupom;
import fatec.mkkg.server.domain.pagamento.formas.cupom.TipoCupom;
import fatec.mkkg.server.repositories.CupomRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class ValidarPagamentoTest {

	@Autowired
	ValidarPagamento strategy;

	@MockitoBean
	CupomRepository cupomRepository;

	@Test
	@DisplayName("Deve ser possível utilizar um cupom promocional")
	void test_deveSerPossivelUtilizarUmCupomPromocional() {
		Pagamento pagamento = Pagamento.builder()
				.valorTotal(50)
				.divisaoFormasPagamento(
						Map.of(
								Cupom.builder().cliente(new Cliente()).tipo(TipoCupom.PROMOCIONAL).build(), 0.0))
				.build();

		Mockito.when(cupomRepository.obterValorDoCupom(Mockito.any(), Mockito.any())).thenReturn(Optional.of(50));

		List<String> actual = strategy.processar(pagamento);
		assertTrue(actual.isEmpty());
	}

	@Test
	@DisplayName("Não deve ser possível utilizar mais de um cupom promocional")
	void test_naoDeveSerPossivelUtilizarMaisDeUmCupomPromocional() {
		Pagamento pagamento = Pagamento.builder()
				.valorTotal(100)
				.divisaoFormasPagamento(
						Map.of(
								Cupom.builder().cliente(new Cliente()).codigo("1P").tipo(TipoCupom.PROMOCIONAL).build(), 0.0,
								Cupom.builder().cliente(new Cliente()).codigo("2P").tipo(TipoCupom.PROMOCIONAL).build(), 0.0))
				.build();

		Mockito.when(cupomRepository.obterValorDoCupom(Mockito.any(), Mockito.any())).thenReturn(Optional.of(50));

		List<String> actual = strategy.processar(pagamento);
		assertEquals(Set.of("Apenas um cupom promocional pode ser utilizado por compra"), Set.copyOf(actual));
	}

	@Test
	@DisplayName("Deve ser possível utilizar apenas cupons de troca")
	void test_deveSerPossivelUtilizarApenasCuponsDeTroca() {
		Pagamento pagamento = Pagamento.builder()
				.valorTotal(14098)
				.divisaoFormasPagamento(
						Map.of(
								Cupom.builder().cliente(new Cliente()).codigo("1T").tipo(TipoCupom.TROCA).build(), 0.0,
								Cupom.builder().cliente(new Cliente()).codigo("2T").tipo(TipoCupom.TROCA).build(), 0.0))
				.build();

		Mockito.when(cupomRepository.obterValorDoCupom(Mockito.any(), Mockito.any()))
				.thenReturn(Optional.of(6000))
				.thenReturn(Optional.of(8500));

		List<String> actual = strategy.processar(pagamento);
		assertTrue(actual.isEmpty());
	}

}