package mkkg.fatec.esiii.controllers;

import jakarta.validation.Valid;
import mkkg.fatec.esiii.daos.EnderecoDAO;
import mkkg.fatec.esiii.domain.endereco.Endereco;
import mkkg.fatec.esiii.domain.endereco.EnderecoRequestDTO;
import mkkg.fatec.esiii.strategies.IStrategy;
import mkkg.fatec.esiii.strategies.endereco.ValidarMinimoEnderecoCobranca;
import mkkg.fatec.esiii.strategies.endereco.ValidarMinimoEnderecoEntrega;
import mkkg.fatec.esiii.util.Validacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/endereco")
public class EnderecoController {

    @Autowired
    private EnderecoDAO dao;

    private List<IStrategy> rns;

    @PostMapping
    public ResponseEntity salvar(@RequestBody @Valid EnderecoRequestDTO request, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Validacao.getErrorMessages(result));
        }

        Endereco endereco = request.toEntity();

        rns = List.of(
                new ValidarMinimoEnderecoCobranca(),
                new ValidarMinimoEnderecoEntrega()
        );

        List<String> mensagensErro = rns.stream()
                .map(rn -> rn.processar(endereco))
                .filter(Objects::nonNull)
                .toList();

        if (!mensagensErro.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mensagensErro);
        }

        dao.salvar(endereco);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
