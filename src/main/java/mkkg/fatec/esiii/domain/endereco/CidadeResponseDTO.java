package mkkg.fatec.esiii.domain.endereco;

import lombok.AllArgsConstructor;
import lombok.Getter;
import mkkg.fatec.esiii.domain.EntidadeDominio;

@Getter
@AllArgsConstructor
public class CidadeResponseDTO extends EntidadeDominio {

    private Integer id;

    private String nome;

}
