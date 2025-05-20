package mkkg.fatec.esiii.controllers;

import mkkg.fatec.esiii.daos.CartaoCreditoDAO;
import mkkg.fatec.esiii.domain.cartao.CartaoCredito;
import mkkg.fatec.esiii.domain.cartao.CartaoCreditoRequestDTO;
import mkkg.fatec.esiii.strategies.IStrategy;
import mkkg.fatec.esiii.strategies.cartao.DefinirCartaoCreditoPreferencial;
import mkkg.fatec.esiii.util.Validacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/cartao")
public class CartaoCreditoController {

    @Autowired
    private CartaoCreditoDAO dao;

    private List<IStrategy> rns;

    @PostMapping
    public ResponseEntity salvar(@RequestBody @Validated CartaoCreditoRequestDTO request, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Validacao.getErrorMessages(result));
        }

        CartaoCredito cartaoCredito = request.toEntity();

        rns = List.of(
                new DefinirCartaoCreditoPreferencial()
        );

        List<String> mensagensErro = rns.stream()
                .map(rn -> rn.processar(cartaoCredito))
                .filter(Objects::nonNull)
                .toList();

        if (!mensagensErro.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mensagensErro);
        }

        dao.salvar(cartaoCredito);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
