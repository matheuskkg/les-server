package mkkg.fatec.esiii.domain.cliente;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import mkkg.fatec.esiii.domain.IRequestDTO;
import mkkg.fatec.esiii.domain.endereco.EnderecoRequestDTO;
import mkkg.fatec.esiii.domain.telefone.TelefoneRequestDTO;

import java.time.LocalDate;
import java.util.List;

public record ClienteCadastrarRequestDTO(
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

        @NotBlank(message = "A senha é obrigatória")
        String senha,

        @NotBlank(message = "A senha (confirmar) é obrigatória")
        String senhaConfirmar,

        @Valid
        TelefoneRequestDTO telefone,

        @Valid
        @NotEmpty
        List<EnderecoRequestDTO> enderecos
) implements IRequestDTO<Cliente> {
    public Cliente toEntity() {
        return Cliente.builder()
                .nome(nome)
                .dataNascimento(dataNascimento)
                .genero(genero)
                .cpf(cpf)
                .email(email)
                .senha(senha)
                .senhaConfirmar(senhaConfirmar)
                .telefone(telefone.toEntity())
                .enderecos(enderecos.stream().map(EnderecoRequestDTO::toEntity).toList())
                .build();
    }
}
