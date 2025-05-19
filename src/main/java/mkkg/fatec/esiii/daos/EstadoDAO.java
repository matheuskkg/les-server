package mkkg.fatec.esiii.daos;

import mkkg.fatec.esiii.domain.EntidadeDominio;
import mkkg.fatec.esiii.domain.endereco.Estado;
import mkkg.fatec.esiii.domain.endereco.EstadoResponseDTO;
import mkkg.fatec.esiii.repositories.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EstadoDAO implements IDAO {

    @Autowired
    private EstadoRepository repository;

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
        Estado estado = (Estado) entidade;

        if (estado.getPais().getNome() != null) {
            List<EstadoResponseDTO> estados = repository.findByPaisNome(estado.getPais().getNome());

            return List.copyOf(estados);
        }

        return null;
    }
}
