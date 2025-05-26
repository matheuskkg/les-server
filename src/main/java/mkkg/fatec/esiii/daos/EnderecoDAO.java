package mkkg.fatec.esiii.daos;

import jakarta.transaction.Transactional;
import mkkg.fatec.esiii.domain.EntidadeDominio;
import mkkg.fatec.esiii.domain.endereco.Cidade;
import mkkg.fatec.esiii.domain.endereco.Endereco;
import mkkg.fatec.esiii.domain.endereco.TipoLogradouro;
import mkkg.fatec.esiii.domain.endereco.TipoResidencia;
import mkkg.fatec.esiii.repositories.CidadeRepository;
import mkkg.fatec.esiii.repositories.EnderecoRepository;
import mkkg.fatec.esiii.repositories.TipoLogradouroRepository;
import mkkg.fatec.esiii.repositories.TipoResidenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EnderecoDAO implements IDAO {

    @Autowired
    private EnderecoRepository repository;

    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private TipoLogradouroRepository tipoLogradouroRepository;

    @Autowired
    private TipoResidenciaRepository tipoResidenciaRepository;

    private Endereco complementarEndereco(Endereco endereco) {
        Cidade cidade = cidadeRepository.findByNome(endereco.getCidade().getNome());
        TipoLogradouro tipoLogradouro = tipoLogradouroRepository.findByTipo(endereco.getTipoLogradouro().getTipo());
        TipoResidencia tipoResidencia = tipoResidenciaRepository.findByTipo(endereco.getTipoResidencia().getTipo());

        endereco.setCidade(cidade);
        endereco.setTipoLogradouro(tipoLogradouro);
        endereco.setTipoResidencia(tipoResidencia);

        return endereco;
    }

    @Override
    public void salvar(EntidadeDominio entidade) {
        Endereco endereco = complementarEndereco((Endereco) entidade);

        repository.save(endereco);
    }

    @Override
    public void alterar(EntidadeDominio entidade) {
        Endereco endereco = complementarEndereco((Endereco) entidade);

        repository.save(endereco);
    }

    @Transactional
    @Override
    public void excluir(EntidadeDominio entidade) {
        Endereco endereco = (Endereco) entidade;

        repository.deleteEnderecoById(endereco.getId());
    }

    @Override
    public List<EntidadeDominio> consultar(EntidadeDominio entidade) {
        return List.of();
    }
}
