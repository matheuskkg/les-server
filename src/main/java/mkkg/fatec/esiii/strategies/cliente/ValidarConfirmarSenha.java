package mkkg.fatec.esiii.strategies.cliente;

import mkkg.fatec.esiii.domain.EntidadeDominio;
import mkkg.fatec.esiii.domain.cliente.Cliente;
import mkkg.fatec.esiii.strategies.IStrategy;

public class ValidarConfirmarSenha implements IStrategy {
    @Override
    public String processar(EntidadeDominio entidade) {
        Cliente cliente = (Cliente) entidade;
        String senha = cliente.getSenha();
        String senhaConfirmar = cliente.getSenhaConfirmar();

        if (!senha.equals(senhaConfirmar)) {
            return "Senhas não coincidem";
        }

        return null;
    }
}
