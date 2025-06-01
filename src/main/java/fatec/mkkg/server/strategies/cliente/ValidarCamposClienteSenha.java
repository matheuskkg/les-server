package fatec.mkkg.server.strategies.cliente;

import fatec.mkkg.server.domain.EntidadeDominio;
import fatec.mkkg.server.domain.cliente.Cliente;
import fatec.mkkg.server.strategies.IStrategy;
import fatec.mkkg.server.util.Validacao;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ValidarCamposClienteSenha implements IStrategy {
    @Override
    public String processar(EntidadeDominio entidade) {
        Cliente cliente = (Cliente) entidade;

        String prefixo = "Os campos ";
        String sufixo = "não foram devidamente preenchidos";

        List<String> camposNaoPreenchidos = new ArrayList<>();

        camposNaoPreenchidos.add(Validacao.validar(cliente.getSenha(), "senha"));
        camposNaoPreenchidos.add(Validacao.validar(cliente.getSenhaConfirmar(), "senhaConfirmar"));

        StringBuilder sb = new StringBuilder();
        for (String campo : camposNaoPreenchidos) {
            if (!campo.isEmpty()) {
                sb.append("'").append(campo).append("' ");
            }
        }

        if (!sb.isEmpty()) {
            return prefixo + sb.toString() + sufixo;
        }

        return null;
    }
}
