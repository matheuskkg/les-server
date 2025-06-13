package fatec.mkkg.server.daos;

import fatec.mkkg.server.domain.EntidadeDominio;
import fatec.mkkg.server.domain.endereco.Estado;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EstadoDAO implements IDAO {

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
        Estado estado = (Estado) entidade;

        if (estado.getPais() != null && estado.getPais().getNome() != null) {
            List<Estado> res = entityManager
                    .createQuery("select new fatec.mkkg.server.domain.endereco.Estado(e.id, e.nome, e.uf) from Estado e where e.pais.nome = :nomePais order by e.nome", Estado.class)
                    .setParameter("nomePais", estado.getPais().getNome())
                    .getResultList();

            return res.isEmpty() ? List.of(buscarEstadoDefault()) : List.copyOf(res);
        }

        return List.of();
    }

    private Estado buscarEstadoDefault() {
        String ESTADO_DEFAULT = "Exterior";

        return entityManager
                .createQuery("select new fatec.mkkg.server.domain.endereco.Estado(e.id, e.nome, e.uf) from Estado e where e.nome = :estadoDefault", Estado.class)
                .setParameter("estadoDefault", ESTADO_DEFAULT)
                .getSingleResult();
    }
}
