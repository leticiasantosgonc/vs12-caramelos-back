package br.com.dbc.vemser.checkout.enums;

import lombok.Getter;

@Getter
public enum TamanhoProduto {

    PEQUENO("P"),
    MEDIO("M"),
    GRANDE("G");

    private final String tamanhoProduto;

    TamanhoProduto(String tamanhoProduto) {
        this.tamanhoProduto = tamanhoProduto;
    }

}
