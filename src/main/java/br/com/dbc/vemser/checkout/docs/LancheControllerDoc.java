package br.com.dbc.vemser.checkout.docs;

import br.com.dbc.vemser.checkout.dtos.LancheInDTO;
import br.com.dbc.vemser.checkout.dtos.LancheOutDTO;
import br.com.dbc.vemser.checkout.exceptions.RegraDeNegocioException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

public interface LancheControllerDoc {
    @Operation(summary = "Criar um lanche", description = "Cria um lanche e armazena no banco")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "201", description = "Lanche criado com sucesso"),
                    @ApiResponse(responseCode = "400", description = "Requisição inválida"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "404", description = "Não encontrado"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada um excessão")
            }
    )
    @PostMapping("/criar/lanche")
    public ResponseEntity<LancheOutDTO> createLanche(@RequestBody @Valid LancheInDTO lancheInDTO);

    @Operation(summary = "Buscar lanche por Id",description = "Busca um lanche por id")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Requisição realizada com sucesso"),
                    @ApiResponse(responseCode = "400", description = "Requisição inválida"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "404", description = "Não encontrado"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada um excessão")
            }
    )
    @GetMapping("/{idLanche}")
    public ResponseEntity<LancheOutDTO> findLancheById(@PathVariable @Positive Integer idLanche) throws RegraDeNegocioException;

    @Operation(summary = "Buscar todos os lanches", description = "Deve retornar uma lista com todos os lanches")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Requisição realizada com sucesso"),
                    @ApiResponse(responseCode = "400", description = "Requisição inválida"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "404", description = "Não encontrado"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada um excessão")
            }
    )
    @GetMapping("/listar/lanches")
    public ResponseEntity<List<LancheOutDTO>> findAllLanches();

    @Operation(summary = "Atualizar um lanche", description = "Atualiza um lanche com id especifico")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Requisição realizada com sucesso"),
                    @ApiResponse(responseCode = "400", description = "Requisição inválida"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "404", description = "Não encontrado"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada um excessão")
            }
    )
    @PutMapping("/lanche/{idLanche}")
    public ResponseEntity<LancheOutDTO> updateLanche(@PathVariable @Positive Integer idLanche, @RequestBody @Valid LancheInDTO lancheInDTO) throws RegraDeNegocioException;


    @Operation(summary ="Deletar um lanche", description = "Deve deletar um lanche por Id")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Requisição realizada com sucesso"),
                    @ApiResponse(responseCode = "400", description = "Requisição inválida"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "404", description = "Não encontrado"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada um excessão")
            }
    )
    @DeleteMapping("/lanche/{idLanche}")
    public ResponseEntity<Void> deleteLancheById(@PathVariable @Positive Integer idLanche) throws RegraDeNegocioException;

}
