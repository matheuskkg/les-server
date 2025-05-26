package mkkg.fatec.esiii.domain.cliente;

import mkkg.fatec.esiii.domain.IRequestDTO;

public record ClienteConsultarRequestDTO(
        String nome,

        String cpf,

        String email
) implements IRequestDTO<Cliente> {
    public Cliente toEntity() {
        return Cliente.builder()
                .nome(nome)
                .cpf(cpf)
                .email(email)
                .build();
    }
}
