package mkkg.fatec.esiii.domain.cartao;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import mkkg.fatec.esiii.domain.IRequestDTO;
import mkkg.fatec.esiii.domain.cliente.Cliente;
import mkkg.fatec.esiii.strategies.cartao.ValidarCartaoCredito;

public record CartaoCreditoRequestDTO(
        @NotBlank(message = "A bandeira é obrigatória")
        String bandeira,

        @NotBlank(message = "O nome do titular é obrigatório")
        String nomeTitular,

        @NotBlank(message = "O número do cartão é obrigatório")
        String numero,

        @NotBlank(message = "O código de segurança é obrigatório")
        String codigoSeguranca,

        @NotNull(message = "O campo 'preferencial' é obrigatório")
        Boolean preferencial,

        @NotNull(message = "O cliente deve ser enviado no body da requisição", groups = ValidarCartaoCredito.class)
        Cliente cliente
) implements IRequestDTO<CartaoCredito> {
    public CartaoCredito toEntity() {
        return CartaoCredito.builder()
                .bandeira(Bandeira.builder().bandeira(bandeira).build())
                .nomeTitular(nomeTitular)
                .numero(numero)
                .codigoSeguranca(codigoSeguranca)
                .preferencial(preferencial)
                .cliente(cliente)
                .build();
    }
}
