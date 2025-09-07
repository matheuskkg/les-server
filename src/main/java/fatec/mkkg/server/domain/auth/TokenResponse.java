package fatec.mkkg.server.domain.auth;

import fatec.mkkg.server.domain.EntidadeDominio;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TokenResponse extends EntidadeDominio {

	private String token;

}
