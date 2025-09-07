package fatec.mkkg.server.daos;

import fatec.mkkg.server.domain.EntidadeDominio;
import fatec.mkkg.server.domain.cliente.Cliente;
import fatec.mkkg.server.domain.cliente.Login;
import fatec.mkkg.server.domain.auth.TokenResponse;
import fatec.mkkg.server.exceptions.InvalidCredentialsException;
import fatec.mkkg.server.util.Criptografia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Component
public class LoginDAO implements IDAO {

	@Autowired
	private ClienteDAO clienteDAO;

	@Autowired
	private JwtEncoder jwtEncoder;

	@Override
	public void salvar(EntidadeDominio entidade) {

	}

	@Override
	public void alterar(EntidadeDominio entidade) {

	}

	@Override
	public void excluir(EntidadeDominio entidade) {

	}

	@Override
	public List<EntidadeDominio> consultar(EntidadeDominio entidade) {
		Login login = (Login) entidade;

		if (login.getEmail() == null || login.getSenha() == null) {
			throw new InvalidCredentialsException();
		}

		Cliente cliente = clienteDAO.buscarPorEmailParaLogin(login.getEmail());

		if (cliente == null || cliente.getSenha() == null
				|| !Criptografia.verificarCriptografia(login.getSenha(), cliente.getSenha().getSenha())) {
			throw new InvalidCredentialsException();
		}

		String token = gerarToken(cliente);
		TokenResponse tokenResponse = new TokenResponse(token);

		return List.of(tokenResponse);
	}

	private String gerarToken(Cliente cliente) {
		Instant now = Instant.now();
		JwtClaimsSet claims = JwtClaimsSet.builder()
			.subject(cliente.getId().toString())
			.issuedAt(now)
			.expiresAt(now.plus(1, ChronoUnit.HOURS))
			.claim("nome", cliente.getNome())
			.claim("email", cliente.getEmail())
			.build();
		return jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
	}

}
