package fatec.mkkg.server.strategies;

import fatec.mkkg.server.domain.EntidadeDominio;

import java.util.List;

public interface IStrategy {

	List<String> processar(EntidadeDominio entidade);

}
