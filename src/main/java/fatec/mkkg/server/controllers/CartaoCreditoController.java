package fatec.mkkg.server.controllers;

import fatec.mkkg.server.domain.FachadaRequestDTO;
import fatec.mkkg.server.domain.FachadaResponseDTO;
import fatec.mkkg.server.domain.OperacaoCRUD;
import fatec.mkkg.server.domain.cartao.CartaoCredito;
import fatec.mkkg.server.domain.cliente.Cliente;
import fatec.mkkg.server.facade.Fachada;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cartao")
@CrossOrigin
public class CartaoCreditoController {

    @Autowired
    private Fachada fachada;

    @PostMapping
    public ResponseEntity salvar(@RequestBody CartaoCredito request) {
        FachadaRequestDTO fachadaRequestDTO = new FachadaRequestDTO(request, OperacaoCRUD.SALVAR);

        FachadaResponseDTO fachadaResponseDTO = fachada.salvar(fachadaRequestDTO);

        HttpStatus responseStatus = fachadaResponseDTO.getMensagens().isEmpty() ? HttpStatus.CREATED : HttpStatus.BAD_REQUEST;

        return ResponseEntity.status(responseStatus).body(fachadaResponseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity alterar(@PathVariable Integer id, @RequestBody CartaoCredito request) {
        request.setId(id);

        FachadaRequestDTO fachadaRequestDTO = new FachadaRequestDTO(request, OperacaoCRUD.ALTERAR);

        FachadaResponseDTO fachadaResponseDTO = fachada.alterar(fachadaRequestDTO);

        HttpStatus responseStatus = fachadaResponseDTO.getMensagens().isEmpty() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;

        return ResponseEntity.status(responseStatus).body(fachadaResponseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity excluir(@PathVariable Integer id) {
        CartaoCredito cartaoCredito = new CartaoCredito(id);

        FachadaRequestDTO fachadaRequestDTO = new FachadaRequestDTO(cartaoCredito, OperacaoCRUD.EXCLUIR);

        FachadaResponseDTO fachadaResponseDTO = fachada.excluir(fachadaRequestDTO);

        HttpStatus responseStatus = fachadaResponseDTO.getMensagens().isEmpty() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;

        return ResponseEntity.status(responseStatus).body(fachadaResponseDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity consultar(@PathVariable Integer id) {
        Cliente cliente = new Cliente(id);
        CartaoCredito cartaoCredito = new CartaoCredito();
        cartaoCredito.setCliente(cliente);

        FachadaRequestDTO fachadaRequestDTO = new FachadaRequestDTO(cartaoCredito, OperacaoCRUD.CONSULTAR);

        FachadaResponseDTO fachadaResponseDTO = fachada.consultar(fachadaRequestDTO);

        HttpStatus responseStatus = HttpStatus.OK;

        return ResponseEntity.status(responseStatus).body(fachadaResponseDTO);
    }
}
