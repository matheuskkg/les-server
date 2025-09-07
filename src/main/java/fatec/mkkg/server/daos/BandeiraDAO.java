package fatec.mkkg.server.daos;

import fatec.mkkg.server.domain.EntidadeDominio;
import fatec.mkkg.server.domain.cartao.Bandeira;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BandeiraDAO implements IDAO {

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
		Bandeira bandeira = (Bandeira) entidade;

		if (bandeira.getBandeira() != null) {
			return List
				.of(entityManager.createQuery("select b from Bandeira b where b.bandeira = :bandeira", Bandeira.class)
					.setParameter("bandeira", bandeira.getBandeira())
					.getSingleResult());
		}

		List<Bandeira> res = entityManager.createQuery("select b from Bandeira b order by bandeira", Bandeira.class)
			.getResultList();

		return List.copyOf(res);
	}

}
