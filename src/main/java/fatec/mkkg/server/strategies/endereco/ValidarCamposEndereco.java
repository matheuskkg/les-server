package fatec.mkkg.server.strategies.endereco;

import fatec.mkkg.server.domain.EntidadeDominio;
import fatec.mkkg.server.domain.cliente.Cliente;
import fatec.mkkg.server.domain.endereco.Endereco;
import fatec.mkkg.server.domain.endereco.TipoLogradouro;
import fatec.mkkg.server.domain.endereco.TipoResidencia;
import fatec.mkkg.server.strategies.IStrategy;
import fatec.mkkg.server.util.Validacao;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ValidarCamposEndereco implements IStrategy {

	private String validarNomeIdentificador(String nomeIdentificador) {
		if (nomeIdentificador == null || nomeIdentificador.isBlank())
			return "O nome identificador é obrigatório.";

		if (nomeIdentificador.length() > 50)
			return "O nome identificador não pode exceder 50 caracteres.";

		return "";
	}

	private String validarPais(String pais) {
		if (pais == null || pais.isBlank())
			return "O país é obrigatório.";

		if (pais.length() > 50)
			return "O país não pode exceder 50 caracteres.";

		return "";
	}

	private String validarEstado(String estado) {
		if (estado == null || estado.isBlank())
			return "O estado é obrigatório.";

		if (estado.length() > 50)
			return "O estado não pode exceder 50 caracteres.";

		return "";
	}

	private String validarCidade(String cidade) {
		if (cidade == null || cidade.isBlank())
			return "A cidade é obrigatória.";

		if (cidade.length() > 50)
			return "A cidade não pode exceder 50 caracteres.";

		return "";
	}

	private String validarTipoLogradouro(TipoLogradouro tipoLogradouro) {
		if (tipoLogradouro == null || tipoLogradouro.getTipo() == null || tipoLogradouro.getTipo().isBlank())
			return "O tipo de logradouro é obrigatório.";

		return "";
	}

	private String validarLogradouro(String logradouro) {
		if (logradouro == null || logradouro.isBlank())
			return "O logradouro é obrigatório.";

		if (logradouro.length() > 100)
			return "O logradouro não pode exceder 100 caracteres.";

		return "";
	}

	private String validarTipoResidencia(TipoResidencia tipoResidencia) {
		if (tipoResidencia == null || tipoResidencia.getTipo() == null || tipoResidencia.getTipo().isBlank())
			return "O tipo de residência é obrigatório.";

		return "";
	}

	private String validarNumero(String numero) {
		if (numero == null || numero.isBlank())
			return "O número é obrigatório.";

		if (numero.length() > 10)
			return "O número não pode exceder 10 caracteres.";

		return "";
	}

	private String validarBairro(String bairro) {
		if (bairro == null || bairro.isBlank())
			return "O bairro é obrigatório.";

		if (bairro.length() > 50)
			return "O bairro não pode exceder 50 caracteres.";

		return "";
	}

	private String validarCep(String cep) {
		if (cep == null || cep.isBlank())
			return "O CEP é obrigatório.";

		if (cep.length() > 8)
			return "O CEP não pode exceder 8 caracteres.";

		return "";
	}

	private String validarCobranca(Boolean cobranca) {
		if (cobranca == null)
			return "O campo de cobrança é obrigatório.";

		return "";
	}

	private String validarEntrega(Boolean entrega) {
		if (entrega == null)
			return "O campo de entrega é obrigatório.";

		return "";
	}

	@Override
	public List<String> processar(EntidadeDominio entidade) {
		Endereco endereco;

		if (entidade instanceof Cliente) {
			endereco = ((Cliente) entidade).getEndereco();
		}
		else {
			endereco = (Endereco) entidade;
		}

		List<String> res = new ArrayList<>();

		Validacao.adicionarErro(res, validarNomeIdentificador(endereco.getNomeIdentificador()));
		Validacao.adicionarErro(res, validarPais(endereco.getPais()));
		Validacao.adicionarErro(res, validarEstado(endereco.getEstado()));
		Validacao.adicionarErro(res, validarCidade(endereco.getCidade()));
		Validacao.adicionarErro(res, validarTipoLogradouro(endereco.getTipoLogradouro()));
		Validacao.adicionarErro(res, validarLogradouro(endereco.getLogradouro()));
		Validacao.adicionarErro(res, validarTipoResidencia(endereco.getTipoResidencia()));
		Validacao.adicionarErro(res, validarNumero(endereco.getNumero()));
		Validacao.adicionarErro(res, validarBairro(endereco.getBairro()));
		Validacao.adicionarErro(res, validarCep(endereco.getCep()));
		Validacao.adicionarErro(res, validarCobranca(endereco.getCobranca()));
		Validacao.adicionarErro(res, validarEntrega(endereco.getEntrega()));

		return res;
	}

}
