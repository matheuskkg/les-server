package fatec.mkkg.server.controllers;

import fatec.mkkg.server.domain.FachadaRequestDTO;
import fatec.mkkg.server.domain.FachadaResponseDTO;
import fatec.mkkg.server.domain.cliente.Login;
import fatec.mkkg.server.facade.Fachada;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private Fachada fachada;

	@PostMapping("/login")
	public ResponseEntity login(@RequestBody Login login) {
		FachadaRequestDTO fachadaRequestDTO = new FachadaRequestDTO(login);

		FachadaResponseDTO response = fachada.consultar(fachadaRequestDTO);

		HttpStatus httpStatus = response.getMensagens().isEmpty() ? HttpStatus.OK : HttpStatus.UNAUTHORIZED;

		return ResponseEntity.status(httpStatus).body(response);
	}

}
