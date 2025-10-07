package fatec.mkkg.server.controllers;

import fatec.mkkg.server.domain.FachadaRequestDTO;
import fatec.mkkg.server.domain.FachadaResponseDTO;
import fatec.mkkg.server.domain.venda.Produto;
import fatec.mkkg.server.facade.Fachada;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

	@Autowired
	private Fachada fachada;

	@GetMapping
	public ResponseEntity consultar() {
		FachadaRequestDTO fachadaRequestDTO = new FachadaRequestDTO(new Produto());

		FachadaResponseDTO fachadaResponseDTO = fachada.consultar(fachadaRequestDTO);

		HttpStatus responseStatus = HttpStatus.OK;

		return ResponseEntity.status(responseStatus).body(fachadaResponseDTO);
	}

}
