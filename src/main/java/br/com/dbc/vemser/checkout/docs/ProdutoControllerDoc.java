package br.com.dbc.vemser.checkout.docs;


import br.com.dbc.vemser.checkout.entities.Produto;
import br.com.dbc.vemser.checkout.exceptions.RegraDeNegocioException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
}
