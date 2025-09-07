package fatec.mkkg.server.strategies.endereco;

import fatec.mkkg.server.daos.EnderecoDAO;
import fatec.mkkg.server.domain.endereco.Endereco;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ValidarEnderecoPodeSerExcluidoTest {

	@Test
	void testEnderecoNaoPodeSerExcluido_NaoHaOutroEnderecoCobrancaEEntregaSalvo() throws Exception {
		EnderecoDAO enderecoDAO = mock(EnderecoDAO.class);
		ValidarEnderecoPodeSerExcluido strategy = new ValidarEnderecoPodeSerExcluido();

		var field = ValidarEnderecoPodeSerExcluido.class.getDeclaredField("enderecoDAO");
		field.setAccessible(true);
		field.set(strategy, enderecoDAO);

		Endereco endereco = new Endereco();
		endereco.setEntrega(true);
		endereco.setCobranca(true);

		when(enderecoDAO.consultar(any())).thenReturn(List.of());

		String result = strategy.processar(endereco);

		assertEquals("Deve existir ao menos um endereço de entrega e um de cobrança", result);
	}

	// @Test
	// void testEnderecoCobrancaTrueEEntregaTruePodeSerExcluido() throws Exception{
	// EnderecoDAO enderecoDAO = mock(EnderecoDAO.class);
	// ValidarEnderecoPodeSerExcluido strategy = new ValidarEnderecoPodeSerExcluido();
	//
	// var field = ValidarEnderecoPodeSerExcluido.class.getDeclaredField("enderecoDAO");
	// field.setAccessible(true);
	// field.set(strategy, enderecoDAO);
	//
	// Endereco endereco = new Endereco();
	// endereco.setEntrega(true);
	// endereco.setCobranca(true);
	//
	// when(enderecoDAO.consultar(any())).thenReturn(List.of(new Endereco()));
	//
	// String result = strategy.processar(endereco);
	//
	// assertNull(result);
	// }

}
