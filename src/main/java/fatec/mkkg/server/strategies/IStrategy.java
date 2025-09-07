package fatec.mkkg.server.strategies;

import fatec.mkkg.server.domain.EntidadeDominio;

public interface IStrategy {

	String processar(EntidadeDominio entidade);

}
