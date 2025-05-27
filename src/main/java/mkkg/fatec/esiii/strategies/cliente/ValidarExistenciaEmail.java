package mkkg.fatec.esiii.strategies.cliente;

import mkkg.fatec.esiii.domain.EntidadeDominio;
import mkkg.fatec.esiii.domain.cliente.Cliente;
import mkkg.fatec.esiii.repositories.ClienteRepository;
import mkkg.fatec.esiii.strategies.IStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidarExistenciaEmail implements IStrategy {

    @Autowired
    private ClienteRepository repository;

    @Override
    public String processar(EntidadeDominio entidade) {
        Cliente cliente = (Cliente) entidade;

        Cliente verificarEmail = repository.findByEmail(cliente.getEmail());

        if (verificarEmail != null && !verificarEmail.getId().equals(cliente.getId())) {
            return "E-mail já está cadastrado";
        }

        return null;
    }
}
