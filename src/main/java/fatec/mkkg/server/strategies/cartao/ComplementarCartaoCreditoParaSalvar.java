package fatec.mkkg.server.strategies.cartao;

import fatec.mkkg.server.domain.EntidadeDominio;
import fatec.mkkg.server.domain.cartao.Bandeira;
import fatec.mkkg.server.domain.cartao.CartaoCredito;
import fatec.mkkg.server.repositories.BandeiraRepository;
import fatec.mkkg.server.strategies.IStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.NoSuchElementException;

@Component
public class ComplementarCartaoCreditoParaSalvar implements IStrategy {

	@Autowired
	private BandeiraRepository bandeiraRepository;

	@Override
	public List<String> processar(EntidadeDominio entidade) {
		CartaoCredito cartaoCredito = (CartaoCredito) entidade;
		Bandeira bandeira = cartaoCredito.getBandeira();

		try {
			if (bandeira != null && bandeira.getId() != null) {
				bandeira = bandeiraRepository.findById(bandeira.getId()).orElseThrow();
				cartaoCredito.setBandeira(bandeira);
			}

			return List.of();
		}
		catch (NoSuchElementException e) {
			return List.of("Bandeira inválida.");
		}
	}

}
