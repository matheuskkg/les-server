package fatec.mkkg.server.daos;

import fatec.mkkg.server.domain.EntidadeDominio;
import fatec.mkkg.server.domain.cliente.Senha;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SenhaDAO implements IDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void salvar(EntidadeDominio entidade) {

    }

    @Override
    public void alterar(EntidadeDominio entidade) {
        Senha senha = (Senha) entidade;

        Senha senhaAtual = entityManager.find(Senha.class, senha.getId());
        senhaAtual.setSenha(senha.getSenha());

        entityManager.merge(senhaAtual);
    }

    @Override
    public void excluir(EntidadeDominio entidade) {

    }

    @Override
    public List<EntidadeDominio> consultar(EntidadeDominio entidade) {
        return List.of();
    }
}
