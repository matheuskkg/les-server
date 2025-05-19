package mkkg.fatec.esiii.controllers;

import mkkg.fatec.esiii.daos.CidadeDAO;
import mkkg.fatec.esiii.domain.endereco.Cidade;
import mkkg.fatec.esiii.domain.endereco.Estado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cidade")
public class CidadeController {

    @Autowired
    private CidadeDAO dao;

    @GetMapping
    public ResponseEntity consultar(@RequestParam(name = "estado") String nomeEstado) {
        Estado estado = Estado.builder().nome(nomeEstado).build();
        Cidade cidade = Cidade.builder().estado(estado).build();

        return ResponseEntity.status(HttpStatus.OK).body(dao.consultar(cidade));
    }
}
