package fatec.mkkg.server.controllers;

import fatec.mkkg.server.domain.FachadaRequestDTO;
import fatec.mkkg.server.domain.FachadaResponseDTO;
import fatec.mkkg.server.domain.cartao.CartaoCredito;
import fatec.mkkg.server.domain.cliente.Cliente;
import fatec.mkkg.server.domain.cliente.Senha;
import fatec.mkkg.server.domain.endereco.Endereco;
import fatec.mkkg.server.dtos.requests.ClienteCadastrarRequest;
import fatec.mkkg.server.facade.Fachada;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clientes")
@CrossOrigin
public class ClienteController {

	@Autowired
	private Fachada fachada;

	@PostMapping
	public ResponseEntity salvar(@RequestBody ClienteCadastrarRequest request) {
		FachadaRequestDTO fachadaRequestDTO = new FachadaRequestDTO(request.toEntity());

		FachadaResponseDTO fachadaResponseDTO = fachada.salvar(fachadaRequestDTO);

		HttpStatus responseStatus = fachadaResponseDTO.getMensagens().isEmpty() ? HttpStatus.CREATED
				: HttpStatus.BAD_REQUEST;

		return ResponseEntity.status(responseStatus).body(fachadaResponseDTO);
	}

	@PatchMapping
	public ResponseEntity alterar(@RequestBody Cliente request, @AuthenticationPrincipal Jwt jwt) {
		request.setId(Integer.valueOf(jwt.getSubject()));

		FachadaRequestDTO fachadaRequestDTO = new FachadaRequestDTO(request);

		FachadaResponseDTO fachadaResponseDTO = fachada.alterar(fachadaRequestDTO);

		HttpStatus responseStatus = fachadaResponseDTO.getMensagens().isEmpty() ? HttpStatus.OK
				: HttpStatus.BAD_REQUEST;

		return ResponseEntity.status(responseStatus).body(fachadaResponseDTO);
	}

	@PatchMapping("/senha")
	public ResponseEntity alterarSenha(@RequestBody Senha senha, @AuthenticationPrincipal Jwt jwt) {
		senha.setCliente(new Cliente(Integer.valueOf(jwt.getSubject())));

		FachadaRequestDTO fachadaRequestDTO = new FachadaRequestDTO(senha);

		FachadaResponseDTO fachadaResponseDTO = fachada.alterar(fachadaRequestDTO);

		HttpStatus responseStatus = fachadaResponseDTO.getMensagens().isEmpty() ? HttpStatus.OK
				: HttpStatus.BAD_REQUEST;

		return ResponseEntity.status(responseStatus).body(fachadaResponseDTO);
	}

	@DeleteMapping
	public ResponseEntity inativar(@AuthenticationPrincipal Jwt jwt) {
		Cliente cliente = new Cliente(Integer.valueOf(jwt.getSubject()));

		FachadaRequestDTO fachadaRequestDTO = new FachadaRequestDTO(cliente);

		FachadaResponseDTO fachadaResponseDTO = fachada.excluir(fachadaRequestDTO);

		HttpStatus responseStatus = fachadaResponseDTO.getMensagens().isEmpty() ? HttpStatus.OK
				: HttpStatus.BAD_REQUEST;

		return ResponseEntity.status(responseStatus).body(fachadaResponseDTO);
	}

	@GetMapping
	public ResponseEntity consultar(@RequestParam(name = "nome", defaultValue = "", required = false) String nome,
			@RequestParam(name = "cpf", defaultValue = "", required = false) String cpf,
			@RequestParam(name = "email", defaultValue = "", required = false) String email) {
		Cliente cliente = new Cliente();
		cliente.setNome(nome);
		cliente.setCpf(cpf);
		cliente.setEmail(email);

		FachadaRequestDTO fachadaRequestDTO = new FachadaRequestDTO(cliente);

		FachadaResponseDTO fachadaResponseDTO = fachada.consultar(fachadaRequestDTO);

		HttpStatus responseStatus = HttpStatus.OK;

		return ResponseEntity.status(responseStatus).body(fachadaResponseDTO);
	}

	@GetMapping("/dados-pessoais")
	public ResponseEntity consultarDadosPessoais(@AuthenticationPrincipal Jwt jwt) {
		Cliente cliente = new Cliente(Integer.valueOf(jwt.getSubject()));

		FachadaRequestDTO fachadaRequestDTO = new FachadaRequestDTO(cliente);

		FachadaResponseDTO fachadaResponseDTO = fachada.consultar(fachadaRequestDTO);

		HttpStatus responseStatus = HttpStatus.OK;

		return ResponseEntity.status(responseStatus).body(fachadaResponseDTO);
	}

	@GetMapping("/enderecos")
	public ResponseEntity consultarEnderecos(@AuthenticationPrincipal Jwt jwt) {
		Cliente cliente = new Cliente(Integer.valueOf(jwt.getSubject()));
		Endereco endereco = new Endereco();
		endereco.setCliente(cliente);

		FachadaRequestDTO fachadaRequestDTO = new FachadaRequestDTO(endereco);

		FachadaResponseDTO fachadaResponseDTO = fachada.consultar(fachadaRequestDTO);

		HttpStatus responseStatus = HttpStatus.OK;

		return ResponseEntity.status(responseStatus).body(fachadaResponseDTO);
	}

	@GetMapping("/cartoes")
	public ResponseEntity consultarCartoes(@AuthenticationPrincipal Jwt jwt) {
		Cliente cliente = new Cliente(Integer.valueOf(jwt.getSubject()));
		CartaoCredito cartaoCredito = new CartaoCredito();
		cartaoCredito.setCliente(cliente);

		FachadaRequestDTO fachadaRequestDTO = new FachadaRequestDTO(cartaoCredito);

		FachadaResponseDTO fachadaResponseDTO = fachada.consultar(fachadaRequestDTO);

		HttpStatus responseStatus = HttpStatus.OK;

		return ResponseEntity.status(responseStatus).body(fachadaResponseDTO);
	}

}
