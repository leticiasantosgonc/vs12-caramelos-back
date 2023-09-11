# <p align="center"> Caramelos - Back end </p>

<p align="center">
<img src="https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white"/>
<img src="https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white"/>
<img src="https://img.shields.io/badge/Oracle-F80000?style=for-the-badge&logo=Oracle&logoColor=white"/>
<img src="https://img.shields.io/badge/IntelliJ_IDEA-000000.svg?style=for-the-badge&logo=intellij-idea&logoColor=white"/>
</p>

Caramelos é um sistema de Checkout, criado durante a 12ª trilha de Back-end do programa Vem Ser, da empresa [DBC Company](https://www.dbccompany.com.br/)

🔗 App Back-end: [Clique aqui](http://vemser-hml.dbccompany.com.br:39000/vemser/vs12-caramelos-back) <br>
🔗 App Front-end: [Clique aqui](http://vemser-dbc.dbccompany.com.br:39000/vemser/vs12-caramelos-front)

## Funcionalidades

### Produto Controller
- `GET/produto/listar/sobremesas-ordenadas-por-preco`: busca todas as sobremesas ordenadas pelo preço;
- `GET/produto/listar/sobremesas-ordenadas-por-nome`: busca todas as sobremesas ordenadas pelo nome;
- `GET/produto/listar/lanches-ordenados-por-preco`: busca todos os lanches ordenados pelo preço;
- `GET/produto/listar/lanches-ordenados-por-nome`: busca todos os lanches ordenados pelo nome;
- `GET/produto/listar/bebidas-ordenadas-por-preco`: busca todas as bebidas ordenadas pelo preço;
- `GET/produto/listar/bebidas-ordenadas-por-nome`: busca todas as bebidas ordenadas pelo nome;
- `GET/produto/disponibilidade/{idProduto}`: busca a disponibilidade do produto selecionado pelo id no estoque;
- `PUT/produto/update-imagem/{idProduto}`: atualiza a imagem do produto selecionado pelo id;
- `PUT/produto/atualizar-quantidade/{idProduto}`: atualiza a quantidade do produto selecionado pelo id;

### Lanche Controller
- `POST/lanche/criar/lanche`: cria um lanche;
- `GET/lanche/listar/lanches`: busca todos os lanches;
- `GET/lanche/lanche/{idLanche}`: busca um lanche pelo id;
- `PUT/lanche/lanche/{idLanche}`: atualiza um lanche pelo id;
- `DELETE/lanche/lanche/{idLanche}`: deleta um lanche pelo id;

### Bebida Controller
- `POST/bebida/criar/bebida`: cria uma bebida;
- `GET/bebida/listar/bebidas`: busca todas as bebidas;
- `GET/bebida/bebida/{idBebida}`: busca uma bebida pelo id;
- `PUT/bebida/bebida/{idBebida}`: atualiza uma bebida pelo id;
- `DELETE/bebida/bebida/{idBebida}`: deleta uma bebida pelo id;

### Sobremesa Controller
- `POST/sobremesa/criar/sobremesa`: cria uma sobremesa;
- `GET/sobremesa/listar/sobremesas`: busca todas as sobremesas;
- `GET/sobremesa/sobremesa/{idSobremesa}`: busca uma sobremesa pelo id;
- `PUT/sobremesa/sobremesa/{idSobremesa}`: atualiza uma sobremesa pelo id;
- `DELETE/sobremesa/sobremesa/{idSobremesa}`: deleta uma sobremesa pelo id;

### Acompanhamento Controller
- `POST/acompanhamento/criar/acompanhamento`: cria um acompanhamento;
- `GET/acompanhamento/listar/acompanhamentos`: busca todos os acompanhamentos;
- `GET/acompanhamento/acompanhamento/{idAcompanhamento}`: busca um acompanhamento pelo id;
- `PUT/acompanhamento/acompanhamento/{idAcompanhamento}`: atualiza um acompanhamento pelo id;
- `DELETE/acompanhamento/acompanhamento/{idAcompanhamento}`: deleta um acompanhamento pelo id;

### Combo Controller
- `POST/combo/criar/combo`: cria um combo;
- `GET/combo/listar/combo`: busca todos os combos;
- `PUT/combo/combo/{idCombo}`: atualiza um combo pelo id;
- `DELETE/combo/combo/{idCombo}`: deleta um combo pelo id;

### Pedido Controller
- `POST/pedido/criar`: cria um pedido;
- `GET/pedido/listar`: busca todos os pedidos;
- `GET/pedido/listar-por-status`: busca todos os pedidos agrupados pelo status;
- `GET/pedido/listar-por-status`: busca todos os pedidos criados em uma data específica;
- `GET/pedido/nota/{idPedido}`: gera a nota fiscal do pedido;

### Usuário Controller
- `POST/admin/enviar-email`: envia e-mail ao alterar senha;
- `POST/admin/cadastrar/admin`: cria um administrador;
- `GET/admin`: busca todos os usuários;
- `PUT/admin/alterar-senha`: atualiza uma senha;
- `DELETE/admin/{idUsuario}`: deleta um administrador;

### Auth Controller
- `POST/auth`: autentica um usuário;

### Desenvolvedores
💻 [Breno Santos](https://github.com/breno-ms)<br>
💻 [Guilherme Militão](https://github.com/guilherme-militao)<br>
💻 [Letícia Santos](https://github.com/leticiasantosgonc)<br>

### Product Owner
💻 [Mayra Amaral](https://github.com/mayraamaral)<br>
