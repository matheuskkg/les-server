package mkkg.fatec.esiii.domain;

import lombok.*;

/**
 * Utilizado para passar requests do Controller para a Fachada
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FachadaRequestDTO {

    private Operacao operacao;

    private EntidadeDominio entidade;

}
