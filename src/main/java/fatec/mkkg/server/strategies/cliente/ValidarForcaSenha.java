package fatec.mkkg.server.strategies.cliente;

import fatec.mkkg.server.domain.EntidadeDominio;
import fatec.mkkg.server.domain.cliente.Cliente;
import fatec.mkkg.server.domain.cliente.Senha;
import fatec.mkkg.server.strategies.IStrategy;
import org.springframework.stereotype.Component;

@Component
public class ValidarForcaSenha implements IStrategy {
    @Override
    public String processar(EntidadeDominio entidade) {
        Senha senha;

        if (entidade instanceof Cliente cliente) {
            senha = cliente.getSenha();
        } else {
            senha = (Senha) entidade;
        }

        String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[-+_!@#$%^&*., ?]).{8,}$";

        if (!senha.getSenha().matches(regex)) {
            return "Senha fraca";
        }

        return null;
    }
}
