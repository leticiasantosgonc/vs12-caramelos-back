package br.com.dbc.vemser.checkout.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class PedidoOutDTO extends PedidoInDTO {

    @Schema(description = "ID do pedido", example = "1")
    private Integer idPedido;

}
