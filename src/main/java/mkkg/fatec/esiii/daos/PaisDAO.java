package mkkg.fatec.esiii.daos;

import mkkg.fatec.esiii.domain.EntidadeDominio;
import mkkg.fatec.esiii.domain.endereco.PaisResponseDTO;
import mkkg.fatec.esiii.repositories.PaisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PaisDAO implements IDAO {

    @Autowired
    private PaisRepository repository;

    @Override
    public void salvar(EntidadeDominio entidade) {

    }

    @Override
    public void atualizar(EntidadeDominio entidade) {

    }

    @Override
    public void excluir(EntidadeDominio entidade) {

    }

    @Override
    public List<EntidadeDominio> consultar(EntidadeDominio entidade) {
        List<PaisResponseDTO> paises = repository.findAllAsPaisResponseDtoList();

        return List.copyOf(paises);
    }
}
