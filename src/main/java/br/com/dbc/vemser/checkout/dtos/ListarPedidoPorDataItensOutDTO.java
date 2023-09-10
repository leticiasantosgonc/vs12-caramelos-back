package br.com.dbc.vemser.checkout.dtos;

import br.com.dbc.vemser.checkout.enums.TipoProduto;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
public class ListarPedidoPorDataItensOutDTO {

    @Positive(message = "O id deve ser positivo")
    private Integer idProduto;

    @Positive(message = "A quantidade deve ser positiva")
    private Integer quantidade;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "O tipo não deve ser nulo")
    private TipoProduto tipoProduto;

}
