package mkkg.fatec.esiii.controllers;

import jakarta.validation.groups.Default;
import mkkg.fatec.esiii.domain.FachadaRequestDTO;
import mkkg.fatec.esiii.domain.FachadaResponseDTO;
import mkkg.fatec.esiii.domain.Operacao;
import mkkg.fatec.esiii.domain.cartao.CartaoCredito;
import mkkg.fatec.esiii.domain.cartao.CartaoCreditoRequestDTO;
import mkkg.fatec.esiii.facade.Fachada;
import mkkg.fatec.esiii.strategies.cartao.ValidarCartaoCredito;
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

@RestController
@RequestMapping("/cartao")
public class CartaoCreditoController {

    @Autowired
    private Fachada fachada;

    @PostMapping
    public ResponseEntity salvar(@RequestBody @Validated({Default.class, ValidarCartaoCredito.class}) CartaoCreditoRequestDTO request, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Validacao.getErrorMessages(result));
        }

        CartaoCredito cartaoCredito = request.toEntity();

        FachadaRequestDTO fachadaRequestDTO = FachadaRequestDTO.builder().entidade(cartaoCredito).operacao(Operacao.SALVAR).build();

        FachadaResponseDTO fachadaResponseDTO = fachada.salvar(fachadaRequestDTO);

        HttpStatus responseStatus = fachadaResponseDTO.getMensagens().isEmpty() ? HttpStatus.CREATED : HttpStatus.BAD_REQUEST;

        return ResponseEntity.status(responseStatus).body(fachadaResponseDTO);
    }
}
