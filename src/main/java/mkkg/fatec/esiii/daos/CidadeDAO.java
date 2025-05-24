package mkkg.fatec.esiii.daos;

import mkkg.fatec.esiii.domain.EntidadeDominio;
import mkkg.fatec.esiii.domain.endereco.Cidade;
import mkkg.fatec.esiii.domain.endereco.CidadeResponseDTO;
import mkkg.fatec.esiii.repositories.CidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CidadeDAO implements IDAO {

    @Autowired
    private CidadeRepository repository;

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
        Cidade cidade = (Cidade) entidade;

        if (cidade.getEstado().getNome() != null) {
            List<CidadeResponseDTO> cidades = repository.findByEstadoNome(cidade.getEstado().getNome());

            return List.copyOf(cidades);
        }

        return null;
    }
}
