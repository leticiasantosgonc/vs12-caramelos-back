package br.com.dbc.vemser.checkout.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RelatorioItemPedidoDTO {
    private String nome;
    private long quantidade;
}
