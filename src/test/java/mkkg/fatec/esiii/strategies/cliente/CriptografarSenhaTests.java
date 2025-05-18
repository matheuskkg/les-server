package mkkg.fatec.esiii.strategies.cliente;

import mkkg.fatec.esiii.domain.cliente.Cliente;
import mkkg.fatec.esiii.strategies.IStrategy;
import mkkg.fatec.esiii.util.Criptografia;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CriptografarSenhaTests {

    @Test
    void testVerificacaoSenhasCriptografadasCoincidem() {
        IStrategy strategy = new CriptografarSenha();

        Cliente c = new Cliente();
        c.setSenha("abcdef");

        strategy.processar(c);

        String hashGerado = c.getSenha();
        assertTrue(Criptografia.verificarCriptografia("abcdef", hashGerado));
    }

    @Test
    void testVerificacaoSenhasCriptografadasNaoCoincidem() {
        IStrategy strategy = new CriptografarSenha();

        Cliente c = new Cliente();
        c.setSenha("abcdef");

        strategy.processar(c);

        String hashGerado = c.getSenha();
        assertFalse(Criptografia.verificarCriptografia("abcdefg", hashGerado));
    }
}
