package fatec.mkkg.server.facade;

import fatec.mkkg.server.daos.*;
import fatec.mkkg.server.domain.cartao.Bandeira;
import fatec.mkkg.server.domain.cartao.CartaoCredito;
import fatec.mkkg.server.domain.cliente.Cliente;
import fatec.mkkg.server.domain.endereco.Cidade;
import fatec.mkkg.server.domain.endereco.Endereco;
import fatec.mkkg.server.domain.endereco.Estado;
import fatec.mkkg.server.domain.endereco.Pais;
import fatec.mkkg.server.domain.telefone.Telefone;
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

    private BandeiraDAO bandeiraDAO;

    private CartaoCreditoDAO cartaoCreditoDAO;

    private CidadeDAO cidadeDAO;

    private ClienteDAO clienteDAO;

    private EnderecoDAO enderecoDAO;

    private EstadoDAO estadoDAO;

    private PaisDAO paisDAO;

    private TelefoneDAO telefoneDAO;

    @Autowired
    private ValidarCamposCartaoCredito validarCamposCartaoCredito;

    @Autowired
    private CriptografarSenha criptografarSenha;

    @Autowired
    private PrepararParaAlterarCliente prepararParaAlterarCliente;

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
    public AbstractFachada(BandeiraDAO bandeiraDAO, CartaoCreditoDAO cartaoCreditoDAO, CidadeDAO cidadeDAO, ClienteDAO clienteDAO, EnderecoDAO enderecoDAO, EstadoDAO estadoDAO, PaisDAO paisDAO, TelefoneDAO telefoneDAO) {
        this.bandeiraDAO = bandeiraDAO;
        this.cartaoCreditoDAO = cartaoCreditoDAO;
        this.cidadeDAO = cidadeDAO;
        this.clienteDAO = clienteDAO;
        this.enderecoDAO = enderecoDAO;
        this.estadoDAO = estadoDAO;
        this.paisDAO = paisDAO;
        this.telefoneDAO = telefoneDAO;

        daos.put(Bandeira.class.getName(), this.bandeiraDAO);
        daos.put(CartaoCredito.class.getName(), this.cartaoCreditoDAO);
        daos.put(Cidade.class.getName(), this.cidadeDAO);
        daos.put(Cliente.class.getName(), this.clienteDAO);
        daos.put(Endereco.class.getName(), this.enderecoDAO);
        daos.put(Estado.class.getName(), this.estadoDAO);
        daos.put(Pais.class.getName(), this.paisDAO);
        daos.put(Telefone.class.getName(), this.telefoneDAO);
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
        List<IStrategy> rnsAlterarCliente = List.of(prepararParaAlterarCliente, validarCamposCliente, validarDataNascimento, validarExistenciaCpf, validarExistenciaEmail, validarCamposTelefone);
        rns.put(Cliente.class.getName(), rnsAlterarCliente);

        List<IStrategy> rnsAlterarEndereco = List.of(validarCamposEndereco, validarMinimoEnderecoCobranca, validarMinimoEnderecoEntrega);
        rns.put(Endereco.class.getName(), rnsAlterarEndereco);

        List<IStrategy> rnsAlterarCartaoCredito = List.of(validarCamposCartaoCredito);
        rns.put(CartaoCredito.class.getName(), rnsAlterarCartaoCredito);
    }

    protected void inicializarAlterarSenha() {
        List<IStrategy> rnsAlterarSenhaCliente = List.of(validarCamposClienteSenha, validarForcaSenha, validarConfirmarSenha, criptografarSenha);
        rns.put(Cliente.class.getName(), rnsAlterarSenhaCliente);
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

        List<IStrategy> rnsConsultarCidade = List.of();
        rns.put(Cidade.class.getName(), rnsConsultarCidade);

        List<IStrategy> rnsConsultarEstado = List.of();
        rns.put(Estado.class.getName(), rnsConsultarEstado);

        List<IStrategy> rnsConsultarPais = List.of();
        rns.put(Pais.class.getName(), rnsConsultarPais);
    }
}
