package mkkg.fatec.esiii.facade;

import mkkg.fatec.esiii.daos.*;
import mkkg.fatec.esiii.domain.cartao.Bandeira;
import mkkg.fatec.esiii.domain.cartao.CartaoCredito;
import mkkg.fatec.esiii.domain.cliente.Cliente;
import mkkg.fatec.esiii.domain.endereco.Cidade;
import mkkg.fatec.esiii.domain.endereco.Endereco;
import mkkg.fatec.esiii.domain.endereco.Estado;
import mkkg.fatec.esiii.domain.endereco.Pais;
import mkkg.fatec.esiii.domain.telefone.Telefone;
import mkkg.fatec.esiii.strategies.IStrategy;
import mkkg.fatec.esiii.strategies.cartao.DefinirCartaoCreditoPreferencial;
import mkkg.fatec.esiii.strategies.cliente.CriptografarSenha;
import mkkg.fatec.esiii.strategies.cliente.ValidarConfirmarSenha;
import mkkg.fatec.esiii.strategies.cliente.ValidarExistenciaCliente;
import mkkg.fatec.esiii.strategies.cliente.ValidarForcaSenha;
import mkkg.fatec.esiii.strategies.endereco.ValidarMinimoEnderecoCobranca;
import mkkg.fatec.esiii.strategies.endereco.ValidarMinimoEnderecoEntrega;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public abstract class AbstractFachada {

    private BandeiraDAO bandeiraDAO;

    private CartaoCreditoDAO cartaoCreditoDAO;

    private CidadeDAO cidadeDAO;

    private ClienteDAO clienteDAO;

    private EnderecoDAO enderecoDAO;

    private EstadoDAO estadoDAO;

    private PaisDAO paisDAO;

    private TelefoneDAO telefoneDAO;

    private DefinirCartaoCreditoPreferencial definirCartaoCreditoPreferencial;

    private CriptografarSenha criptografarSenha;

    private ValidarConfirmarSenha validarConfirmarSenha;

    private ValidarExistenciaCliente validarExistenciaCliente;

    private ValidarForcaSenha validarForcaSenha;

    private ValidarMinimoEnderecoCobranca validarMinimoEnderecoCobranca;

    private ValidarMinimoEnderecoEntrega validarMinimoEnderecoEntrega;

    protected Map<String, List<IStrategy>> rns = new HashMap<>();

    protected Map<String, IDAO> daos = new HashMap<>();

    @Autowired
    public AbstractFachada(BandeiraDAO bandeiraDAO, CartaoCreditoDAO cartaoCreditoDAO, CidadeDAO cidadeDAO, ClienteDAO clienteDAO, EnderecoDAO enderecoDAO, EstadoDAO estadoDAO, PaisDAO paisDAO, TelefoneDAO telefoneDAO, DefinirCartaoCreditoPreferencial definirCartaoCreditoPreferencial, CriptografarSenha criptografarSenha, ValidarConfirmarSenha validarConfirmarSenha, ValidarExistenciaCliente validarExistenciaCliente, ValidarForcaSenha validarForcaSenha, ValidarMinimoEnderecoCobranca validarMinimoEnderecoCobranca, ValidarMinimoEnderecoEntrega validarMinimoEnderecoEntrega) {
        this.bandeiraDAO = bandeiraDAO;
        this.cartaoCreditoDAO = cartaoCreditoDAO;
        this.cidadeDAO = cidadeDAO;
        this.clienteDAO = clienteDAO;
        this.enderecoDAO = enderecoDAO;
        this.estadoDAO = estadoDAO;
        this.paisDAO = paisDAO;
        this.telefoneDAO = telefoneDAO;
        this.definirCartaoCreditoPreferencial = definirCartaoCreditoPreferencial;
        this.criptografarSenha = criptografarSenha;
        this.validarConfirmarSenha = validarConfirmarSenha;
        this.validarExistenciaCliente = validarExistenciaCliente;
        this.validarForcaSenha = validarForcaSenha;
        this.validarMinimoEnderecoCobranca = validarMinimoEnderecoCobranca;
        this.validarMinimoEnderecoEntrega = validarMinimoEnderecoEntrega;

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
        List<IStrategy> rnsSalvarCliente = List.of(validarConfirmarSenha, validarForcaSenha, criptografarSenha, validarExistenciaCliente, validarMinimoEnderecoCobranca, validarMinimoEnderecoEntrega);
        rns.put(Cliente.class.getName(), rnsSalvarCliente);

        List<IStrategy> rnsSalvarEndereco = List.of(validarMinimoEnderecoCobranca, validarMinimoEnderecoEntrega);
        rns.put(Endereco.class.getName(), rnsSalvarEndereco);

        List<IStrategy> rnsSalvarCartaoCredito = List.of(definirCartaoCreditoPreferencial);
        rns.put(CartaoCredito.class.getName(), rnsSalvarCartaoCredito);
    }

    protected void inicializarAlterar() {
        List<IStrategy> rnsAlterarCliente = List.of(validarConfirmarSenha, validarForcaSenha, criptografarSenha, validarExistenciaCliente, validarMinimoEnderecoCobranca, validarMinimoEnderecoEntrega);
        rns.put(Cliente.class.getName(), rnsAlterarCliente);

        List<IStrategy> rnsAlterarEndereco = List.of(validarMinimoEnderecoCobranca, validarMinimoEnderecoEntrega);
        rns.put(Endereco.class.getName(), rnsAlterarEndereco);

        List<IStrategy> rnsAlterarCartaoCredito = List.of(definirCartaoCreditoPreferencial);
        rns.put(CartaoCredito.class.getName(), rnsAlterarCartaoCredito);
    }

    protected void inicializarAlterarSenha() {
        List<IStrategy> rnsAlterarSenhaCliente = List.of(validarConfirmarSenha, validarForcaSenha, criptografarSenha);
        rns.put(Cliente.class.getName(), rnsAlterarSenhaCliente);
    }

    protected void inicializarExcluir() {

    }

    protected void inicializarConsultar() {

    }
}
