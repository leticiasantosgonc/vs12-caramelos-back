package br.com.dbc.vemser.checkout.docs;

import br.com.dbc.vemser.checkout.dtos.BebidaInDTO;
import br.com.dbc.vemser.checkout.dtos.BebidaOutDTO;
import br.com.dbc.vemser.checkout.exceptions.RegraDeNegocioException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

public interface BebidaControllerDoc {

    @Operation(summary = "Buscar uma bebida", description = "Buscar todas as bebidas")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Requisição realizada com sucesso"),
                    @ApiResponse(responseCode = "400", description = "Requisição inválida"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "404", description = "Não encontrado"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada um excessão")
            }
    )
    @GetMapping("/listar/bebida")
    public List<BebidaOutDTO> findAllBebidas();

    @Operation(summary = "Buscar uma bebida", description = "Busca uma bebida por Id")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Requisição realizada com sucesso"),
                    @ApiResponse(responseCode = "400", description = "Requisição inválida"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "404", description = "Não encontrado"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada um excessão")
            }
    )
    @GetMapping("/bebida/{idBebida}")
    public ResponseEntity<BebidaOutDTO> findBebidaById(@PathVariable("idBebida") @Positive Integer idBebida) throws RegraDeNegocioException;

    @Operation(summary = "Criar uma bebida", description = "Deve criar uma bebida")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "201", description = "Bebida criada com sucesso"),
                    @ApiResponse(responseCode = "400", description = "Requisição inválida"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "404", description = "Não encontrado"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada um excessão")
            }
    )
    @PostMapping("/criar/bebida")
    public ResponseEntity<BebidaOutDTO> createBebida(@RequestBody @Valid BebidaInDTO bebidaInDTO);

    @Operation(summary ="Atualizar uma bebida", description = "Deve atualizar uma bebida")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Requisição realizada com sucesso"),
                    @ApiResponse(responseCode = "400", description = "Requisição inválida"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "404", description = "Não encontrado"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada um excessão")
            }
    )
    @PutMapping("/bebida/{idBebida}")
    public ResponseEntity<BebidaOutDTO> updateBebida(@PathVariable("idBebida") @Positive Integer idBebida, @RequestBody @Valid BebidaOutDTO bebidaEntrada) throws RegraDeNegocioException;

    @Operation(summary ="Deletar uma bebida", description = "Deve deletar uma bebida")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Requisição realizada com sucesso"),
                    @ApiResponse(responseCode = "400", description = "Requisição inválida"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "404", description = "Não encontrado"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada um excessão")
            }
    )
    @DeleteMapping("/bebida/{idBebida}")
    public ResponseEntity<Void> deleteBebidaById(@PathVariable("idBebida") @Positive Integer idBebida) throws RegraDeNegocioException, DataIntegrityViolationException;

}
