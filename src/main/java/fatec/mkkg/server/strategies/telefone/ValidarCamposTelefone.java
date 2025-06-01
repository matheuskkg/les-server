package fatec.mkkg.server.strategies.telefone;

import fatec.mkkg.server.domain.EntidadeDominio;
import fatec.mkkg.server.domain.cliente.Cliente;
import fatec.mkkg.server.domain.telefone.Telefone;
import fatec.mkkg.server.strategies.IStrategy;
import fatec.mkkg.server.util.Validacao;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ValidarCamposTelefone implements IStrategy {
    @Override
    public String processar(EntidadeDominio entidade) {
        Telefone telefone = ((Cliente) entidade).getTelefone();

        String prefixo = "Os campos ";
        String sufixo = "não foram devidamente preenchidos";

        List<String> camposNaoPreenchidos = new ArrayList<>();

        camposNaoPreenchidos.add(Validacao.validar(telefone.getDdd(), "ddd"));
        camposNaoPreenchidos.add(Validacao.validar(telefone.getTipoTelefone().getTipo(), "tipoTelefone"));
        camposNaoPreenchidos.add(Validacao.validar(telefone.getNumero(), "numero"));

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
