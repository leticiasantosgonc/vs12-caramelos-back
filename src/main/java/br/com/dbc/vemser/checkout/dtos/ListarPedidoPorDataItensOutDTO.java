package br.com.dbc.vemser.checkout.dtos;

import br.com.dbc.vemser.checkout.enums.TipoProduto;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@Data
public class ListarPedidoPorDataItensOutDTO {

    private Integer idProduto;

    @Positive(message = "A quantidade deve ser positiva")
    private Integer quantidade;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "O tipo não deve ser nulo")
    private TipoProduto tipoProduto;

    private BigDecimal valorDoPedido;

}
