package fatec.mkkg.server.repositories;

import fatec.mkkg.server.domain.livro.ControleEstoqueProduto;
import fatec.mkkg.server.domain.venda.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ControleEstoqueProdutoRepository extends JpaRepository<ControleEstoqueProduto, Integer> {

	@Query("select c.quantidade from ControleEstoqueProduto c where c.produto = ?1")
	Integer obterQuantidadeDisponivelEmEstoquePeloProduto(Produto produto);

}
