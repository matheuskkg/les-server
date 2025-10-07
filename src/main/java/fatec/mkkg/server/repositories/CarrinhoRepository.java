package fatec.mkkg.server.repositories;

import fatec.mkkg.server.domain.carrinho.Carrinho;
import fatec.mkkg.server.domain.cliente.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CarrinhoRepository extends JpaRepository<Carrinho, Integer> {

	// TODO: alterar quando adicionar status no carrinho
	Optional<Carrinho> findByCliente(Cliente cliente);

}
