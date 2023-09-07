package br.com.dbc.vemser.checkout.dtos;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ComboProdutoOutDTO {

    private String nome;
    private String descricao;
    private String imagem;
    private BigDecimal preco;

}
