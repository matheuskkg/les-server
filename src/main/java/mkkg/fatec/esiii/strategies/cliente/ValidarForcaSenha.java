package mkkg.fatec.esiii.strategies.cliente;

import mkkg.fatec.esiii.domain.EntidadeDominio;
import mkkg.fatec.esiii.domain.cliente.Cliente;
import mkkg.fatec.esiii.strategies.IStrategy;

import java.util.List;

public class ValidarForcaSenha implements IStrategy {
    @Override
    public List<String> processar(EntidadeDominio entidade) {
        String senha = ((Cliente) entidade).getSenha();
        String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[-+_!@#$%^&*., ?]).{8,}$";

        if (!senha.matches(regex)) {
            return List.of("Senha fraca");
        }

        return null;
    }
}
