package br.com.dbc.vemser.checkout.dtos;

import br.com.dbc.vemser.checkout.enums.MarcaProduto;
import br.com.dbc.vemser.checkout.enums.TamanhoProduto;
import br.com.dbc.vemser.checkout.enums.TipoProduto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.List;

@Data
@Valid
public class BebidaInDTO {

    @NotBlank(message = "O campo não dever ser vazio e/ou nulo e/ou branco")
    @Schema(description = "Nome da bebida", required = true, example = "Pitchulinha")
    private String nome;

    @NotBlank(message = "O campo não dever ser vazio e/ou nulo e/ou branco")
    @Schema(description = "Descrição da bebida", required = true, example = "Refrigerante")
    private String descricao;

//    private String imagem;

    @Positive(message = "A quantidade precisa ser positiva")
    @Schema(description = "Quantidade de bebida", required = true, example = "50 copos")
    private Integer quantidade;

    @NotNull(message = "O campo não dever ser vazio e/ou nulo e/ou branco")
    @DecimalMin(value = "0.01", inclusive = false, message = "O preço deve ser maior do que zero")
    @DecimalMax(value = "999.99", message = "O preço não pode ser maior do que 999.99")
    @Digits(integer = 3, fraction = 2, message = "O preço deve ter no máximo três dígitos inteiros e dois dígitos decimais")
    @Schema(description = "Preço da bebida", required = true, example = "5.90")
    private BigDecimal preco;

    @NotNull(message = "O campo não dever ser vazio e/ou nulo e/ou branco")
    @Enumerated(EnumType.STRING)
    @Schema(description = "Tipo do produto", required = true, example = "BEBIDA")
    private TipoProduto tipoProduto;

    @NotNull(message = "O campo não dever ser vazio e/ou nulo e/ou branco")
    @Enumerated(EnumType.STRING)
    @Schema(description = "Marca da bebida", required = true, example = "PEPSI")
    private MarcaProduto marca;

    @NotNull(message = "O campo não dever ser vazio e/ou nulo e/ou branco")
    @Enumerated(EnumType.STRING)
    @Schema(description = "Tamanho da bebida", required = true, example = "MEDIO")
    private TamanhoProduto tamanhoProduto;
}
