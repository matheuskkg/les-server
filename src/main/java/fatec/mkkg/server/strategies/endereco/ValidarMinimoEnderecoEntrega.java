package fatec.mkkg.server.strategies.endereco;

import fatec.mkkg.server.daos.EnderecoDAO;
import fatec.mkkg.server.domain.EntidadeDominio;
import fatec.mkkg.server.domain.cliente.Cliente;
import fatec.mkkg.server.domain.endereco.Endereco;
import fatec.mkkg.server.strategies.IStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ValidarMinimoEnderecoEntrega implements IStrategy {

    @Autowired
    private EnderecoDAO enderecoDAO;

    @Override
    public String processar(EntidadeDominio entidade) {
        List<Endereco> enderecos = new ArrayList<>();

        //Cadastro de novo cliente
        //Não possui endereços salvos no banco
        if (entidade instanceof Cliente cliente) {
            enderecos = cliente.getEnderecos();
        } else {
            Endereco endereco = (Endereco) entidade;
            enderecos.add(endereco);

            Endereco filtro = new Endereco();
            filtro.setCliente(endereco.getCliente());
            filtro.setEntrega(true);

            List<Endereco> enderecosExistentes = enderecoDAO.consultar(filtro).stream().map(Endereco::new).toList();

            if (enderecosExistentes.size() > 1) {
                return null;
            }

            if (enderecosExistentes.size() == 1 && !enderecosExistentes.getFirst().getId().equals(endereco.getId())) {
                return null;
            }
        }

        for (Endereco e : enderecos) {
            if (e.getEntrega()) {
                return null;
            }
        }

        return "Ao menos um endereço deve ser de entrega";
    }
}
