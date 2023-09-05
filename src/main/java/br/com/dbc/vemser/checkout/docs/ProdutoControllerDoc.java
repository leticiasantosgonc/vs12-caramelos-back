package br.com.dbc.vemser.checkout.docs;

import br.com.dbc.vemser.checkout.dtos.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

public interface ProdutoControllerDoc {
    @Operation(summary = "Criar um lanche", description = "Cria um lanche e armazena no banco")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "201", description = "Lanche criado com sucesso"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @PostMapping("/criar/lanche")
    public ResponseEntity<LancheOutDTO> createLanche(@RequestBody @Valid LancheInDTO lancheInDTO);

    @Operation(summary = "Buscar lanche por Id",description = "Busca um lanche por id")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Requisição realizada com sucesso"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada um excessão")
            }
    )
    @GetMapping("/{idLanche}")
    public ResponseEntity<LancheOutDTO> findLancheById(@PathVariable @Positive Integer idLanche) throws Exception;

    @Operation(summary = "Buscar todos os lanches", description = "Deve retornar uma lista com todos os lanches")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Requisição realizada com sucesso"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "foi gerada uma excessão")
            }
    )
    @GetMapping("/listar/lanches")
    public ResponseEntity<List<LancheOutDTO>> findAllLanches();

    @Operation(summary = "Atualiza um lanche", description = "Atualiza um lanche com id especifico")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Requisição realizada com sucesso"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar esse recurso"),
                    @ApiResponse(responseCode = "500", description = "Uma excessão foi gerada")
            }
    )
    @PutMapping("/lanche/{idLanche}")
    public ResponseEntity<LancheOutDTO> updateLancheById(@PathVariable @Positive Integer idLanche, @RequestBody @Valid LancheInDTO lancheInDTO) throws Exception;


    @Operation(summary ="Deletar um lanche", description = "Deve deletar um lanche por Id")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Requisição realizada com sucesso"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar esse recurso"),
                    @ApiResponse(responseCode = "500", description = "Uma excessão foi gerada")
            }
    )
    @DeleteMapping("/lanche/{idLanche}")
    public ResponseEntity<Void> deleteById(@PathVariable @Positive Integer idLanche) throws Exception;

    @Operation(summary = "Buscar todas as bebidas",description = "Deve listar todos os lanches")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description ="Requisição realizada com sucesso"),
                    @ApiResponse(responseCode = "403",description = "Você não tem permissão para acessar esse recurso"),
                    @ApiResponse(responseCode = "500", description = "Uma excessão foi gerada")
            }
    )
    @GetMapping("/listar/bebida")
    public List<BebidaOutDTO> findAllBebidas();

    @Operation(summary = "Busca uma bebida", description = "Busca uma bebida por Id")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Requisição realizada com sucesso"),
                    @ApiResponse(responseCode = "403",description = "Você não tem permissão para acessar esse recurso"),
                    @ApiResponse(responseCode = "500", description = "Uma excessão foi gerada")
            }
    )
    @GetMapping("/bebida/{idBebida}")
    public ResponseEntity<BebidaOutDTO> findBebidaById(@PathVariable ("idBebida") @Positive Integer idBebida) throws Exception;

    @Operation(summary = "Criar uma bebida", description = "Deve criar uma bebida")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "201", description = "Bebida criada com sucesso"),
                    @ApiResponse(responseCode = "403",description = "Você não tem permissão para acessar esse recurso"),
                    @ApiResponse(responseCode = "500", description = "Uma excessão foi gerada")
            }
    )
    @PostMapping("/criar/bebida")
    public ResponseEntity<BebidaOutDTO>createBebida(@RequestBody @Valid BebidaInDTO bebidaInDTO);

    @Operation(summary ="Atualizar uma bebida", description = "Deve atualizar uma bebida")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Requisição realizada com sucesso"),
                    @ApiResponse(responseCode = "403",description = "Você não tem permissão para acessar esse recurso"),
                    @ApiResponse(responseCode = "500", description = "Uma excessão foi gerada")
            }
    )
    @PutMapping("/bebida/{idBebida}")
    public ResponseEntity<BebidaOutDTO> updateBebida(@PathVariable("idBebida") @Positive Integer idBebida, @RequestBody @Valid BebidaOutDTO bebidaEntrada) throws Exception;
}
