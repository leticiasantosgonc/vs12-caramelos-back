package br.com.dbc.vemser.checkout.dtos;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ComboOutDTO {

    private Integer quantidade;
    private List<LancheOutDTO> lanches = new ArrayList<>();
    private List<BebidaOutDTO> bebidas = new ArrayList<>();

    public void addLanche(LancheOutDTO lanche) {
        this.lanches.add(lanche);
    }

    public void addBebida(BebidaOutDTO bebida) {
        this.bebidas.add(bebida);
    }

}
