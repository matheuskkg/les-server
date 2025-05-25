package mkkg.fatec.esiii.strategies.cliente;

import mkkg.fatec.esiii.domain.EntidadeDominio;
import mkkg.fatec.esiii.domain.cliente.Cliente;
import mkkg.fatec.esiii.repositories.ClienteRepository;
import mkkg.fatec.esiii.strategies.IStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidarExistenciaCliente implements IStrategy {

    @Autowired
    private ClienteRepository repository;

    @Override
    public String processar(EntidadeDominio entidade) {
        Cliente cliente = (Cliente) entidade;

        Cliente verificarCpf = repository.findByCpf(cliente.getCpf());
        Cliente verificarEmail = repository.findByEmail(cliente.getEmail());

        if (verificarCpf != null && !verificarCpf.getId().equals(cliente.getId())) {
            return "CPF já está cadastrado";
        }

        if (verificarEmail != null && !verificarEmail.getId().equals(cliente.getId())) {
            return "E-mail já está cadastrado";
        }

        return null;
    }
}
