package fatec.mkkg.server.strategies.cliente;

import fatec.mkkg.server.domain.EntidadeDominio;
import fatec.mkkg.server.domain.cliente.Cliente;
import fatec.mkkg.server.strategies.IStrategy;
import org.springframework.stereotype.Component;

@Component
public class SetCadastroAtivo implements IStrategy {
    @Override
    public String processar(EntidadeDominio entidade) {
        Cliente cliente = (Cliente) entidade;

        cliente.setCadastroAtivo(true);

        return null;
    }
}
