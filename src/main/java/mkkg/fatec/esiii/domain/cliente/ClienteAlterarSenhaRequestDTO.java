package mkkg.fatec.esiii.domain.cliente;

import jakarta.validation.constraints.NotBlank;

public record ClienteAlterarSenhaRequestDTO(
        @NotBlank(message = "A senha é obrigatória")
        String senha,

        @NotBlank(message = "A senha (confirmar) é obrigatória")
        String senhaConfirmar
) {
    public Cliente toEntity() {
        return Cliente.builder()
                .senha(senha)
                .senhaConfirmar(senhaConfirmar)
                .build();
    }
}
