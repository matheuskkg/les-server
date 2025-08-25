package fatec.mkkg.server.dtos.requests;

import fatec.mkkg.server.domain.cliente.Cliente;
import fatec.mkkg.server.domain.cliente.Senha;
import fatec.mkkg.server.domain.endereco.Endereco;
import fatec.mkkg.server.domain.telefone.Telefone;
import fatec.mkkg.server.exceptions.ErroFormatacao;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public record ClienteCadastrarRequest(
		String nome,

		String dataNascimento,

		String genero,

		String cpf,

		String email,

		Senha senha,

		Telefone telefone,

		Endereco endereco
) {
	public Cliente toEntity() {
		try {
			Cliente cliente = new Cliente();
			cliente.setNome(this.nome);
			cliente.setDataNascimento(LocalDate.parse(this.dataNascimento));
			cliente.setGenero(this.genero);
			cliente.setCpf(this.cpf);
			cliente.setEmail(this.email);
			cliente.setSenha(this.senha);
			cliente.setTelefone(this.telefone);
			cliente.setEndereco(this.endereco);
			return cliente;
		} catch (DateTimeParseException e) {
			throw new ErroFormatacao("Data de nascimento em formato inválido. Use o formato YYYY-MM-DD.");
		}
	}
}
