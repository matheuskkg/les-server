package mkkg.fatec.esiii.daos;

import mkkg.fatec.esiii.domain.EntidadeDominio;
import mkkg.fatec.esiii.domain.telefone.Telefone;
import mkkg.fatec.esiii.domain.telefone.TipoTelefone;
import mkkg.fatec.esiii.repositories.TelefoneRepository;
import mkkg.fatec.esiii.repositories.TipoTelefoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TelefoneDAO implements IDAO {

    @Autowired
    private TelefoneRepository repository;

    @Autowired
    private TipoTelefoneRepository tipoTelefoneRepository;

    @Override
    public void salvar(EntidadeDominio entidade) {
        Telefone telefone = (Telefone) entidade;

        TipoTelefone tipoTelefone = tipoTelefoneRepository.findByTipo(telefone.getTipoTelefone().getTipo());

        telefone.setTipoTelefone(tipoTelefone);

        repository.save(telefone);
    }

    @Override
    public void alterar(EntidadeDominio entidade) {

    }

    @Override
    public void excluir(EntidadeDominio entidade) {

    }

    @Override
    public List<EntidadeDominio> consultar(EntidadeDominio entidade) {
        return List.of();
    }
}
