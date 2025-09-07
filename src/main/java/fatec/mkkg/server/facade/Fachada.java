package fatec.mkkg.server.facade;

import fatec.mkkg.server.daos.*;
import fatec.mkkg.server.domain.EntidadeDominio;
import fatec.mkkg.server.domain.FachadaRequestDTO;
import fatec.mkkg.server.domain.FachadaResponseDTO;
import fatec.mkkg.server.strategies.IStrategy;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Fachada extends AbstractFachada implements IFachada {

	public Fachada(BandeiraDAO bandeiraDAO, CartaoCreditoDAO cartaoCreditoDAO, ClienteDAO clienteDAO,
			EnderecoDAO enderecoDAO, SenhaDAO senhaDAO, LoginDAO loginDAO) {
		super(bandeiraDAO, cartaoCreditoDAO, clienteDAO, enderecoDAO, senhaDAO, loginDAO);
	}

	private void processarRegras(EntidadeDominio entidade, List<IStrategy> regrasEntidade, List<String> mensagens) {
		for (IStrategy rn : regrasEntidade) {
			String res = rn.processar(entidade);

			if (res != null) {
				mensagens.add(res);
			}
		}
	}

	@Override
	public FachadaResponseDTO salvar(FachadaRequestDTO request) {
		super.inicializarSalvar();

		EntidadeDominio entidade = request.getEntidade();

		List<String> mensagens = new ArrayList<>();
		FachadaResponseDTO response = new FachadaResponseDTO();

		String nomeEntidade = entidade.getClass().getName();
		List<IStrategy> regrasEntidade = rns.get(nomeEntidade);
		IDAO dao = daos.get(nomeEntidade);

		processarRegras(entidade, regrasEntidade, mensagens);

		if (mensagens.isEmpty()) {
			dao.salvar(entidade);
		}
		else {
			response.setMensagens(mensagens);
		}

		return response;
	}

	@Override
	public FachadaResponseDTO alterar(FachadaRequestDTO request) {
		super.inicializarAlterar();

		EntidadeDominio entidade = request.getEntidade();

		List<String> mensagens = new ArrayList<>();
		FachadaResponseDTO response = new FachadaResponseDTO();

		String nomeEntidade = entidade.getClass().getName();
		List<IStrategy> regrasEntidade = rns.get(nomeEntidade);
		IDAO dao = daos.get(nomeEntidade);

		processarRegras(entidade, regrasEntidade, mensagens);

		if (mensagens.isEmpty()) {
			dao.alterar(entidade);
		}
		else {
			response.setMensagens(mensagens);
		}

		return response;
	}

	@Override
	public FachadaResponseDTO excluir(FachadaRequestDTO request) {
		super.inicializarExcluir();

		EntidadeDominio entidade = request.getEntidade();

		List<String> mensagens = new ArrayList<>();
		FachadaResponseDTO response = new FachadaResponseDTO();

		String nomeEntidade = entidade.getClass().getName();
		List<IStrategy> regrasEntidade = rns.get(nomeEntidade);
		IDAO dao = daos.get(nomeEntidade);

		processarRegras(entidade, regrasEntidade, mensagens);

		if (mensagens.isEmpty()) {
			dao.excluir(entidade);
		}
		else {
			response.setMensagens(mensagens);
		}

		return response;
	}

	@Override
	public FachadaResponseDTO consultar(FachadaRequestDTO request) {
		super.inicializarConsultar();

		EntidadeDominio entidade = request.getEntidade();

		List<String> mensagens = new ArrayList<>();
		FachadaResponseDTO response = new FachadaResponseDTO();

		String nomeEntidade = entidade.getClass().getName();
		List<IStrategy> regrasEntidade = rns.get(nomeEntidade);
		IDAO dao = daos.get(nomeEntidade);

		processarRegras(entidade, regrasEntidade, mensagens);

		response.setEntidades(dao.consultar(entidade));
		response.setMensagens(mensagens);

		return response;
	}

}
