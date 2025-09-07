package fatec.mkkg.server.daos;

import fatec.mkkg.server.domain.EntidadeDominio;
import fatec.mkkg.server.domain.cartao.Bandeira;
import fatec.mkkg.server.domain.cartao.CartaoCredito;
import fatec.mkkg.server.repositories.CartaoCreditoRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CartaoCreditoDAO implements IDAO {

	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	private BandeiraDAO bandeiraDAO;

	@Autowired
	private CartaoCreditoRepository cartaoCreditoRepository;

	private void complementar(CartaoCredito cartaoCredito) {
		if (cartaoCredito.getBandeira().getId() == null) {
			cartaoCredito.setBandeira((Bandeira) bandeiraDAO.consultar(cartaoCredito.getBandeira()).getFirst());
		}
	}

	@Transactional
	@Override
	public void salvar(EntidadeDominio entidade) {
		CartaoCredito cartaoCredito = (CartaoCredito) entidade;

		complementar(cartaoCredito);

		if (cartaoCredito.getPreferencial()) {
			setPreferencialFalsePorCliente(cartaoCredito);
		}

		entityManager.persist(cartaoCredito);
	}

	@Transactional
	@Override
	public void alterar(EntidadeDominio entidade) {
		CartaoCredito cartaoCredito = (CartaoCredito) entidade;

		complementar(cartaoCredito);

		if (cartaoCredito.getPreferencial()) {
			setPreferencialFalsePorCliente(cartaoCredito);
		}

		entityManager.merge(cartaoCredito);
	}

	@Transactional
	@Override
	public void excluir(EntidadeDominio entidade) {
		CartaoCredito cartaoCredito = entityManager.find(CartaoCredito.class, ((CartaoCredito) entidade).getId());
		if (cartaoCredito != null) {
			entityManager.remove(cartaoCredito);
		}
	}

	@Override
	public List<EntidadeDominio> consultar(EntidadeDominio entidade) {
		CartaoCredito cartaoCredito = (CartaoCredito) entidade;

		if (cartaoCredito.getCliente() != null && cartaoCredito.getCliente().getId() != null) {
			return List.copyOf(cartaoCreditoRepository.buscarPorClienteId(cartaoCredito.getCliente().getId()));
		}

		if (cartaoCredito.getId() != null) {
			return List.of(cartaoCreditoRepository.buscarPorId(cartaoCredito.getId()));
		}

		return List.of();
	}

	private void setPreferencialFalsePorCliente(CartaoCredito cartaoCredito) {
		entityManager.createQuery("""
				    update CartaoCredito c
				    set c.preferencial = false where c.cliente.id = :clienteId
				""").setParameter("clienteId", cartaoCredito.getCliente().getId()).executeUpdate();
	}

}
