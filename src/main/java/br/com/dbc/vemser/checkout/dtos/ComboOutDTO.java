package br.com.dbc.vemser.checkout.dtos;

import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
public class ComboOutDTO {

    private Integer quantidadeDisponivel;
    private List<LancheOutDTO> lanches = new ArrayList<>();
    private List<BebidaOutDTO> bebidas = new ArrayList<>();
    private BigDecimal precoTotal = calcularPreco();

    private BigDecimal calcularPreco() {
        BigDecimal valorTotal = BigDecimal.ZERO;

        for (LancheOutDTO lanche : lanches) {
            valorTotal.add(lanche.getPreco());
        }

        for (BebidaOutDTO bebida : bebidas) {
            valorTotal.add(bebida.getPreco());
        }

        return valorTotal;
    }

}
