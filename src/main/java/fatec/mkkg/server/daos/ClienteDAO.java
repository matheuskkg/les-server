package fatec.mkkg.server.daos;

import fatec.mkkg.server.domain.EntidadeDominio;
import fatec.mkkg.server.domain.cliente.Cliente;
import fatec.mkkg.server.domain.cliente.Senha;
import fatec.mkkg.server.domain.endereco.Endereco;
import fatec.mkkg.server.domain.telefone.Telefone;
import fatec.mkkg.server.repositories.ClienteRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

//TODO: refatorar tudo p repository, utilizar o DAO como uma service
@Component
public class ClienteDAO implements IDAO {

	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	private EnderecoDAO enderecoDAO;

	@Autowired
	private TelefoneDAO telefoneDAO;

	@Autowired
	private SenhaDAO senhaDAO;

	@Autowired
	private ClienteRepository clienteRepository;

	@Transactional
	@Override
	public void salvar(EntidadeDominio entidade) {
		Cliente cliente = (Cliente) entidade;
		Endereco endereco = cliente.getEndereco();
		Telefone telefone = cliente.getTelefone();
		Senha senha = cliente.getSenha();

		senhaDAO.salvar(senha);

		cliente = entityManager.merge(cliente);

		endereco.setCliente(cliente);
		enderecoDAO.salvar(endereco);

		telefone.setCliente(cliente);
		telefoneDAO.salvar(telefone);
	}

	@Transactional
	@Override
	public void alterar(EntidadeDominio entidade) {
		Cliente cliente = (Cliente) entidade;

		Telefone telefone = cliente.getTelefone();
		telefone.setCliente(cliente);
		telefoneDAO.alterar(telefone);
		alterarCadastroCliente(cliente);
	}

	@Transactional
	@Override
	public void excluir(EntidadeDominio entidade) {
		Cliente cliente = (Cliente) entidade;

		inativarCliente(cliente);
	}

	@Override
	public List<EntidadeDominio> consultar(EntidadeDominio entidade) {
		Cliente cliente = (Cliente) entidade;

		if (cliente.getId() != null) {
			return List.of(clienteRepository.buscarPorId(cliente.getId()));
		}

		return List
			.copyOf(clienteRepository.buscarPorClienteFiltro(cliente.getNome(), cliente.getEmail(), cliente.getCpf()));
	}

	private void alterarCadastroCliente(Cliente cliente) {
		entityManager.createQuery("""
				    update Cliente c set
				        c.nome = :nome,
				        c.dataNascimento = :dataNascimento,
				        c.genero = :genero,
				        c.cpf = :cpf,
				        c.email = :email
				    where c.id = :id
				""")
			.setParameter("nome", cliente.getNome())
			.setParameter("dataNascimento", cliente.getDataNascimento())
			.setParameter("genero", cliente.getGenero())
			.setParameter("cpf", cliente.getCpf())
			.setParameter("email", cliente.getEmail())
			.setParameter("id", cliente.getId())
			.executeUpdate();
	}

	private void inativarCliente(Cliente cliente) {
		entityManager.createQuery("""
				    update Cliente c set
				        c.cadastroAtivo = false
				    where c.id = :id
				""").setParameter("id", cliente.getId()).executeUpdate();
	}

	public Cliente buscarPorCpf(Cliente cliente) {
		try {
			return entityManager.createQuery(
					"select new fatec.mkkg.server.domain.cliente.Cliente(c.id) from Cliente c where c.cpf = :cpfCliente",
					Cliente.class)
				.setParameter("cpfCliente", cliente.getCpf())
				.getSingleResult();
		}
		catch (NoResultException e) {
			return new Cliente();
		}
	}

	public Cliente buscarPorEmail(Cliente cliente) {
		try {
			return entityManager.createQuery(
					"select new fatec.mkkg.server.domain.cliente.Cliente(c.id) from Cliente c where c.email = :emailCliente",
					Cliente.class)
				.setParameter("emailCliente", cliente.getEmail())
				.getSingleResult();
		}
		catch (NoResultException e) {
			return new Cliente();
		}
	}

	public Cliente buscarPorEmailParaLogin(String email) {
		try {
			return entityManager
				.createQuery(
						"select c from Cliente c join fetch c.senha where c.email = :email and c.cadastroAtivo = true",
						Cliente.class)
				.setParameter("email", email)
				.getSingleResult();
		}
		catch (NoResultException e) {
			return null;
		}
	}

}
