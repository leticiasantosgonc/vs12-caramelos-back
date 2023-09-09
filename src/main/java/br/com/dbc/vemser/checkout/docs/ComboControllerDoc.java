package br.com.dbc.vemser.checkout.docs;

import br.com.dbc.vemser.checkout.dtos.ComboInDTO;
import br.com.dbc.vemser.checkout.dtos.ComboOutDTO;
import br.com.dbc.vemser.checkout.exceptions.RegraDeNegocioException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

public interface ComboControllerDoc {
    @Operation(summary ="Criar um combo", description = "Deve criar um combo")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "201", description = "Combo criado com sucesso"),
                    @ApiResponse(responseCode = "400", description = "Requisição inválida"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "404", description = "Não encontrado"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada um excessão")
            }
    )
    @PostMapping("/criar/combo")
    public ResponseEntity<ComboOutDTO> createCombo(@RequestBody @Valid ComboInDTO comboInDTO);

    @Operation(summary ="Buscar todos os combos", description = "Deve todos os combos")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Requisição realizada com sucesso"),
                    @ApiResponse(responseCode = "400", description = "Requisição inválida"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "404", description = "Não encontrado"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada um excessão")
            }
    )
    @GetMapping("/listar/combo")
    public List<ComboOutDTO> findAllCombos();

    @Operation(summary ="Atualizar um combo", description = "Deve atualizar um combo pelo Id")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Requisição realizada com sucesso"),
                    @ApiResponse(responseCode = "400", description = "Requisição inválida"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "404", description = "Não encontrado"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada um excessão")
            }
    )
    @PutMapping("/combo/{idCombo}")
    public ResponseEntity<ComboOutDTO> updateCombo(@PathVariable("idCombo") @Positive Integer idCombo, @RequestBody @Valid ComboOutDTO comboEntrada) throws RegraDeNegocioException;

    @Operation(summary ="Deletar um combo", description = "Deve deletar um combo pelo Id")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Requisição realizada com sucesso"),
                    @ApiResponse(responseCode = "400", description = "Requisição inválida"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "404", description = "Não encontrado"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada um excessão")
            }
    )
    @DeleteMapping("/combo/{idCombo}")
    public ResponseEntity<Void> deleteComboById(@PathVariable("idCombo") @Positive Integer idCombo) throws RegraDeNegocioException;
}
