package mkkg.fatec.esiii.strategies.cliente;

import mkkg.fatec.esiii.domain.EntidadeDominio;
import mkkg.fatec.esiii.domain.cliente.Cliente;
import mkkg.fatec.esiii.strategies.IStrategy;
import mkkg.fatec.esiii.util.Criptografia;

import java.util.List;

public class CriptografarSenha implements IStrategy {
    @Override
    public List<String> processar(EntidadeDominio entidade) {
        Cliente cliente = (Cliente) entidade;

        String senhaCriptografada = Criptografia.criptografar(cliente.getSenha());

        cliente.setSenha(senhaCriptografada);

        return List.of();
    }
}
