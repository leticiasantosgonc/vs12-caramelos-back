package br.com.dbc.vemser.checkout.docs;

import br.com.dbc.vemser.checkout.dtos.*;
import br.com.dbc.vemser.checkout.exceptions.RegraDeNegocioException;
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
    public ResponseEntity<LancheOutDTO> findLancheById(@PathVariable @Positive Integer idLanche) throws RegraDeNegocioException;

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

    @Operation(summary = "Atualizar um lanche", description = "Atualiza um lanche com id especifico")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Requisição realizada com sucesso"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar esse recurso"),
                    @ApiResponse(responseCode = "500", description = "Uma excessão foi gerada")
            }
    )
    @PutMapping("/lanche/{idLanche}")
    public ResponseEntity<LancheOutDTO> updateLanche(@PathVariable @Positive Integer idLanche, @RequestBody @Valid LancheInDTO lancheInDTO) throws RegraDeNegocioException;


    @Operation(summary ="Deletar um lanche", description = "Deve deletar um lanche por Id")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Requisição realizada com sucesso"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar esse recurso"),
                    @ApiResponse(responseCode = "500", description = "Uma excessão foi gerada")
            }
    )
    @DeleteMapping("/lanche/{idLanche}")
    public ResponseEntity<Void> deleteLancheById(@PathVariable @Positive Integer idLanche) throws RegraDeNegocioException;

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

    @Operation(summary = "Buscar uma bebida", description = "Busca uma bebida por Id")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Requisição realizada com sucesso"),
                    @ApiResponse(responseCode = "403",description = "Você não tem permissão para acessar esse recurso"),
                    @ApiResponse(responseCode = "500", description = "Uma excessão foi gerada")
            }
    )
    @GetMapping("/bebida/{idBebida}")
    public ResponseEntity<BebidaOutDTO> findBebidaById(@PathVariable ("idBebida") @Positive Integer idBebida) throws RegraDeNegocioException;

    @Operation(summary = "Criar uma bebida", description = "Deve criar uma bebida")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "201", description = "Bebida criada com sucesso"),
                    @ApiResponse(responseCode = "403",description = "Você não tem permissão para acessar esse recurso"),
                    @ApiResponse(responseCode = "500", description = "Uma excessão foi gerada")
            }
    )
    @PostMapping("/criar/bebida")
    public ResponseEntity<BebidaOutDTO> createBebida(@RequestBody @Valid BebidaInDTO bebidaInDTO);

    @Operation(summary ="Atualizar uma bebida", description = "Deve atualizar uma bebida")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Requisição realizada com sucesso"),
                    @ApiResponse(responseCode = "403",description = "Você não tem permissão para acessar esse recurso"),
                    @ApiResponse(responseCode = "500", description = "Uma excessão foi gerada")
            }
    )
    @PutMapping("/bebida/{idBebida}")
    public ResponseEntity<BebidaOutDTO> updateBebida(@PathVariable("idBebida") @Positive Integer idBebida, @RequestBody @Valid BebidaOutDTO bebidaEntrada) throws RegraDeNegocioException;

    @Operation(summary ="Deletar uma bebida", description = "Deve deletar uma bebida")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Requisição realizada com sucesso"),
                    @ApiResponse(responseCode = "403",description = "Você não tem permissão para acessar esse recurso"),
                    @ApiResponse(responseCode = "500", description = "Uma excessão foi gerada")
            }
    )
    @DeleteMapping("/bebida/{idBebida}")
    public ResponseEntity<Void> deleteBebidaById(@PathVariable("idBebida") @Positive Integer idBebida) throws RegraDeNegocioException;

    @Operation(summary ="Buscar todas as sobremesas", description = "Deve buscar todas as sobremesas")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Requisição realizada com sucesso"),
                    @ApiResponse(responseCode = "403",description = "Você não tem permissão para acessar esse recurso"),
                    @ApiResponse(responseCode = "500", description = "Uma excessão foi gerada")
            }
    )
    @GetMapping("/listar-sobremesas")
    public ResponseEntity<List<SobremesaOutDTO>> findAllSobremesas();

    @Operation(summary ="Buscar uma sobremesa", description = "Buscar uma sobremesa por Id")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Requisição realizada com sucesso"),
                    @ApiResponse(responseCode = "403",description = "Você não tem permissão para acessar esse recurso"),
                    @ApiResponse(responseCode = "500", description = "Uma excessão foi gerada")
            }
    )
    @GetMapping("/sobremesa/{idSobremesa}")
    public ResponseEntity<SobremesaOutDTO> findSobremesaById(@PathVariable @Positive Integer idSobremesa) throws RegraDeNegocioException;

    @Operation(summary ="Criar uma sobremesa", description = "Deve criar uma sobremesa")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Requisição realizada com sucesso"),
                    @ApiResponse(responseCode = "403",description = "Você não tem permissão para acessar esse recurso"),
                    @ApiResponse(responseCode = "500", description = "Uma excessão foi gerada")
            }
    )
    @PostMapping("/criar/sobremesa")
    public ResponseEntity<SobremesaOutDTO> saveSobremesa(@RequestBody @Valid SobremesaInDTO sobremesa);

    @Operation(summary ="Atualizar uma sobremesa", description = "Deve atualizar uma sobremesa")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Requisição realizada com sucesso"),
                    @ApiResponse(responseCode = "403",description = "Você não tem permissão para acessar esse recurso"),
                    @ApiResponse(responseCode = "500", description = "Uma excessão foi gerada")
            }
    )
    @PutMapping("/sobremesa/{idSobremesa}")
    public ResponseEntity<SobremesaOutDTO> updateSobremesa(@RequestBody @Valid SobremesaInDTO sobremesaAtualizada,
                                                           @PathVariable @Positive Integer idSobremesa)throws RegraDeNegocioException;

    @Operation(summary ="Deletar uma sobremesa", description = "Deve deletar uma sobremesa")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Requisição realizada com sucesso"),
                    @ApiResponse(responseCode = "403",description = "Você não tem permissão para acessar esse recurso"),
                    @ApiResponse(responseCode = "500", description = "Uma excessão foi gerada")
            }
    )
    @DeleteMapping("/sobremesa/{idSobremesa}")
    public ResponseEntity<Void> deleteSobremesa(@PathVariable @Positive Integer idSobremesa);

}
