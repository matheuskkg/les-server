package fatec.mkkg.server.domain.carrinho;

import fatec.mkkg.server.domain.EntidadeDominio;
import fatec.mkkg.server.domain.cliente.Cliente;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class AlteracaoCarrinhoRequest extends EntidadeDominio {

	@Builder.Default
	private List<ItemCarrinho> edits = new ArrayList<>();

	@Builder.Default
	private List<Integer> removes = new ArrayList<>();

	private Cliente cliente;

}
