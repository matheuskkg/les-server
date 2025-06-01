package fatec.mkkg.server.daos;

import fatec.mkkg.server.domain.EntidadeDominio;
import fatec.mkkg.server.domain.endereco.Cidade;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CidadeDAO implements IDAO {

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
        Cidade cidade = (Cidade) entidade;

        if (cidade.getEstado() != null && cidade.getEstado().getNome() != null) {
            List<Cidade> res = entityManager
                    .createQuery("select new fatec.mkkg.server.domain.endereco.Cidade(c.id, c.nome) from Cidade c where c.estado.nome = :nomeEstado order by c.nome", Cidade.class)
                    .setParameter("nomeEstado", cidade.getEstado().getNome())
                    .getResultList();

            return List.copyOf(res);
        }

        if (cidade.getNome() != null) {
            return List.copyOf(entityManager
                    .createQuery("select new fatec.mkkg.server.domain.endereco.Cidade(c.id, c.nome) from Cidade c where c.nome = :nomeCidade", Cidade.class)
                    .setParameter("nomeCidade", cidade.getNome())
                    .getResultList());
        }

        return List.of();
    }
}
