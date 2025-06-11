package fatec.mkkg.server.controllers;

import fatec.mkkg.server.domain.FachadaRequestDTO;
import fatec.mkkg.server.domain.FachadaResponseDTO;
import fatec.mkkg.server.domain.OperacaoCRUD;
import fatec.mkkg.server.domain.endereco.Estado;
import fatec.mkkg.server.domain.endereco.Pais;
import fatec.mkkg.server.facade.Fachada;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/estado")
@CrossOrigin
public class EstadoController {

    @Autowired
    private Fachada fachada;

    @GetMapping
    public ResponseEntity consultar(@RequestParam(name = "pais") String nomePais) {
        Pais pais = new Pais(nomePais);
        Estado estado = new Estado(pais);

        FachadaRequestDTO fachadaRequestDTO = new FachadaRequestDTO(estado, OperacaoCRUD.CONSULTAR);

        FachadaResponseDTO fachadaResponseDTO = fachada.consultar(fachadaRequestDTO);

        HttpStatus responseStatus = HttpStatus.OK;

        return ResponseEntity.status(responseStatus).body(fachadaResponseDTO);
    }
}