package mkkg.fatec.esiii.domain.endereco;

import lombok.AllArgsConstructor;
import lombok.Getter;
import mkkg.fatec.esiii.domain.EntidadeDominio;

@Getter
@AllArgsConstructor
public class EnderecoResponseDTO extends EntidadeDominio {

    private Integer id;

    private String nomeIdentificador;

    private String cidade;

    private String tipoLogradouro;

    private String logradouro;

    private String tipoResidencia;

    private String numero;

    private String bairro;

    private String cep;

    private String observacao;

    private Boolean cobranca;

    private Boolean entrega;

}
