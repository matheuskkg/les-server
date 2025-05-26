package mkkg.fatec.esiii.domain.endereco;

import mkkg.fatec.esiii.domain.IRequestDTO;
import mkkg.fatec.esiii.domain.cliente.Cliente;

public record EnderecoConsultarRequestDTO(
        Cliente cliente
) implements IRequestDTO<Endereco> {
    @Override
    public Endereco toEntity() {
        return Endereco.builder()
                .cliente(cliente)
                .build();
    }
}
