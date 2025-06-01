package fatec.mkkg.server.strategies.cliente;

import fatec.mkkg.server.domain.EntidadeDominio;
import fatec.mkkg.server.domain.cliente.Cliente;
import fatec.mkkg.server.strategies.IStrategy;
import org.springframework.stereotype.Component;

@Component
public class ValidarForcaSenha implements IStrategy {
    @Override
    public String processar(EntidadeDominio entidade) {
        String senha = ((Cliente) entidade).getSenha();
        String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[-+_!@#$%^&*., ?]).{8,}$";

        if (!senha.matches(regex)) {
            return "Senha fraca";
        }

        return null;
    }
}
