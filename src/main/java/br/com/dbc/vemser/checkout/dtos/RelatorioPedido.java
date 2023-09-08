package br.com.dbc.vemser.checkout.dtos;

import br.com.dbc.vemser.checkout.enums.StatusPedido;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RelatorioPedido {
    private Integer idPedido;
    private List<RelatorioItemPedidoDTO> itens = new ArrayList<>();
    private LocalDate dataPedido;
    private StatusPedido status;
    private BigDecimal valorTotal;
    private LocalDate dataRelatorio;
}
