package fatec.mkkg.server.controllers;

import fatec.mkkg.server.domain.FachadaRequestDTO;
import fatec.mkkg.server.domain.FachadaResponseDTO;
import fatec.mkkg.server.domain.OperacaoCRUD;
import fatec.mkkg.server.domain.cliente.Cliente;
import fatec.mkkg.server.facade.Fachada;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cliente")
@CrossOrigin
public class ClienteController {

    @Autowired
    private Fachada fachada;

    @PostMapping
    public ResponseEntity salvar(@RequestBody Cliente request) {
        FachadaRequestDTO fachadaRequestDTO = new FachadaRequestDTO(request, OperacaoCRUD.SALVAR);

        FachadaResponseDTO fachadaResponseDTO = fachada.salvar(fachadaRequestDTO);

        HttpStatus responseStatus = fachadaResponseDTO.getMensagens().isEmpty() ? HttpStatus.CREATED : HttpStatus.BAD_REQUEST;

        return ResponseEntity.status(responseStatus).body(fachadaResponseDTO);
    }

    @PatchMapping("/{id}")
    public ResponseEntity alterar(@PathVariable Integer id, @RequestBody Cliente request) {
        request.setId(id);

        FachadaRequestDTO fachadaRequestDTO = new FachadaRequestDTO(request, OperacaoCRUD.ALTERAR);

        FachadaResponseDTO fachadaResponseDTO = fachada.alterar(fachadaRequestDTO);

        HttpStatus responseStatus = fachadaResponseDTO.getMensagens().isEmpty() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;

        return ResponseEntity.status(responseStatus).body(fachadaResponseDTO);
    }

    @PatchMapping("/{id}/senha")
    public ResponseEntity alterarSenha(@PathVariable Integer id, @RequestBody Cliente request) {
        request.setId(id);

        FachadaRequestDTO fachadaRequestDTO = new FachadaRequestDTO(request, OperacaoCRUD.ALTERAR_SENHA);

        FachadaResponseDTO fachadaResponseDTO = fachada.alterar(fachadaRequestDTO);

        HttpStatus responseStatus = fachadaResponseDTO.getMensagens().isEmpty() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;

        return ResponseEntity.status(responseStatus).body(fachadaResponseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity inativar(@PathVariable Integer id) {
        Cliente cliente = new Cliente();
        cliente.setId(id);

        FachadaRequestDTO fachadaRequestDTO = new FachadaRequestDTO(cliente, OperacaoCRUD.EXCLUIR);

        FachadaResponseDTO fachadaResponseDTO = fachada.excluir(fachadaRequestDTO);

        HttpStatus responseStatus = fachadaResponseDTO.getMensagens().isEmpty() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;

        return ResponseEntity.status(responseStatus).body(fachadaResponseDTO);
    }

    @GetMapping
    public ResponseEntity consultar(
            @RequestParam(name = "nome", defaultValue = "", required = false) String nome,
            @RequestParam(name = "cpf", defaultValue = "", required = false) String cpf,
            @RequestParam(name = "email", defaultValue = "", required = false) String email
    ) {
        Cliente cliente = new Cliente();
        cliente.setNome(nome);
        cliente.setCpf(cpf);
        cliente.setEmail(email);

        FachadaRequestDTO fachadaRequestDTO = new FachadaRequestDTO(cliente, OperacaoCRUD.CONSULTAR);

        FachadaResponseDTO fachadaResponseDTO = fachada.consultar(fachadaRequestDTO);

        HttpStatus responseStatus = HttpStatus.OK;

        return ResponseEntity.status(responseStatus).body(fachadaResponseDTO);
    }
}
