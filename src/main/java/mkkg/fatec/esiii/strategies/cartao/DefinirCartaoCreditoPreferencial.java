package mkkg.fatec.esiii.strategies.cartao;

import mkkg.fatec.esiii.daos.CartaoCreditoDAO;
import mkkg.fatec.esiii.domain.EntidadeDominio;
import mkkg.fatec.esiii.domain.cartao.CartaoCredito;
import mkkg.fatec.esiii.strategies.IStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DefinirCartaoCreditoPreferencial implements IStrategy {

    @Autowired
    private CartaoCreditoDAO dao;

    @Override
    public String processar(EntidadeDominio entidade) {
        CartaoCredito cartao = (CartaoCredito) entidade;

        if (!cartao.getPreferencial()) {
            return null;
        }

        List<EntidadeDominio> entidades = dao.consultar(cartao);
        for (EntidadeDominio e : entidades) {
            CartaoCredito c = (CartaoCredito) e;

            c.setPreferencial(false);
        }

        entidades.add(cartao);
        entidades.forEach(c -> dao.salvar(c));

        return null;
    }
}
