package br.com.dbc.vemser.checkout.docs;

import br.com.dbc.vemser.checkout.dtos.ListarPedidoPorDataOutDTO;
import br.com.dbc.vemser.checkout.dtos.PedidoInDTO;
import br.com.dbc.vemser.checkout.dtos.PedidoOutDTO;
import br.com.dbc.vemser.checkout.exceptions.RegraDeNegocioException;
import com.stripe.exception.StripeException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface PedidoControllerDoc {

    @Operation(summary = "Gerar nota fiscal", description = "Deve gerar uma nota fiscal com base no ID do pedido")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "201", description = "Nota fiscal criada com sucesso"),
                    @ApiResponse(responseCode = "400", description = "Requisição inválida"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "404", description = "Não encontrado"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada um excessão")
            }
    )
    @GetMapping("/nota/{idPedido}")
    public ResponseEntity<Void> gerarNota(@PathVariable Integer idPedido) throws RegraDeNegocioException, StripeException, IOException;

    @Operation(summary = "Buscar todos os pedidos", description = "Deve buscar todos os pedidos")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Requisição realizada com sucesso"),
                    @ApiResponse(responseCode = "400", description = "Requisição inválida"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "404", description = "Não encontrado"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada um excessão")
            }
    )
    @GetMapping("/listar")
    public ResponseEntity<List<PedidoOutDTO>> findAllPedidos();

    @Operation(summary = "Buscar todos os pedidos agrupados pelo status: pagos e não pagos", description = "Deve buscar a quantidade de pedidos pagos e a quantidade de não pagos")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Requisição realizada com sucesso"),
                    @ApiResponse(responseCode = "400", description = "Requisição inválida"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "404", description = "Não encontrado"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada um excessão")
            }
    )
    @GetMapping("/listar-por-status")
    public ResponseEntity<Map<String, Long>> listarPedidosPorStatus();

    @Operation(summary = "Buscar todos os pedidos criados em uma data específica", description = "Deve buscar todos os os pedidos realizados na data informada no corpo da requisição")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Requisição realizada com sucesso"),
                    @ApiResponse(responseCode = "400", description = "Requisição inválida"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "404", description = "Não encontrado"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada um excessão")
            }
    )
    @GetMapping("/listar-por-data")
    public ResponseEntity<List<ListarPedidoPorDataOutDTO>> listarPedidosPorData(@RequestBody LocalDate data);

    @Operation(summary = "Criar pedido", description = "Deve criar um pedido")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "201", description = "Pedido criado com sucesso"),
                    @ApiResponse(responseCode = "400", description = "Requisição inválida"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "404", description = "Não encontrado"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada um excessão")
            }
    )
    @PostMapping("/criar")
    public ResponseEntity<Object> createPedido(@RequestBody PedidoInDTO pedidoInDTO) throws RegraDeNegocioException, IOException, StripeException, Exception;

}
