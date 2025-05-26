package mkkg.fatec.esiii.strategies.endereco;

import mkkg.fatec.esiii.domain.EntidadeDominio;
import mkkg.fatec.esiii.domain.endereco.Endereco;
import mkkg.fatec.esiii.repositories.EnderecoRepository;
import mkkg.fatec.esiii.strategies.IStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidarEnderecoPodeSerExcluido implements IStrategy {

    @Autowired
    private EnderecoRepository repository;

    @Override
    public String processar(EntidadeDominio entidade) {
        Endereco endereco = (Endereco) entidade;

        if (
                !repository.existsByClienteIdIsAndCobrancaIsTrueAndIdIsNot(endereco.getCliente().getId(), endereco.getId())
                        || !repository.existsByClienteIdIsAndEntregaIsTrueAndIdIsNot(endereco.getCliente().getId(), endereco.getId())) {
            return "Deve existir ao menos um endereço de entrega e um de cobrança";
        }

        return null;
    }
}
