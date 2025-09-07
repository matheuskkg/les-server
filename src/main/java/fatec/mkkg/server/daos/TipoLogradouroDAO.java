package fatec.mkkg.server.daos;

import fatec.mkkg.server.domain.EntidadeDominio;
import fatec.mkkg.server.domain.endereco.TipoLogradouro;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TipoLogradouroDAO implements IDAO {

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
		TipoLogradouro tipoLogradouro = (TipoLogradouro) entidade;

		if (tipoLogradouro.getTipo() != null) {
			return List.of(entityManager
				.createQuery("select t from TipoLogradouro t where t.tipo = :tipoLogradouro", TipoLogradouro.class)
				.setParameter("tipoLogradouro", tipoLogradouro.getTipo())
				.getSingleResult());
		}

		return List.of();
	}

}
