package mkkg.fatec.esiii.domain.telefone;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TelefoneResponseDTO {

    private String ddd;

    private String tipoTelefone;

    private String numero;

}
