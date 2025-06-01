package fatec.mkkg.server.daos;

import fatec.mkkg.server.domain.EntidadeDominio;

import java.util.List;

public interface IDAO {

    void salvar(EntidadeDominio entidade);
    void alterar(EntidadeDominio entidade);
    void excluir(EntidadeDominio entidade);
    List<EntidadeDominio> consultar(EntidadeDominio entidade);

}
