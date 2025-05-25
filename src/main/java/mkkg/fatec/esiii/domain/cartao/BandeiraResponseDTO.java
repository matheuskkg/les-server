package mkkg.fatec.esiii.domain.cartao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import mkkg.fatec.esiii.domain.EntidadeDominio;

@Getter
@AllArgsConstructor
public class BandeiraResponseDTO extends EntidadeDominio {

    private Integer id;

    private String bandeira;

}
