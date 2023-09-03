package br.com.dbc.vemser.checkout.enums;

import lombok.Getter;

@Getter
public enum TipoProduto {

    LANCHE("L"),
    BEBIDA("B"),
    SOBREMESA("S");

    private final String tipoProduto;

    TipoProduto(String tipoProduto) {
        this.tipoProduto = tipoProduto;
    }

}
