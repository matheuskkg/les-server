package mkkg.fatec.esiii.strategies.cliente;

import mkkg.fatec.esiii.domain.EntidadeDominio;
import mkkg.fatec.esiii.domain.cliente.Cliente;
import mkkg.fatec.esiii.strategies.IStrategy;

import java.util.List;

public class ValidarConfirmarSenha implements IStrategy {
    @Override
    public List<String> processar(EntidadeDominio entidade) {
        Cliente cliente = (Cliente) entidade;
        String senha = cliente.getSenha();
        String senhaConfirmar = cliente.getSenhaConfirmar();

        if (!senha.equals(senhaConfirmar)) {
            return List.of("Senhas não coincidem\n");
        }

        return null;
    }
}
