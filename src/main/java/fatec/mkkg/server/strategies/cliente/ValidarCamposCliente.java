package fatec.mkkg.server.strategies.cliente;

import fatec.mkkg.server.domain.EntidadeDominio;
import fatec.mkkg.server.domain.cliente.Cliente;
import fatec.mkkg.server.strategies.IStrategy;
import fatec.mkkg.server.util.Validacao;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
public class ValidarCamposCliente implements IStrategy {

	private final Set<String> GENEROS = Set.of("MASCULINO", "FEMININO", "OUTRO");

	private String validarNome(String nome) {
		if (nome == null || nome.isBlank())
			return "O nome é obrigatório.";

		if (nome.length() > 100)
			return "O nome não pode exceder 100 caracteres.";

		return "";
	}

	private String validarDataNascimento(LocalDate dataNascimento) {
		if (dataNascimento == null)
			return "A data de nascimento é obrigatória.";

		if (dataNascimento.isAfter(LocalDate.now()))
			return "A data de nascimento não pode ser no futuro.";

		return "";
	}

	private String validarGenero(String genero) {
		if (genero == null || genero.isBlank())
			return "O gênero é obrigatório.";

		if (!GENEROS.contains(genero.toUpperCase())) {
			return "O gênero deve ser 'Masculino', 'Feminino' ou 'Outro'.";
		}

		return "";
	}

	private String validarCpf(String cpf) {
		if (cpf == null || cpf.isBlank())
			return "O CPF é obrigatório.";

		if (!cpf.matches("\\d{11}"))
			return "O CPF deve conter exatamente 11 dígitos.";

		return "";
	}

	private String validarEmail(String email) {
		if (email == null || email.isBlank())
			return "O email é obrigatório.";

		if (!email.matches(".+@.+"))
			return "O email deve estar em um formato válido.";

		return "";
	}

	@Override
	public List<String> processar(EntidadeDominio entidade) {
		Cliente cliente = (Cliente) entidade;

		List<String> res = new ArrayList<>();

		Validacao.adicionarErro(res, validarNome(cliente.getNome()));
		Validacao.adicionarErro(res, validarDataNascimento(cliente.getDataNascimento()));
		Validacao.adicionarErro(res, validarGenero(cliente.getGenero()));
		Validacao.adicionarErro(res, validarCpf(cliente.getCpf()));
		Validacao.adicionarErro(res, validarEmail(cliente.getEmail()));

		return res;
	}

}
