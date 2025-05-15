package mkkg.fatec.esiii.domain.cliente;

import jakarta.persistence.*;
import mkkg.fatec.esiii.domain.EntidadeDominio;
import mkkg.fatec.esiii.domain.cartao.CartaoCredito;
import mkkg.fatec.esiii.domain.endereco.Endereco;
import mkkg.fatec.esiii.domain.telefone.Telefone;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "clientes")
public class Cliente extends EntidadeDominio {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "cli_id")
    private Integer id;

    @Column(name = "cli_nome", nullable = false)
    private String nome;

    @Column(name = "cli_data_nascimento")
    private LocalDate dataNascimento;

    @Column(name = "cli_genero", nullable = false, length = 1)
    private String genero;

    @Column(name = "cli_cpf", nullable = false)
    private String cpf;

    @Column(name = "cli_email", nullable = false)
    private String email;

    @Column(name = "cli_senha", nullable = false)
    private String senha;

    @Transient
    private String senhaConfirmar;

    @Column(name = "cli_cadastro_ativo", nullable = false)
    private Boolean cadastroAtivo;

    @OneToMany
    @JoinColumn(name = "tel_cli_id", referencedColumnName = "cli_id", nullable = false)
    private List<Telefone> telefones;

    @OneToMany
    @JoinColumn(name = "ctc_cli_id", referencedColumnName = "cli_id", nullable = false)
    private List<CartaoCredito> cartoesCredito;

    @OneToMany
    @JoinColumn(name = "end_cli_id", referencedColumnName = "cli_id", nullable = false)
    private List<Endereco> enderecos;

    public Cliente() {
    }

    public Cliente(Integer id, String nome, LocalDate dataNascimento, String genero, String cpf, String email, String senha, String senhaConfirmar, Boolean cadastroAtivo, List<Telefone> telefones, List<CartaoCredito> cartoesCredito, List<Endereco> enderecos) {
        this.id = id;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.genero = genero;
        this.cpf = cpf;
        this.email = email;
        this.senha = senha;
        this.senhaConfirmar = senhaConfirmar;
        this.cadastroAtivo = cadastroAtivo;
        this.telefones = telefones;
        this.cartoesCredito = cartoesCredito;
        this.enderecos = enderecos;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getSenhaConfirmar() {
        return senhaConfirmar;
    }

    public void setSenhaConfirmar(String senhaConfirmar) {
        this.senhaConfirmar = senhaConfirmar;
    }

    public Boolean getCadastroAtivo() {
        return cadastroAtivo;
    }

    public void setCadastroAtivo(Boolean cadastroAtivo) {
        this.cadastroAtivo = cadastroAtivo;
    }

    public List<Telefone> getTelefones() {
        return telefones;
    }

    public void setTelefones(List<Telefone> telefones) {
        this.telefones = telefones;
    }

    public List<CartaoCredito> getCartoesCredito() {
        return cartoesCredito;
    }

    public void setCartoesCredito(List<CartaoCredito> cartoesCredito) {
        this.cartoesCredito = cartoesCredito;
    }

    public List<Endereco> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(List<Endereco> enderecos) {
        this.enderecos = enderecos;
    }
}
