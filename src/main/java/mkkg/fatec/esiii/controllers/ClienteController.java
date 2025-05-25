package mkkg.fatec.esiii.controllers;

import jakarta.validation.Valid;
import mkkg.fatec.esiii.domain.FachadaRequestDTO;
import mkkg.fatec.esiii.domain.FachadaResponseDTO;
import mkkg.fatec.esiii.domain.Operacao;
import mkkg.fatec.esiii.domain.cliente.Cliente;
import mkkg.fatec.esiii.domain.cliente.ClienteAlterarRequestDTO;
import mkkg.fatec.esiii.domain.cliente.ClienteAlterarSenhaRequestDTO;
import mkkg.fatec.esiii.domain.cliente.ClienteCadastrarRequestDTO;
import mkkg.fatec.esiii.facade.Fachada;
import mkkg.fatec.esiii.util.Validacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    private Fachada fachada;

    @PostMapping
    public ResponseEntity salvar(@RequestBody @Valid ClienteCadastrarRequestDTO request, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Validacao.getErrorMessages(result));
        }

        Cliente cliente = request.toEntity();
        cliente.setCadastroAtivo(true);

        FachadaRequestDTO fachadaRequestDTO = FachadaRequestDTO.builder().entidade(cliente).operacao(Operacao.SALVAR).build();

        FachadaResponseDTO fachadaResponseDTO = fachada.salvar(fachadaRequestDTO);

        HttpStatus responseStatus = fachadaResponseDTO.getMensagens().isEmpty() ? HttpStatus.CREATED : HttpStatus.BAD_REQUEST;

        return ResponseEntity.status(responseStatus).body(fachadaResponseDTO);
    }

    @PatchMapping("/{id}")
    public ResponseEntity alterar(@PathVariable Integer id, @RequestBody @Valid ClienteAlterarRequestDTO request, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Validacao.getErrorMessages(result));
        }

        Cliente cliente = request.toEntity();
        cliente.setId(id);

        FachadaRequestDTO fachadaRequestDTO = FachadaRequestDTO.builder().entidade(cliente).operacao(Operacao.ALTERAR).build();

        FachadaResponseDTO fachadaResponseDTO = fachada.alterar(fachadaRequestDTO);

        HttpStatus responseStatus = fachadaResponseDTO.getMensagens().isEmpty() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;

        return ResponseEntity.status(responseStatus).body(fachadaResponseDTO);
    }

    @PatchMapping("/senha/{id}")
    public ResponseEntity alterarSenha(@PathVariable Integer id, @RequestBody @Valid ClienteAlterarSenhaRequestDTO request, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Validacao.getErrorMessages(result));
        }

        Cliente cliente = request.toEntity();
        cliente.setId(id);

        FachadaRequestDTO fachadaRequestDTO = FachadaRequestDTO.builder().entidade(cliente).operacao(Operacao.ALTERAR_SENHA).build();

        FachadaResponseDTO fachadaResponseDTO = fachada.alterar(fachadaRequestDTO);

        HttpStatus responseStatus = fachadaResponseDTO.getMensagens().isEmpty() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;

        return ResponseEntity.status(responseStatus).body(fachadaResponseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity excluir(@PathVariable Integer id) {
        Cliente cliente = Cliente.builder().id(id).build();

        FachadaRequestDTO fachadaRequestDTO = FachadaRequestDTO.builder().entidade(cliente).operacao(Operacao.EXCLUIR).build();

        FachadaResponseDTO fachadaResponseDTO = fachada.excluir(fachadaRequestDTO);

        HttpStatus responseStatus = fachadaResponseDTO.getMensagens().isEmpty() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;

        return ResponseEntity.status(responseStatus).body(fachadaResponseDTO);
    }
}
