package br.com.dbc.vemser.checkout.docs;


import br.com.dbc.vemser.checkout.dtos.BebidaOutDTO;
import br.com.dbc.vemser.checkout.dtos.LancheOutDTO;
import br.com.dbc.vemser.checkout.dtos.SobremesaOutDTO;
import br.com.dbc.vemser.checkout.entities.Produto;
import br.com.dbc.vemser.checkout.exceptions.RegraDeNegocioException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;

public interface ProdutoControllerDoc {

    @Operation(summary ="Busca a disponibilidade do produto", description = "Deve buscar a quantidade de produto pelo Id")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Requisição realizada com sucesso"),
                    @ApiResponse(responseCode = "400", description = "Requisição inválida"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "404", description = "Não encontrado"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada um excessão")
            }
    )
    @PutMapping("/disponibilidade/{idProduto}")
    public ResponseEntity<Integer> getQuantidadeProdutoPorId(@PathVariable @Positive Integer idProduto) throws RegraDeNegocioException;

    @Operation(summary ="Atualizar quantidade de produto", description = "Deve atualizar a quantidade de produto pelo Id do produto")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Requisição realizada com sucesso"),
                    @ApiResponse(responseCode = "400", description = "Requisição inválida"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "404", description = "Não encontrado"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada um excessão")
            }
    )
    @PutMapping("/atualizar-quantidade/{idProduto}")
    public ResponseEntity<Produto> updateQuantidadeProduto(@RequestBody @Positive Integer quantidade,
                                                           @PathVariable @Positive Integer idProduto) throws RegraDeNegocioException;

    @Operation(summary ="Buscar todos os lanches ordenados por nome", description = "Deve buscar todos os lanches ordenados por nome")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Requisição realizada com sucesso"),
                    @ApiResponse(responseCode = "400", description = "Requisição inválida"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "404", description = "Não encontrado"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada um excessão")
            }
    )
    @GetMapping("/listar/lanches-ordenados-por-nome")
    public Page<LancheOutDTO> findLanchesOrdenadosPorNome(@RequestParam(defaultValue= "0") Integer numeroDePaginas, @RequestParam(defaultValue= "10")  Integer quantidadeDeRegistrosPorPagina);

    @Operation(summary ="Buscar todas as bebidas ordenadas por nome", description = "Deve buscar todas as bebidas ordenadas por nome")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Requisição realizada com sucesso"),
                    @ApiResponse(responseCode = "400", description = "Requisição inválida"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "404", description = "Não encontrado"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada um excessão")
            }
    )
    @GetMapping("/listar/bebidas-ordenadas-por-nome")
    public Page<BebidaOutDTO> findBebidasOrdenadasPorNome(@RequestParam(defaultValue= "0") Integer numeroDePaginas, @RequestParam(defaultValue= "10") Integer quantidadeDeRegistros);

    @Operation(summary ="Buscar todas as sobremesas ordenadas por nome", description = "Deve buscar todas as sobremesas ordenadas por nome")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Requisição realizada com sucesso"),
                    @ApiResponse(responseCode = "400", description = "Requisição inválida"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "404", description = "Não encontrado"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada um excessão")
            }
    )
    @GetMapping("/listar/sobremesas-ordenadas-por-nome")
    public Page<SobremesaOutDTO> findSobremesasOrdenadasPorNome(@RequestParam(defaultValue= "0") Integer numeroDePaginas, @RequestParam(defaultValue= "10") Integer quantidadeDeRegistros);

    @Operation(summary ="Buscar todos os lanches ordenados por preço", description = "Deve buscar todos os lanches ordenados por preço")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Requisição realizada com sucesso"),
                    @ApiResponse(responseCode = "400", description = "Requisição inválida"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "404", description = "Não encontrado"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada um excessão")
            }
    )
    @GetMapping("/listar/lanches-ordenados-por-preco")
    public Page<LancheOutDTO> findLanchesOrdenadosPorPreco(@RequestParam(defaultValue= "0") Integer numeroDePaginas, @RequestParam(defaultValue= "10") Integer quantidadeDeRegistrosPorPagina);

    @Operation(summary ="Buscar todas as bebidas ordenadas por preço", description = "Deve buscar todas as bebidas ordenadas por preço")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Requisição realizada com sucesso"),
                    @ApiResponse(responseCode = "400", description = "Requisição inválida"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "404", description = "Não encontrado"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada um excessão")
            }
    )
    @GetMapping("/listar/bebidas-ordenadas-por-preco")
    public Page<BebidaOutDTO> findBebidasOrdenadasPorPreco(@RequestParam(defaultValue= "0") Integer numeroDePaginas, @RequestParam(defaultValue= "10") Integer quantidadeDeRegistros);

    @Operation(summary ="Buscar todas as sobremesas ordenadas por preço", description = "Deve buscar todas as sobremesas ordenadas por preço")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Requisição realizada com sucesso"),
                    @ApiResponse(responseCode = "400", description = "Requisição inválida"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "404", description = "Não encontrado"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada um excessão")
            }
    )
    @GetMapping("/listar/sobremesas-ordenadas-por-preco")
    public Page<SobremesaOutDTO> findSobremesasOrdenadasPorPreco(@RequestParam(defaultValue= "0") Integer numeroDePaginas, @RequestParam(defaultValue= "10") Integer quantidadeDeRegistros);

}
