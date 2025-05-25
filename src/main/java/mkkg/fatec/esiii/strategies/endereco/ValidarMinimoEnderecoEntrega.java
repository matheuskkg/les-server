package mkkg.fatec.esiii.strategies.endereco;

import mkkg.fatec.esiii.domain.EntidadeDominio;
import mkkg.fatec.esiii.domain.cliente.Cliente;
import mkkg.fatec.esiii.domain.endereco.Endereco;
import mkkg.fatec.esiii.repositories.EnderecoRepository;
import mkkg.fatec.esiii.strategies.IStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ValidarMinimoEnderecoEntrega implements IStrategy {

    @Autowired
    private EnderecoRepository repository;

    private boolean procurarEnderecoEntrega(List<Endereco> enderecos) {
        for (Endereco e : enderecos) {
            if (e.getEntrega()) {
                return true;
            }
        }

        return false;
    }

    @Override
    public String processar(EntidadeDominio entidade) {
        List<Endereco> enderecos = null;
        Integer clienteId = null;

        if (entidade instanceof Cliente cliente) {
            enderecos = cliente.getEnderecos();
            clienteId = cliente.getId();
        } else {
            Endereco endereco = (Endereco) entidade;
            enderecos = List.of(endereco);
            clienteId = endereco.getCliente().getId();
        }

        if (!repository.existsByClienteIdAndEntregaIsTrue(clienteId) && !procurarEnderecoEntrega(enderecos)) {
            return "Ao menos um endereço deve ser de entrega";
        }

        return null;
    }
}
