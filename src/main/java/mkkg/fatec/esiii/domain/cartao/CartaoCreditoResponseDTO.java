package mkkg.fatec.esiii.domain.cartao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import mkkg.fatec.esiii.domain.EntidadeDominio;

@Getter
@AllArgsConstructor
public class CartaoCreditoResponseDTO extends EntidadeDominio {

    private Integer id;

    private String bandeira;

    private String nomeTitular;

    private String numero;

    private String codigoSeguranca;

    private Boolean preferencial;

}
