package fatec.mkkg.server.repositories;

import fatec.mkkg.server.domain.cliente.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

	@Query("""
			select new fatec.mkkg.server.domain.cliente.Cliente(
			    c.id,
			    c.nome,
			    c.dataNascimento,
			    c.genero,
			    c.cpf,
			    c.email,
			    new fatec.mkkg.server.domain.telefone.Telefone(t.id, t.ddd, t.tipoTelefone, t.numero)
			)
			from Cliente c
			join Telefone t on t.cliente.id = c.id
			where c.cadastroAtivo = true
				and (:nome is null or trim(:nome) = '' or lower(c.nome) like lower(concat('%', :nome, '%')))
				and (:cpf is null or trim(:cpf) = '' or c.cpf = :cpf)
				and (:email is null or trim(:email) = '' or lower(c.email) = lower(:email))
			order by c.nome
			""")
	List<Cliente> buscarPorClienteFiltro(@Param("nome") String nome,
	                                     @Param("email") String email,
	                                     @Param("cpf") String cpf);

	@Query("""
				select new fatec.mkkg.server.domain.cliente.Cliente(
				    c.id,
				    c.nome,
				    c.dataNascimento,
				    c.genero,
				    c.cpf,
				    c.email,
				    new fatec.mkkg.server.domain.telefone.Telefone(t.id, t.ddd, t.tipoTelefone, t.numero)
				)
				from Cliente c
				join Telefone t on t.cliente.id = c.id
				where c.id = :id
			  	and c.cadastroAtivo = true
			""")
	Cliente buscarPorId(@Param("id") Integer id);

}
