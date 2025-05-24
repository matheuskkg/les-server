package mkkg.fatec.esiii.daos;

import jakarta.transaction.Transactional;
import mkkg.fatec.esiii.domain.EntidadeDominio;
import mkkg.fatec.esiii.domain.cliente.Cliente;
import mkkg.fatec.esiii.domain.endereco.Endereco;
import mkkg.fatec.esiii.domain.telefone.Telefone;
import mkkg.fatec.esiii.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ClienteDAO implements IDAO {

    @Autowired
    private ClienteRepository repository;

    @Autowired
    private TelefoneDAO telefoneDAO;

    @Autowired
    private EnderecoDAO enderecoDAO;

    @Transactional
    @Override
    public void salvar(EntidadeDominio entidade) {
        Cliente cliente = (Cliente) entidade;
        cliente = repository.save(cliente);

        Telefone telefone = cliente.getTelefone();
        telefone.setCliente(cliente);
        telefoneDAO.salvar(telefone);

        for (Endereco endereco : cliente.getEnderecos()) {
            endereco.setCliente(cliente);
            enderecoDAO.salvar(endereco);
        }
    }

    @Override
    public void atualizar(EntidadeDominio entidade) {

    }

    @Transactional
    @Override
    public void excluir(EntidadeDominio entidade) {
        Cliente cliente = (Cliente) entidade;

        cliente.setCadastroAtivo(false);

        repository.inativarCadastroCliente(cliente.getId());
    }

    @Override
    public List<EntidadeDominio> consultar(EntidadeDominio entidade) {
        return List.of();
    }
}
