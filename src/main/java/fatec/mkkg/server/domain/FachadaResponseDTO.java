package fatec.mkkg.server.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FachadaResponseDTO {

    private List<String> mensagens = new ArrayList<>();

    private List<EntidadeDominio> entidades = new ArrayList<>();

}
