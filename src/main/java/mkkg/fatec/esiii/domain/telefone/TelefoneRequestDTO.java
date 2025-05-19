package mkkg.fatec.esiii.domain.telefone;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record TelefoneRequestDTO(
        @Pattern(regexp = "^[0-9]+$", message = "DDD inválido")
        @NotBlank(message = "O DDD é obrigatório")
        String ddd,

        @NotBlank(message = "O tipo de telefone é obrigatório")
        String tipoTelefone,

        @Pattern(regexp = "^[0-9]+$", message = "Número inválido")
        @NotBlank(message = "O numero é obrigatório")
        String numero
) {
    public Telefone toEntity() {
        return Telefone.builder()
                .ddd(ddd)
                .tipoTelefone(TipoTelefone.builder().tipo(tipoTelefone).build())
                .numero(numero)
                .build();
    }
}
