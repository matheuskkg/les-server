package fatec.mkkg.server.daos;

import fatec.mkkg.server.domain.EntidadeDominio;
import fatec.mkkg.server.domain.endereco.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EnderecoDAO implements IDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private TipoLogradouroDAO tipoLogradouroDAO;

    @Autowired
    private TipoResidenciaDAO tipoResidenciaDAO;

    private void complementar(Endereco endereco) {
        if (endereco.getTipoLogradouro().getId() == null) {
            endereco.setTipoLogradouro((TipoLogradouro) tipoLogradouroDAO.consultar(endereco.getTipoLogradouro()).getFirst());
        }

        if (endereco.getTipoResidencia().getId() == null) {
            endereco.setTipoResidencia((TipoResidencia) tipoResidenciaDAO.consultar(endereco.getTipoResidencia()).getFirst());
        }
    }

    @Transactional
    @Override
    public void salvar(EntidadeDominio entidade) {
        Endereco endereco = (Endereco) entidade;

        complementar(endereco);

        entityManager.persist(endereco);
    }

    @Transactional
    @Override
    public void alterar(EntidadeDominio entidade) {
        Endereco endereco = (Endereco) entidade;

        complementar(endereco);

        entityManager.merge(endereco);
    }

    @Transactional
    @Override
    public void excluir(EntidadeDominio entidade) {
        Endereco endereco = entityManager.find(Endereco.class, ((Endereco) entidade).getId());
        if (endereco != null) {
            entityManager.remove(endereco);
        }
    }

    @Override
    public List<EntidadeDominio> consultar(EntidadeDominio entidade) {
        Endereco endereco = (Endereco) entidade;

        if (endereco.getCliente() != null && endereco.getCliente().getId() != null) {
            return List.copyOf(buscarPorCliente(endereco));
        }

        if (endereco.getId() != null) {
            return List.of(buscarPorId(endereco));
        }

        return List.of();
    }

    private List<Endereco> buscarPorCliente(Endereco endereco) {
        return entityManager
                .createQuery("""
                    select new fatec.mkkg.server.domain.endereco.Endereco(
                        e.id,
                        e.nomeIdentificador,
                        e.pais,
                        e.estado,
                        e.cidade,
                        e.tipoLogradouro,
                        e.logradouro,
                        e.tipoResidencia,
                        e.numero,
                        e.bairro,
                        e.cep,
                        e.observacao,
                        e.cobranca,
                        e.entrega
                    )
                    from Endereco e
                    where e.cliente.id = :clienteId
                """, Endereco.class)
                .setParameter("clienteId", endereco.getCliente().getId())
                .getResultList();
    }

    private Endereco buscarPorId(Endereco endereco) {
        return entityManager
                .createQuery("""
                    select new fatec.mkkg.server.domain.endereco.Endereco(
                        e.id,
                        e.nomeIdentificador,
                        e.pais,
                        e.estado,
                        e.cidade,
                        e.tipoLogradouro,
                        e.logradouro,
                        e.tipoResidencia,
                        e.numero,
                        e.bairro,
                        e.cep,
                        e.observacao,
                        e.cobranca,
                        e.entrega
                    )
                    from Endereco e
                    where e.id = :id
                """, Endereco.class)
                .setParameter("id", endereco.getId())
                .getSingleResult();
    }

    public List<Endereco> buscarPorClienteCobrancaIdDiferente(Endereco endereco) {
        return entityManager
                .createQuery("""
                    select new fatec.mkkg.server.domain.endereco.Endereco(
                        e.id
                    )
                    from Endereco e
                    where e.cliente.id = :clienteId and cobranca = true and e.id != :enderecoId
                """, Endereco.class)
                .setParameter("clienteId", endereco.getCliente().getId())
                .setParameter("enderecoId", endereco.getId())
                .getResultList();
    }

    public List<Endereco> buscarPorClienteCobranca(Endereco endereco) {
        return entityManager
                .createQuery("""
                    select new fatec.mkkg.server.domain.endereco.Endereco(
                        e.id
                    )
                    from Endereco e
                    where e.cliente.id = :clienteId and e.cobranca = true
                """, Endereco.class)
                .setParameter("clienteId", endereco.getCliente().getId())
                .getResultList();
    }

    public List<Endereco> buscarPorClienteEntregaIdDiferente(Endereco endereco) {
        return entityManager
                .createQuery("""
                    select new fatec.mkkg.server.domain.endereco.Endereco(
                        e.id
                    )
                    from Endereco e
                    where e.cliente.id = :clienteId and entrega = true and e.id != :enderecoId
                """, Endereco.class)
                .setParameter("clienteId", endereco.getCliente().getId())
                .setParameter("enderecoId", endereco.getId())
                .getResultList();
    }

    public List<Endereco> buscarPorClienteEntrega(Endereco endereco) {
        return entityManager
                .createQuery("""
                    select new fatec.mkkg.server.domain.endereco.Endereco(
                        e.id
                    )
                    from Endereco e
                    where e.cliente.id = :clienteId and e.entrega = true
                """, Endereco.class)
                .setParameter("clienteId", endereco.getCliente().getId())
                .getResultList();
    }

    public Endereco complementarParaExcluir(Endereco endereco) {
        return entityManager
                .createQuery("""
                        select new fatec.mkkg.server.domain.endereco.Endereco(
                            e.id,
                            e.cobranca,
                            e.entrega,
                            new fatec.mkkg.server.domain.cliente.Cliente(e.cliente.id)
                        )
                        from Endereco e where e.id = :id
                """, Endereco.class)
		        .setParameter("id", endereco.getId())
		        .getSingleResult();
    }
}
