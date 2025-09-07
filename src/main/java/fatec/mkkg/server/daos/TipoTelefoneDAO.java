package fatec.mkkg.server.daos;

import fatec.mkkg.server.domain.EntidadeDominio;
import fatec.mkkg.server.domain.telefone.TipoTelefone;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TipoTelefoneDAO implements IDAO {

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
		TipoTelefone tipoTelefone = (TipoTelefone) entidade;

		if (tipoTelefone.getTipo() != null) {
			return List.of(entityManager
				.createQuery("select t from TipoTelefone t where t.tipo = :tipoTelefone", TipoTelefone.class)
				.setParameter("tipoTelefone", tipoTelefone.getTipo())
				.getSingleResult());
		}

		return List.of();
	}

}
