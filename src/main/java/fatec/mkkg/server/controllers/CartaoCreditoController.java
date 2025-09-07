package fatec.mkkg.server.controllers;

import fatec.mkkg.server.domain.FachadaRequestDTO;
import fatec.mkkg.server.domain.FachadaResponseDTO;
import fatec.mkkg.server.domain.cartao.CartaoCredito;
import fatec.mkkg.server.domain.cliente.Cliente;
import fatec.mkkg.server.facade.Fachada;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cartoes")
@CrossOrigin
public class CartaoCreditoController {

	@Autowired
	private Fachada fachada;

	@PostMapping
	public ResponseEntity salvar(@RequestBody CartaoCredito request, @AuthenticationPrincipal Jwt jwt) {
		request.setCliente(new Cliente(Integer.valueOf(jwt.getSubject())));

		FachadaRequestDTO fachadaRequestDTO = new FachadaRequestDTO(request);

		FachadaResponseDTO fachadaResponseDTO = fachada.salvar(fachadaRequestDTO);

		HttpStatus responseStatus = fachadaResponseDTO.getMensagens().isEmpty() ? HttpStatus.CREATED
				: HttpStatus.BAD_REQUEST;

		return ResponseEntity.status(responseStatus).body(fachadaResponseDTO);
	}

	@PatchMapping("/{id}")
	public ResponseEntity alterar(@PathVariable Integer id, @RequestBody CartaoCredito request,
			@AuthenticationPrincipal Jwt jwt) {
		request.setId(id);
		request.setCliente(new Cliente(Integer.valueOf(jwt.getSubject())));

		FachadaRequestDTO fachadaRequestDTO = new FachadaRequestDTO(request);

		FachadaResponseDTO fachadaResponseDTO = fachada.alterar(fachadaRequestDTO);

		HttpStatus responseStatus = fachadaResponseDTO.getMensagens().isEmpty() ? HttpStatus.OK
				: HttpStatus.BAD_REQUEST;

		return ResponseEntity.status(responseStatus).body(fachadaResponseDTO);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity excluir(@PathVariable Integer id) {
		CartaoCredito cartaoCredito = new CartaoCredito(id);

		FachadaRequestDTO fachadaRequestDTO = new FachadaRequestDTO(cartaoCredito);

		FachadaResponseDTO fachadaResponseDTO = fachada.excluir(fachadaRequestDTO);

		HttpStatus responseStatus = fachadaResponseDTO.getMensagens().isEmpty() ? HttpStatus.OK
				: HttpStatus.BAD_REQUEST;

		return ResponseEntity.status(responseStatus).body(fachadaResponseDTO);
	}

	@GetMapping("/{id}")
	public ResponseEntity consultar(@PathVariable Integer id) {
		CartaoCredito cartaoCredito = new CartaoCredito(id);

		FachadaRequestDTO fachadaRequestDTO = new FachadaRequestDTO(cartaoCredito);

		FachadaResponseDTO fachadaResponseDTO = fachada.consultar(fachadaRequestDTO);

		HttpStatus responseStatus = HttpStatus.OK;

		return ResponseEntity.status(responseStatus).body(fachadaResponseDTO);
	}

}
