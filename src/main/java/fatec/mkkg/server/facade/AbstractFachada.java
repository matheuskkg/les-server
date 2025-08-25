package fatec.mkkg.server.facade;

import fatec.mkkg.server.daos.*;
import fatec.mkkg.server.domain.cartao.Bandeira;
import fatec.mkkg.server.domain.cartao.CartaoCredito;
import fatec.mkkg.server.domain.cliente.Cliente;
import fatec.mkkg.server.domain.cliente.Login;
import fatec.mkkg.server.domain.cliente.Senha;
import fatec.mkkg.server.domain.endereco.Endereco;
import fatec.mkkg.server.strategies.IStrategy;
import fatec.mkkg.server.strategies.cartao.ValidarCamposCartaoCredito;
import fatec.mkkg.server.strategies.cliente.*;
import fatec.mkkg.server.strategies.endereco.*;
import fatec.mkkg.server.strategies.telefone.ValidarCamposTelefone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class AbstractFachada {

    @Autowired
    private ValidarCamposCartaoCredito validarCamposCartaoCredito;

    @Autowired
    private CriptografarSenha criptografarSenha;

    @Autowired
    private SetCadastroAtivo setCadastroAtivo;

    @Autowired
    private ValidarCamposCliente validarCamposCliente;

    @Autowired
    private ValidarCamposClienteSenha validarCamposClienteSenha;

    @Autowired
    private ValidarConfirmarSenha validarConfirmarSenha;

    @Autowired
    private ValidarDataNascimento validarDataNascimento;

    @Autowired
    private ValidarExistenciaCpf validarExistenciaCpf;

    @Autowired
    private ValidarExistenciaEmail validarExistenciaEmail;

    @Autowired
    private ValidarForcaSenha validarForcaSenha;

    @Autowired
    private ComplementarEnderecoParaExcluir complementarEnderecoParaExcluir;

    @Autowired
    private ValidarCamposEndereco validarCamposEndereco;

    @Autowired
    private ValidarEnderecoPodeSerExcluido validarEnderecoPodeSerExcluido;

    @Autowired
    private ValidarMinimoEnderecoCobranca validarMinimoEnderecoCobranca;

    @Autowired
    private ValidarMinimoEnderecoEntrega validarMinimoEnderecoEntrega;

    @Autowired
    private ValidarCamposTelefone validarCamposTelefone;

    protected Map<String, List<IStrategy>> rns = new HashMap<>();

    protected Map<String, IDAO> daos = new HashMap<>();

    @Autowired
    public AbstractFachada(BandeiraDAO bandeiraDAO, CartaoCreditoDAO cartaoCreditoDAO, ClienteDAO clienteDAO, EnderecoDAO enderecoDAO, SenhaDAO senhaDAO, LoginDAO loginDAO) {
        daos.put(Bandeira.class.getName(), bandeiraDAO);
        daos.put(CartaoCredito.class.getName(), cartaoCreditoDAO);
        daos.put(Cliente.class.getName(), clienteDAO);
        daos.put(Endereco.class.getName(), enderecoDAO);
        daos.put(Senha.class.getName(), senhaDAO);
        daos.put(Login.class.getName(), loginDAO);
    }

    protected void inicializarSalvar() {
        List<IStrategy> rnsSalvarCliente = List.of(setCadastroAtivo, validarCamposCliente, validarCamposClienteSenha, validarForcaSenha, validarConfirmarSenha, criptografarSenha, validarDataNascimento, validarExistenciaCpf, validarExistenciaEmail, validarCamposEndereco, validarMinimoEnderecoCobranca, validarMinimoEnderecoEntrega, validarCamposTelefone);
        rns.put(Cliente.class.getName(), rnsSalvarCliente);

        List<IStrategy> rnsSalvarEndereco = List.of(validarCamposEndereco, validarMinimoEnderecoCobranca, validarMinimoEnderecoEntrega);
        rns.put(Endereco.class.getName(), rnsSalvarEndereco);

        List<IStrategy> rnsSalvarCartaoCredito = List.of(validarCamposCartaoCredito);
        rns.put(CartaoCredito.class.getName(), rnsSalvarCartaoCredito);
    }

    protected void inicializarAlterar() {
        List<IStrategy> rnsAlterarCliente = List.of(validarCamposCliente, validarDataNascimento, validarExistenciaCpf, validarExistenciaEmail, validarCamposTelefone);
        rns.put(Cliente.class.getName(), rnsAlterarCliente);

        List<IStrategy> rnsAlterarEndereco = List.of(validarCamposEndereco, validarMinimoEnderecoCobranca, validarMinimoEnderecoEntrega);
        rns.put(Endereco.class.getName(), rnsAlterarEndereco);

        List<IStrategy> rnsAlterarCartaoCredito = List.of(validarCamposCartaoCredito);
        rns.put(CartaoCredito.class.getName(), rnsAlterarCartaoCredito);

        List<IStrategy> rnsAlterarSenha = List.of(validarCamposClienteSenha, validarForcaSenha, validarConfirmarSenha, criptografarSenha);
        rns.put(Senha.class.getName(), rnsAlterarSenha);
    }

    protected void inicializarExcluir() {
        List<IStrategy> rnsExcluirCliente = List.of();
        rns.put(Cliente.class.getName(), rnsExcluirCliente);

        List<IStrategy> rnsExcluirEndereco = List.of(complementarEnderecoParaExcluir, validarEnderecoPodeSerExcluido);
        rns.put(Endereco.class.getName(), rnsExcluirEndereco);

        List<IStrategy> rnsExcluirCartaoCredito = List.of();
        rns.put(CartaoCredito.class.getName(), rnsExcluirCartaoCredito);
    }

    protected void inicializarConsultar() {
        List<IStrategy> rnsConsultarCliente = List.of();
        rns.put(Cliente.class.getName(), rnsConsultarCliente);

        List<IStrategy> rnsConsultarEndereco = List.of();
        rns.put(Endereco.class.getName(), rnsConsultarEndereco);

        List<IStrategy> rnsConsultarCartaoCredito = List.of();
        rns.put(CartaoCredito.class.getName(), rnsConsultarCartaoCredito);

        List<IStrategy> rnsConsultarBandeira = List.of();
        rns.put(Bandeira.class.getName(), rnsConsultarBandeira);

        List<IStrategy> rnsConsularLogin = List.of();
        rns.put(Login.class.getName(), rnsConsularLogin);
    }
}
