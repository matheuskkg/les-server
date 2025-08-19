package fatec.mkkg.server.strategies.cliente;

import fatec.mkkg.server.daos.ClienteDAO;
import fatec.mkkg.server.domain.EntidadeDominio;
import fatec.mkkg.server.domain.cliente.Cliente;
import fatec.mkkg.server.strategies.IStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidarExistenciaCpf implements IStrategy {

    @Autowired
    private ClienteDAO clienteDAO;

    @Override
    public String processar(EntidadeDominio entidade) {
        Cliente cliente = (Cliente) entidade;

        Cliente filtro = new Cliente();
        filtro.setCpf(cliente.getCpf());

        Cliente clienteCpf = clienteDAO.buscarPorCpf(filtro);
        if (clienteCpf.getId() != null && !clienteCpf.getId().equals(cliente.getId())) {
            return "CPF já está cadastrado";
        }

        return null;
    }
}
