package fatec.mkkg.server.daos;

import fatec.mkkg.server.domain.EntidadeDominio;
import fatec.mkkg.server.domain.endereco.TipoResidencia;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TipoResidenciaDAO implements IDAO {

    @PersistenceContext
    private EntityManager entityManager;

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
        TipoResidencia tipoResidencia = (TipoResidencia) entidade;

        if (tipoResidencia.getTipo() != null) {
            return List.of(entityManager
                    .createQuery("select t from TipoResidencia t where t.tipo = :tipoResidencia", TipoResidencia.class)
                    .setParameter("tipoResidencia", tipoResidencia.getTipo())
                    .getSingleResult());
        }

        return List.of();
    }
}
