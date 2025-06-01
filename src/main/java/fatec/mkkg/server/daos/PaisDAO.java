package fatec.mkkg.server.daos;

import fatec.mkkg.server.domain.EntidadeDominio;
import fatec.mkkg.server.domain.endereco.Pais;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PaisDAO implements IDAO {

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
        List<Pais> res = entityManager
                .createQuery("select p from Pais p order by nome", Pais.class)
                .getResultList();

        return List.copyOf(res);
    }
}
