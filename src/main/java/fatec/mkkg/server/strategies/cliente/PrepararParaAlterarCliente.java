package fatec.mkkg.server.strategies.cliente;

import fatec.mkkg.server.domain.EntidadeDominio;
import fatec.mkkg.server.domain.cliente.Cliente;
import fatec.mkkg.server.strategies.IStrategy;
import org.springframework.stereotype.Component;

//se enviar pro dao com senha vai dar ruim
@Component
public class PrepararParaAlterarCliente implements IStrategy {
    @Override
    public String processar(EntidadeDominio entidade) {
        Cliente cliente = (Cliente) entidade;

        cliente.setSenha(null);
        cliente.setSenhaConfirmar(null);

        return null;
    }
}
