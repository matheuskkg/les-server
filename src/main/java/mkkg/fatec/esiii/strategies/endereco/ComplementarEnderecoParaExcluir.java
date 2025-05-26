package mkkg.fatec.esiii.strategies.endereco;

import mkkg.fatec.esiii.domain.EntidadeDominio;
import mkkg.fatec.esiii.domain.endereco.Endereco;
import mkkg.fatec.esiii.repositories.EnderecoRepository;
import mkkg.fatec.esiii.strategies.IStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ComplementarEnderecoParaExcluir implements IStrategy {

    @Autowired
    private EnderecoRepository repository;

    @Override
    public String processar(EntidadeDominio entidade) {
        Endereco endereco = (Endereco) entidade;

        Endereco enderecoDados = repository.complementarEnderecoParaExcluir(endereco.getId());

        endereco.setCobranca(enderecoDados.getCobranca());
        endereco.setEntrega(enderecoDados.getEntrega());
        endereco.setCliente(enderecoDados.getCliente());

        return null;
    }
}
