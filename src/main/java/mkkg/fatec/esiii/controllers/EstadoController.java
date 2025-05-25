package mkkg.fatec.esiii.controllers;

import mkkg.fatec.esiii.domain.FachadaRequestDTO;
import mkkg.fatec.esiii.domain.FachadaResponseDTO;
import mkkg.fatec.esiii.domain.Operacao;
import mkkg.fatec.esiii.domain.endereco.Estado;
import mkkg.fatec.esiii.domain.endereco.Pais;
import mkkg.fatec.esiii.facade.Fachada;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/estado")
public class EstadoController {

    @Autowired
    private Fachada fachada;

    @GetMapping
    public ResponseEntity consultar(@RequestParam(name = "pais") String nomePais) {
        Pais pais = Pais.builder().nome(nomePais).build();
        Estado estado = Estado.builder().pais(pais).build();

        FachadaRequestDTO fachadaRequestDTO = FachadaRequestDTO.builder().entidade(estado).operacao(Operacao.CONSULTAR).build();

        FachadaResponseDTO fachadaResponseDTO = fachada.consultar(fachadaRequestDTO);

        return ResponseEntity.status(HttpStatus.OK).body(fachadaResponseDTO);
    }
}
