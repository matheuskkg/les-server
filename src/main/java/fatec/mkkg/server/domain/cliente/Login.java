package fatec.mkkg.server.domain.cliente;

import fatec.mkkg.server.domain.EntidadeDominio;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Login extends EntidadeDominio {

	private String email;

	private String senha;

}
