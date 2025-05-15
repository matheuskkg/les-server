package mkkg.fatec.esiii.strategies;

import mkkg.fatec.esiii.domain.EntidadeDominio;

import java.util.List;

public interface IStrategy {

    List<String> processar(EntidadeDominio entidade);

}
