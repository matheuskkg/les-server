package fatec.mkkg.server.controllers;

import fatec.mkkg.server.domain.FachadaRequestDTO;
import fatec.mkkg.server.domain.FachadaResponseDTO;
import fatec.mkkg.server.domain.OperacaoCRUD;
import fatec.mkkg.server.domain.cliente.Cliente;
import fatec.mkkg.server.domain.endereco.Endereco;
import fatec.mkkg.server.facade.Fachada;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/endereco")
public class EnderecoController {

    @Autowired
    private Fachada fachada;

    @PostMapping
    public ResponseEntity salvar(@RequestBody Endereco request) {
        FachadaRequestDTO fachadaRequestDTO = new FachadaRequestDTO(request, OperacaoCRUD.SALVAR);

        FachadaResponseDTO fachadaResponseDTO = fachada.salvar(fachadaRequestDTO);

        HttpStatus responseStatus = fachadaResponseDTO.getMensagens().isEmpty() ? HttpStatus.CREATED : HttpStatus.BAD_REQUEST;

        return ResponseEntity.status(responseStatus).body(fachadaResponseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity alterar(@PathVariable Integer id, @RequestBody Endereco request) {
        request.setId(id);

        FachadaRequestDTO fachadaRequestDTO = new FachadaRequestDTO(request, OperacaoCRUD.ALTERAR);

        FachadaResponseDTO fachadaResponseDTO = fachada.alterar(fachadaRequestDTO);

        HttpStatus responseStatus = fachadaResponseDTO.getMensagens().isEmpty() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;

        return ResponseEntity.status(responseStatus).body(fachadaResponseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity excluir(@PathVariable Integer id) {
        Endereco endereco = new Endereco(id);

        FachadaRequestDTO fachadaRequestDTO = new FachadaRequestDTO(endereco, OperacaoCRUD.EXCLUIR);

        FachadaResponseDTO fachadaResponseDTO = fachada.excluir(fachadaRequestDTO);

        HttpStatus responseStatus = fachadaResponseDTO.getMensagens().isEmpty() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;

        return ResponseEntity.status(responseStatus).body(fachadaResponseDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity consultar(@PathVariable Integer id) {
        Cliente cliente = new Cliente(id);
        Endereco endereco = new Endereco();
        endereco.setCliente(cliente);

        FachadaRequestDTO fachadaRequestDTO = new FachadaRequestDTO(endereco, OperacaoCRUD.CONSULTAR);

        FachadaResponseDTO fachadaResponseDTO = fachada.consultar(fachadaRequestDTO);

        HttpStatus responseStatus = fachadaResponseDTO.getEntidades().isEmpty() ? HttpStatus.NO_CONTENT : HttpStatus.OK;

        return ResponseEntity.status(responseStatus).body(fachadaResponseDTO);
    }
}