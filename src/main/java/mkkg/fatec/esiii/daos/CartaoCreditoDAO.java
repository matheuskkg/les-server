package mkkg.fatec.esiii.daos;

import mkkg.fatec.esiii.domain.EntidadeDominio;
import mkkg.fatec.esiii.domain.cartao.Bandeira;
import mkkg.fatec.esiii.domain.cartao.CartaoCredito;
import mkkg.fatec.esiii.repositories.BandeiraRepository;
import mkkg.fatec.esiii.repositories.CartaoCreditoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CartaoCreditoDAO implements IDAO {

    @Autowired
    private CartaoCreditoRepository repository;

    @Autowired
    private BandeiraRepository bandeiraRepository;

    private CartaoCredito complementarCartaoCredito(CartaoCredito cartaoCredito) {
        Bandeira bandeira = bandeiraRepository.findByBandeira(cartaoCredito.getBandeira().getBandeira());

        cartaoCredito.setBandeira(bandeira);

        return cartaoCredito;
    }

    @Override
    public void salvar(EntidadeDominio entidade) {
        CartaoCredito cartaoCredito = complementarCartaoCredito((CartaoCredito) entidade);

        repository.save(cartaoCredito);
    }

    @Override
    public void atualizar(EntidadeDominio entidade) {

    }

    @Override
    public void excluir(EntidadeDominio entidade) {

    }

    @Override
    public List<EntidadeDominio> consultar(EntidadeDominio entidade) {
        CartaoCredito cartaoCredito = (CartaoCredito) entidade;

        if (cartaoCredito.getCliente() != null && cartaoCredito.getCliente().getId() != null) {
            return new ArrayList<>(repository.findByClienteId(cartaoCredito.getCliente().getId()));
        }

        return List.of();
    }
}
