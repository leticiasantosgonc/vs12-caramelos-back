package br.com.dbc.vemser.checkout.enums;

import lombok.Getter;

@Getter
public enum MarcaProduto {

    COCA_COLA("COCA_COLA"),
    FANTA("FANTA"),
    FRUKI("FRUKI"),
    GUARANA_ANTARCTICA("GUARANA_ANTARCTICA"),
    PEPSI("PEPSI"),
    CHARRUA("CHARRUA"),
    KUAT("KUAT"),
    DOLLY("DOLLY"),
    SPRITE("SPRITE"),
    SUKITA("SUKITA");

    private final String marcaProduto;

    MarcaProduto(String marcaProduto) {
        this.marcaProduto = marcaProduto;
    }

}
