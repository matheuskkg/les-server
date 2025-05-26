package mkkg.fatec.esiii.domain.endereco;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import mkkg.fatec.esiii.domain.IRequestDTO;
import mkkg.fatec.esiii.domain.cliente.Cliente;
import mkkg.fatec.esiii.strategies.endereco.ValidarEndereco;

public record EnderecoRequestDTO(
        @NotBlank(message = "O identificador do endereço é obrigatório")
        String nomeIdentificador,

        @NotBlank(message = "A cidade é obrigatória")
        String cidade,

        @NotBlank(message = "O tipo de logradouro é obrigatório")
        String tipoLogradouro,

        @NotBlank(message = "O logradouro é obrigatório")
        String logradouro,

        @NotBlank(message = "O tipo de residência é obrigatório")
        String tipoResidencia,

        @NotBlank(message = "O número da residência é obrigatório")
        String numero,

        @NotBlank(message = "O bairro é obrigatório")
        String bairro,

        @NotBlank(message = "O CEP é obrigatório")
        String cep,

        String observacao,

        Boolean cobranca,

        Boolean entrega,

        @NotNull(message = "O cliente deve ser enviado no body da requisição", groups = ValidarEndereco.class)
        Cliente cliente
) implements IRequestDTO<Endereco> {
    public Endereco toEntity() {
        return Endereco.builder()
                .nomeIdentificador(nomeIdentificador)
                .cidade(Cidade.builder().nome(cidade).build())
                .tipoLogradouro(TipoLogradouro.builder().tipo(tipoLogradouro).build())
                .logradouro(logradouro)
                .tipoResidencia(TipoResidencia.builder().tipo(tipoResidencia).build())
                .numero(numero)
                .bairro(bairro)
                .cep(cep)
                .observacao(observacao)
                .cobranca(cobranca)
                .entrega(entrega)
                .cliente(cliente)
                .build();
    }
}
