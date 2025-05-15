package mkkg.fatec.esiii.strategies.cliente;

import mkkg.fatec.esiii.domain.cliente.Cliente;
import mkkg.fatec.esiii.strategies.IStrategy;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class ValidarConfirmarSenhaTests {

    @Test
    void testSenhasCoincidemRetornaNull() {
        IStrategy strategy = new ValidarConfirmarSenha();

        Cliente c = new Cliente();

        c.setSenha("abcdef");
        c.setSenhaConfirmar("abcdef");

        assertNull(strategy.processar(c));
    }

    @Test
    void testSenhasNaoCoincidemRetornaNotNull() {
        IStrategy strategy = new ValidarConfirmarSenha();

        Cliente c = new Cliente();

        c.setSenha("abcdef");
        c.setSenhaConfirmar("abcdef ");

        assertNotNull(strategy.processar(c));
    }
}
