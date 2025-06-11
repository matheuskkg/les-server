package fatec.mkkg.server.strategies.endereco;

import fatec.mkkg.server.domain.EntidadeDominio;
import fatec.mkkg.server.domain.cliente.Cliente;
import fatec.mkkg.server.domain.endereco.Endereco;
import fatec.mkkg.server.strategies.IStrategy;
import fatec.mkkg.server.util.Validacao;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ValidarCamposEndereco implements IStrategy {
    @Override
    public String processar(EntidadeDominio entidade) {
        Endereco endereco;
        boolean validandoEndereco = false;

        if (entidade instanceof Cliente) {
            endereco = ((Cliente) entidade).getEndereco();
        } else {
            endereco = (Endereco) entidade;
            validandoEndereco = true;
        }

        String prefixo = "Os campos ";
        String sufixo = "não foram devidamente preenchidos";

        List<String> camposNaoPreenchidos = new ArrayList<>();

        camposNaoPreenchidos.add(Validacao.validar(endereco.getNomeIdentificador(), "nomeIdentificador"));
        camposNaoPreenchidos.add(Validacao.validar(endereco.getCidade().getNome(), "cidade.nome"));
        camposNaoPreenchidos.add(Validacao.validar(endereco.getTipoLogradouro().getTipo(), "tipoLogradouro.tipo"));
        camposNaoPreenchidos.add(Validacao.validar(endereco.getLogradouro(), "logradouro"));
        camposNaoPreenchidos.add(Validacao.validar(endereco.getTipoResidencia().getTipo(), "tipoResidencia.tipo"));
        camposNaoPreenchidos.add(Validacao.validar(endereco.getNumero(), "numero"));
        camposNaoPreenchidos.add(Validacao.validar(endereco.getBairro(), "bairro"));
        camposNaoPreenchidos.add(Validacao.validar(endereco.getCep(), "cep"));
        camposNaoPreenchidos.add(Validacao.validar(endereco.getCobranca(), "cobranca"));
        camposNaoPreenchidos.add(Validacao.validar(endereco.getEntrega(), "entrega"));
        if (validandoEndereco) {
            if (endereco.getCliente() != null) {
                camposNaoPreenchidos.add(Validacao.validar(endereco.getCliente().getId(), "cliente.id"));
            } else {
                camposNaoPreenchidos.add("cliente.id");
            }
        }

        StringBuilder sb = new StringBuilder();
        for (String campo : camposNaoPreenchidos) {
            if (!campo.isEmpty()) {
                sb.append("'").append(campo).append("' ");
            }
        }

        if (!sb.isEmpty()) {
            return prefixo + sb.toString() + "do endereco '" + endereco.getNomeIdentificador() + "' " + sufixo;
        }

        return null;
    }
}
