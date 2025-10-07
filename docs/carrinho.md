### Request:

**edits** ⇾ Lista de `ItemCarrinho`, onde cada item representa um produto e a quantidade a ser alterada (caso o item não exista no carrinho, ele será adicionado)

**removes** ⇾ Lista de inteiros, onde cada inteiro representa o ID do produto a ser removido do carrinho
```json
{
  "edits": [
    {
      "produto": {
        "id": 53
      },
      "quantidade": 3
    },
    {
      "produto": {
        "id": 21
      },
      "quantidade": -1
    }
  ],
  "removes": [
    1,
    21,
    65
  ]
}
```

### Passos:
- Procurar carrinho do cliente
  - Criar caso não exista
- Processar `AlteracaoCarrinhoRequest`
  - `edits`
    - Para cada `ItemCarrinho` na request, procurar no carrinho do cliente
      - Se existir, atualizar quantidade
      - Se não existir, adicionar ao carrinho
  - `removes`
    - Filtrar `Carrinho` removendo os `ItemCarrinho` que estão na request
- Salvar carrinho atualizado