package fatec.mkkg.server.domain.livro;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Embeddable
@Data
public class Medidas {

	private Double altura;

	private Double largura;

	private Double peso;

	private Double profundidade;

}
