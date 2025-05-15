package mkkg.fatec.esiii.strategies.cliente;

import mkkg.fatec.esiii.domain.cliente.Cliente;
import mkkg.fatec.esiii.strategies.IStrategy;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ValidarForcaSenhaTests {

    @Test
    void testSenhaForteRetornaNull() {
        IStrategy strategy = new ValidarForcaSenha();

        Cliente c = new Cliente();
        c.setSenha("Abcdef@1");

        assertNull(strategy.processar(c));
    }

    @Test
    void testSenhaSemCharUpperCaseRetornaNotNull() {
        IStrategy strategy = new ValidarForcaSenha();

        Cliente c = new Cliente();
        c.setSenha("awd@@31fgg");

        assertNotNull(strategy.processar(c));
    }

    @Test
    void testSenhaSemCharLowerCaseRetornaNotNull() {
        IStrategy strategy = new ValidarForcaSenha();

        Cliente c = new Cliente();
        c.setSenha("AWDS@32FG");

        assertNotNull(strategy.processar(c));
    }

    @Test
    void testSenhaComMenosOitoCharRetornaNotNull() {
        IStrategy strategy = new ValidarForcaSenha();

        Cliente c = new Cliente();
        c.setSenha("123a@fF");

        assertNotNull(strategy.processar(c));
    }

    @Test
    void testSenhaSemCharEspecialRetornaNotNull() {
        IStrategy strategy = new ValidarForcaSenha();

        Cliente c = new Cliente();
        c.setSenha("awsdFFAS24");

        assertNotNull(strategy.processar(c));
    }

    @Test
    void testSenhaVaziaRetornaNotNull() {
        IStrategy strategy = new ValidarForcaSenha();

        Cliente c = new Cliente();
        c.setSenha("");

        assertNotNull(strategy.processar(c));
    }
}
