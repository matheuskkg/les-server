package fatec.mkkg.server.daos;

import fatec.mkkg.server.domain.EntidadeDominio;
import fatec.mkkg.server.domain.cliente.Senha;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SenhaDAO implements IDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    @Override
    public void salvar(EntidadeDominio entidade) {
        Senha senha = (Senha) entidade;

        entityManager.persist(senha);
    }

    @Transactional
    @Override
    public void alterar(EntidadeDominio entidade) {
        Senha senha = (Senha) entidade;

        Senha senhaAtual = buscarPorCliente(senha);

        senha.setId(senhaAtual.getId());

        alterarSenha(senha);
    }

    @Override
    public void excluir(EntidadeDominio entidade) {

    }

    @Override
    public List<EntidadeDominio> consultar(EntidadeDominio entidade) {
        return List.of();
    }

    private void alterarSenha(Senha senha) {
        entityManager
                .createQuery("""
                    update Senha s set s.senha = :novaSenha where s.id = :id
                """)
                .setParameter("novaSenha", senha.getSenha())
                .setParameter("id", senha.getId())
                .executeUpdate();
    }

    private Senha buscarPorCliente(Senha senha) {
        return entityManager
                .createQuery("""
                    select s
                    from Senha s
                    join Cliente c on c.senha.id = s.id
                    where c.id = :clienteId
                """, Senha.class)
                .setParameter("clienteId", senha.getCliente().getId())
                .getSingleResult();
    }
}
