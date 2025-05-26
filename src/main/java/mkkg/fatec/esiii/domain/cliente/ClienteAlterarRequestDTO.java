package mkkg.fatec.esiii.domain.cliente;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import mkkg.fatec.esiii.domain.IRequestDTO;
import mkkg.fatec.esiii.domain.telefone.TelefoneRequestDTO;

import java.time.LocalDate;

public record ClienteAlterarRequestDTO(
        @NotBlank(message = "O nome é obrigatório")
        String nome,

        @NotNull(message = "A data de nascimento é obrigatória")
        @Past(message = "Data de nascimento inválida")
        LocalDate dataNascimento,

        @NotBlank(message = "O gênero é obrigatório")
        String genero,

        @NotBlank(message = "O CPF é obrigatório")
        String cpf,

        @NotBlank(message = "O email é obrigatório")
        @Email(message = "E-mail inválido")
        String email,

        @Valid
        TelefoneRequestDTO telefone
) implements IRequestDTO<Cliente> {
    public Cliente toEntity() {
        return Cliente.builder()
                .nome(nome)
                .dataNascimento(dataNascimento)
                .genero(genero)
                .cpf(cpf)
                .email(email)
                .telefone(telefone.toEntity())
                .build();
    }
}
