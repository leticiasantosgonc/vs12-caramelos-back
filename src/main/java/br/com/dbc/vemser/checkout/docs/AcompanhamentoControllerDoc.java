package br.com.dbc.vemser.checkout.docs;

import br.com.dbc.vemser.checkout.dtos.AcompanhamentoInDTO;
import br.com.dbc.vemser.checkout.dtos.AcompanhamentoOutDTO;
import br.com.dbc.vemser.checkout.exceptions.RegraDeNegocioException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

public interface AcompanhamentoControllerDoc {
    @Operation(summary ="Criar um novo acompanhamento", description = "Deve criar um novo acompanhamento")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "201", description = "Acompanhamento criado com sucesso"),
                    @ApiResponse(responseCode = "400", description = "Requisição inválida"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "404", description = "Não encontrado"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada um excessão")
            }
    )
    @PostMapping("/criar/acompanhamento")
    public ResponseEntity<AcompanhamentoOutDTO> createAcompanhamento(@RequestBody @Valid AcompanhamentoInDTO acompanhamentoInDTO);

    @Operation(summary ="Buscar todos os acompanhamentos", description = "Deve buscar todos os acompanhamentos")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Requisição realizada com sucesso"),
                    @ApiResponse(responseCode = "400", description = "Requisição inválida"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "404", description = "Não encontrado"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada um excessão")
            }
    )
    @GetMapping("/listar/acompanhamentos")
    public List<AcompanhamentoOutDTO> findAllAcompanhamentos();

    @Operation(summary ="Buscar acompanhamento pelo Id", description = "Deve buscar um acompanhamento pelo Id")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Requisição realizada com sucesso"),
                    @ApiResponse(responseCode = "400", description = "Requisição inválida"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "404", description = "Não encontrado"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada um excessão")
            }
    )
    @GetMapping("/acompanhamento/{idAcompanhamento}")
    public ResponseEntity<AcompanhamentoOutDTO> findAcompanhamentoById(@PathVariable("idAcompanhamento") @Positive Integer idAcompanhamento) throws RegraDeNegocioException;
    @Operation(summary ="Atualizar um acompanhamento", description = "Deve atualizar um acompanhamento pelo Id")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Requisição realizada com sucesso"),
                    @ApiResponse(responseCode = "400", description = "Requisição inválida"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "404", description = "Não encontrado"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada um excessão")
            }
    )
    @PutMapping("/acompanhamento/{idAcompanhamento}")
    public ResponseEntity<AcompanhamentoOutDTO> updateAcompanhamento(@PathVariable("idAcompanhamento") @Positive Integer idAcompanhamento, @RequestBody @Valid AcompanhamentoOutDTO acompanhamentoEntrada) throws RegraDeNegocioException;

    @Operation(summary ="Deletar acompanhamento", description = "Deve deletar um acompanhamento pelo Id")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Requisição realizada com sucesso"),
                    @ApiResponse(responseCode = "400", description = "Requisição inválida"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "404", description = "Não encontrado"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada um excessão")
            }
    )
    @DeleteMapping("/acompanhamento/{idAcompanhamento}")
    public ResponseEntity<Void> deleteAcompanhamentoById(@PathVariable("idAcompanhamento") @Positive Integer idAcompanhamento);

}
