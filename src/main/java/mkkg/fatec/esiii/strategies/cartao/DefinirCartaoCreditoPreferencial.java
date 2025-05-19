package mkkg.fatec.esiii.strategies.cartao;

import mkkg.fatec.esiii.domain.EntidadeDominio;
import mkkg.fatec.esiii.domain.cartao.CartaoCredito;
import mkkg.fatec.esiii.repositories.CartaoCreditoRepository;
import mkkg.fatec.esiii.strategies.IStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DefinirCartaoCreditoPreferencial implements IStrategy {

    @Autowired
    private CartaoCreditoRepository repository;

    @Override
    public String processar(EntidadeDominio entidade) {
        /*CartaoCredito cartao = (CartaoCredito) entidade;

        List<CartaoCredito> cartoes = repository.findByClienteId(cartao.getCliente().getId());
        cartoes.forEach(c -> {c.setPreferencial(false);});

        cartao.setPreferencial(true);*/

        return null;
    }
}
