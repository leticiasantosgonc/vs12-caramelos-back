package br.com.dbc.vemser.checkout.enums;

import lombok.Getter;

@Getter
public enum DietaProduto {

    LANCHE_NAO_VEGETARIANO("0"),
    LANCHE_VEGETARIANO("1");

    private final String dieta;

    DietaProduto(String dieta) {
        this.dieta = dieta;
    }

}
