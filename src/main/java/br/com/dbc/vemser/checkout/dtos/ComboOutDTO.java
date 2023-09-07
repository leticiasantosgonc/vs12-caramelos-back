package br.com.dbc.vemser.checkout.dtos;

import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
public class ComboOutDTO {

    private String nome;
    private String descricao;
    private List<ComboProdutoOutDTO> lanches = new ArrayList<>();
    private List<ComboProdutoOutDTO> bebidas = new ArrayList<>();
    private Integer quantidadeDisponivel;
    private BigDecimal precoTotal = null;

}
