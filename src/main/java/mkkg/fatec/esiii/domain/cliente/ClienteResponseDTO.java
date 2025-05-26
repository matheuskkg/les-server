package mkkg.fatec.esiii.domain.cliente;

import lombok.AllArgsConstructor;
import lombok.Getter;
import mkkg.fatec.esiii.domain.EntidadeDominio;
import mkkg.fatec.esiii.domain.telefone.TelefoneResponseDTO;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class ClienteResponseDTO extends EntidadeDominio {
    private Integer id;

    private String nome;

    private LocalDate dataNascimento;

    private String genero;

    private String cpf;

    private String email;

    private TelefoneResponseDTO telefone;
}
