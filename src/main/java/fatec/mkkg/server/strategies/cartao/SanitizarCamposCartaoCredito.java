package fatec.mkkg.server.strategies.cartao;

import fatec.mkkg.server.domain.EntidadeDominio;
import fatec.mkkg.server.domain.cartao.CartaoCredito;
import fatec.mkkg.server.strategies.IStrategy;
import fatec.mkkg.server.util.Sanitizacao;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SanitizarCamposCartaoCredito implements IStrategy {

	private void aplicarTrim(CartaoCredito cartaoCredito) {
		cartaoCredito.setNomeTitular(Sanitizacao.trim(cartaoCredito.getNomeTitular()));
		cartaoCredito.setNumero(Sanitizacao.trim(cartaoCredito.getNumero()));
		cartaoCredito.setCodigoSeguranca(Sanitizacao.trim(cartaoCredito.getCodigoSeguranca()));
	}

	@Override
	public List<String> processar(EntidadeDominio entidade) {
		CartaoCredito cartaoCredito = (CartaoCredito) entidade;

		aplicarTrim(cartaoCredito);

		return List.of();
	}
}
