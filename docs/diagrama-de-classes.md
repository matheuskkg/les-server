# Diagrama de Classes
![img.png](images/diagrama-de-classes.png)

- Um mesmo livro pode ser publicado diversas vezes;
  - Nova publicação = novo ISBN;
  - Novo ISBN = novo código de barras;


- `ItemPedido` deve persistir snapshot do preço do `Produto` no momento da finalização do pedido;


### TODO:
- Alterar relação de `Publicacao` e `Produto`;
  - `Publicacao` deve herdar de `Produto`;
- Alterar nome da tabela da entidade `ItemCarrinho` para `itens_carrinho`;