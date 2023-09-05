package br.com.dbc.vemser.checkout.dtos;

import br.com.dbc.vemser.checkout.enums.TamanhoProduto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.math.BigDecimal;

@Data
@Valid
public class SobremesaInDTO {
    @Schema(description = "Insira o nome do produto", example = "gelatina")
    @NotBlank(message = "O campo não dever ser vazio e/ou nulo e/ou branco")
    private String nome;

    @Schema(description = "Descrição sobre o produto", example = "Um produto produzido a base de derivado de leite")
    @NotBlank(message = "O campo não dever ser vazio e/ou nulo e/ou branco")
    @Size(min = 3, max = 500,message = "O campo deve possuir no mínimo 3 e maximo 500 caracteres")
    private String descricao;

    @NotBlank(message = "O campo imagem não pode estar vazio")
    @Size(max = 1000000, message = "A string de Base64 não pode ter mais de 1MB")
    private String imagem;

    @Schema(description = "Quantidade a ser inserida do produto", example = "10")
    @Positive(message = "O campo deve ser positivo")
    private Integer quantidade;

    @Schema(description = "Insirao tamanho do produto", example = "PEQUENO")
    @NotNull(message = "O campo não deve ser nulo")
    @Enumerated(EnumType.STRING)
    private TamanhoProduto tamanhoProduto;

    @Schema(description = "Insira o preco do produto", example = "15.90")
    @NotNull(message = "O campo preço não pode ser nulo")
    @DecimalMin(value = "0.01", inclusive = false, message = "O preço deve ser maior do que zero")
    @DecimalMax(value = "999.99", message = "O preço não pode ser maior do que 999.99")
    @Digits(integer = 3, fraction = 2, message = "O preço deve ter no máximo três dígitos inteiros e dois dígitos decimais")
    private BigDecimal preco;

}
