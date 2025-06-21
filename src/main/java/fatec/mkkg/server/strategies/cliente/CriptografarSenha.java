package fatec.mkkg.server.strategies.cliente;

import fatec.mkkg.server.domain.EntidadeDominio;
import fatec.mkkg.server.domain.cliente.Cliente;
import fatec.mkkg.server.domain.cliente.Senha;
import fatec.mkkg.server.strategies.IStrategy;
import fatec.mkkg.server.util.Criptografia;
import org.springframework.stereotype.Component;

@Component
public class CriptografarSenha implements IStrategy {
    @Override
    public String processar(EntidadeDominio entidade) {
        Senha senha;

        if (entidade instanceof Cliente cliente) {
            senha = cliente.getSenha();
        } else {
            senha = (Senha) entidade;
        }

        String senhaCriptografada = Criptografia.criptografar(senha.getSenha());

        senha.setSenha(senhaCriptografada);

        return null;
    }
}
