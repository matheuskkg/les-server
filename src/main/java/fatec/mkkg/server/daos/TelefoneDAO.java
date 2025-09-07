package fatec.mkkg.server.daos;

import fatec.mkkg.server.domain.EntidadeDominio;
import fatec.mkkg.server.domain.telefone.Telefone;
import fatec.mkkg.server.domain.telefone.TipoTelefone;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TelefoneDAO implements IDAO {

	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	private TipoTelefoneDAO tipoTelefoneDAO;

	private void complementar(Telefone telefone) {
		if (telefone.getTipoTelefone().getId() == null) {
			telefone.setTipoTelefone((TipoTelefone) tipoTelefoneDAO.consultar(telefone.getTipoTelefone()).getFirst());
		}
	}

	@Transactional
	@Override
	public void salvar(EntidadeDominio entidade) {
		Telefone telefone = (Telefone) entidade;

		complementar(telefone);

		entityManager.persist(telefone);
	}

	@Transactional
	@Override
	public void alterar(EntidadeDominio entidade) {
		Telefone telefone = (Telefone) entidade;

		complementar(telefone);

		Telefone telefoneExistente = (Telefone) consultar(telefone).getFirst();

		telefone.setId(telefoneExistente.getId());

		entityManager.merge(telefone);
	}

	@Transactional
	@Override
	public void excluir(EntidadeDominio entidade) {

	}

	@Override
	public List<EntidadeDominio> consultar(EntidadeDominio entidade) {
		Telefone telefone = (Telefone) entidade;

		if (telefone.getCliente() != null && telefone.getCliente().getId() != null) {
			return List.of(buscarPorCliente(telefone));
		}

		return List.of();
	}

	private Telefone buscarPorCliente(Telefone telefone) {
		return entityManager.createQuery("select t from Telefone t where t.cliente.id = :clienteId", Telefone.class)
			.setParameter("clienteId", telefone.getCliente().getId())
			.getSingleResult();
	}

}
