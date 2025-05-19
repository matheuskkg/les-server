package mkkg.fatec.esiii.controllers;

import jakarta.validation.Valid;
import mkkg.fatec.esiii.daos.ClienteDAO;
import mkkg.fatec.esiii.domain.cliente.Cliente;
import mkkg.fatec.esiii.domain.cliente.ClienteRequestDTO;
import mkkg.fatec.esiii.strategies.IStrategy;
import mkkg.fatec.esiii.strategies.cartao.DefinirCartaoCreditoPreferencial;
import mkkg.fatec.esiii.strategies.cliente.CriptografarSenha;
import mkkg.fatec.esiii.strategies.cliente.ValidarConfirmarSenha;
import mkkg.fatec.esiii.strategies.cliente.ValidarExistenciaCliente;
import mkkg.fatec.esiii.strategies.cliente.ValidarForcaSenha;
import mkkg.fatec.esiii.strategies.endereco.ValidarMinimoEnderecoCobranca;
import mkkg.fatec.esiii.strategies.endereco.ValidarMinimoEnderecoEntrega;
import mkkg.fatec.esiii.util.Validacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    private ClienteDAO dao;

    private List<IStrategy> rns;

    @PostMapping
    public ResponseEntity salvar(@RequestBody @Valid ClienteRequestDTO request, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Validacao.getErrorMessages(result));
        }

        Cliente cliente = request.toEntity();
        cliente.setCadastroAtivo(true);

        rns = List.of(
                new ValidarConfirmarSenha(),
                new ValidarForcaSenha(),
                new CriptografarSenha(),
                new ValidarExistenciaCliente(),
                new ValidarMinimoEnderecoEntrega(),
                new ValidarMinimoEnderecoCobranca(),
                new DefinirCartaoCreditoPreferencial()
        );

        List<String> mensagensErro = rns.stream()
                .map(rn -> rn.processar(cliente))
                .filter(Objects::nonNull)
                .toList();

        if (!mensagensErro.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mensagensErro);
        }

        dao.salvar(cliente);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
