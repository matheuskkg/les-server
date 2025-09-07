package fatec.mkkg.server.repositories;

import fatec.mkkg.server.domain.endereco.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Integer> {

	@Query("""
		select new fatec.mkkg.server.domain.endereco.Endereco(
	       e.id,
	       e.nomeIdentificador,
	       e.pais,
	       e.estado,
	       e.cidade,
	       e.tipoLogradouro,
	       e.logradouro,
	       e.tipoResidencia,
	       e.numero,
	       e.bairro,
	       e.cep,
	       e.observacao,
	       e.cobranca,
	       e.entrega
	   )
	   from Endereco e
	   where e.cliente.id = :clienteId
	""")
	List<Endereco> buscarPorClienteId(@Param("clienteId") Integer clienteId);

}
