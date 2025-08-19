package fatec.mkkg.server.strategies.cliente;

import fatec.mkkg.server.daos.ClienteDAO;
import fatec.mkkg.server.domain.EntidadeDominio;
import fatec.mkkg.server.domain.cliente.Cliente;
import fatec.mkkg.server.strategies.IStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidarExistenciaEmail implements IStrategy {

    @Autowired
    private ClienteDAO clienteDAO;

    @Override
    public String processar(EntidadeDominio entidade) {
        Cliente cliente = (Cliente) entidade;

        Cliente filtro = new Cliente();
        filtro.setEmail(cliente.getEmail());

        Cliente clienteEmail = clienteDAO.buscarPorEmail(filtro);
        if (clienteEmail.getId() != null && !clienteEmail.getId().equals(cliente.getId())) {
            return "E-mail já está cadastrado";
        }

        return null;
    }
}
