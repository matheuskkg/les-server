package mkkg.fatec.esiii.daos;

import mkkg.fatec.esiii.domain.EntidadeDominio;
import mkkg.fatec.esiii.domain.cartao.BandeiraResponseDTO;
import mkkg.fatec.esiii.repositories.BandeiraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BandeiraDAO implements IDAO {

    @Autowired
    private BandeiraRepository repository;

    @Override
    public void salvar(EntidadeDominio entidade) {

    }

    @Override
    public void alterar(EntidadeDominio entidade) {

    }

    @Override
    public void excluir(EntidadeDominio entidade) {

    }

    @Override
    public List<EntidadeDominio> consultar(EntidadeDominio entidade) {
        List<BandeiraResponseDTO> bandeiras = repository.findAllOrderByBandeira();

        return List.copyOf(bandeiras);
    }
}
