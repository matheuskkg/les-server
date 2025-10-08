package fatec.mkkg.server.domain.pagamento.formas.cupom;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TipoCupom {

	TROCA("Troca"),
	PROMOCIONAL("Promocional");

	private final String tipo;

}
