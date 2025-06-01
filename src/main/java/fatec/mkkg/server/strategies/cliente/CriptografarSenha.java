package fatec.mkkg.server.strategies.cliente;

import fatec.mkkg.server.domain.EntidadeDominio;
import fatec.mkkg.server.domain.cliente.Cliente;
import fatec.mkkg.server.strategies.IStrategy;
import fatec.mkkg.server.util.Criptografia;
import org.springframework.stereotype.Component;

@Component
public class CriptografarSenha implements IStrategy {
    @Override
    public String processar(EntidadeDominio entidade) {
        Cliente cliente = (Cliente) entidade;

        String senhaCriptografada = Criptografia.criptografar(cliente.getSenha());

        cliente.setSenha(senhaCriptografada);

        return null;
    }
}
