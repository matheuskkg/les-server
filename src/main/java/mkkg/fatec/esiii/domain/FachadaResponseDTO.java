package mkkg.fatec.esiii.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class FachadaResponseDTO {

    private List<String> mensagens = new ArrayList<>();

    private List<EntidadeDominio> entidades = new ArrayList<>();

}
