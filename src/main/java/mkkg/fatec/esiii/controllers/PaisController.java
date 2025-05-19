package mkkg.fatec.esiii.controllers;

import mkkg.fatec.esiii.daos.PaisDAO;
import mkkg.fatec.esiii.domain.endereco.Pais;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pais")
public class PaisController {

    @Autowired
    private PaisDAO dao;

    @GetMapping
    public ResponseEntity consultar() {
        return ResponseEntity.status(HttpStatus.OK).body(dao.consultar(new Pais()));
    }
}
