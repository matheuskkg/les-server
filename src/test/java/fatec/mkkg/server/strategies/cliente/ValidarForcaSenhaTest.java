package fatec.mkkg.server.strategies.cliente;

import fatec.mkkg.server.domain.cliente.Senha;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ValidarForcaSenhaTest {

	@Test
	void testSenhaForte() {
		Senha senha = new Senha();
		senha.setSenha("Abcdef1!");

		ValidarForcaSenha validarForcaSenha = new ValidarForcaSenha();
		assertNull(validarForcaSenha.processar(senha));
	}

	@Test
	void testSenhaFracaSemCharEspecial() {
		Senha senha = new Senha();
		senha.setSenha("AbcdefgH");

		ValidarForcaSenha validarForcaSenha = new ValidarForcaSenha();
		assertEquals("Senha fraca", validarForcaSenha.processar(senha));
	}

	@Test
	void testSenhaFracaSemCharUppercase() {
		Senha senha = new Senha();
		senha.setSenha("ab@cdefgh2");

		ValidarForcaSenha validarForcaSenha = new ValidarForcaSenha();
		assertEquals("Senha fraca", validarForcaSenha.processar(senha));
	}

	@Test
	void testSenhaFracaSemCharLowercase() {
		Senha senha = new Senha();
		senha.setSenha("AB@CDEFGH2");

		ValidarForcaSenha validarForcaSenha = new ValidarForcaSenha();
		assertEquals("Senha fraca", validarForcaSenha.processar(senha));
	}

	@Test
	void testSenhaFracaTamanhoMenorQueMinimo() {
		Senha senha = new Senha();
		senha.setSenha("Ab@cdef");

		ValidarForcaSenha validarForcaSenha = new ValidarForcaSenha();
		assertEquals("Senha fraca", validarForcaSenha.processar(senha));
	}

}