package br.com.dbc.vemser.checkout.enums;

import lombok.Getter;

@Getter
public enum TipoProduto {

    LANCHE("LANCHE"),
    BEBIDA("BEBIDA"),
    SOBREMESA("SOBREMESA");

    private final String tipoProduto;

    TipoProduto(String tipoProduto) {
        this.tipoProduto = tipoProduto;
    }

}
