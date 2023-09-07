package br.com.dbc.vemser.checkout.dtos;

import br.com.dbc.vemser.checkout.enums.MarcaProduto;
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
public class BebidaInDTO {

    @NotBlank(message = "O campo nome não pode estar vazio")
    @Size(min = 1, max = 100)
    @Pattern(regexp = "^(?!\\d+$)[a-zA-Z\\d\\s]*$", message = "O campo nome não pode conter apenas números")
    @Schema(description = "Nome da bebida", required = true, example = "Pitchulinha")
    private String nome;

    @NotBlank(message = "O campo descrição não pode estar vazio")
    @Size(min = 1, max = 300)
    @Schema(description = "Descrição da bebida", required = true, example = "Refrigerante")
    private String descricao;

    @NotBlank(message = "O campo imagem não pode estar vazio")
    @Size(max = 1000000, message = "A string de Base64 não pode ter mais de 1MB")
    private String imagem;

    @NotNull(message = "O campo quantidade não pode estar nulo")
    @Positive(message = "A quantidade precisa ser positiva")
    @Schema(description = "Quantidade de bebida", required = true, example = "50 copos")
    private Integer quantidade;

    @NotNull(message = "O campo preço não pode ser nulo")
    @DecimalMin(value = "0.01", inclusive = false, message = "O preço deve ser maior do que zero")
    @DecimalMax(value = "999.99", message = "O preço não pode ser maior do que 999.99")
    @Digits(integer = 3, fraction = 2, message = "O preço deve ter no máximo três dígitos inteiros e dois dígitos decimais")
    @Schema(description = "Preço da bebida", required = true, example = "5.90")
    private BigDecimal preco;

    @NotNull(message = "O campo não dever ser vazio e/ou nulo e/ou branco")
    @Enumerated(EnumType.STRING)
    @Schema(description = "Marca da bebida", required = true, example = "PEPSI")
    private MarcaProduto marca;

    @NotNull(message = "O campo tamanho não pode ser nulo")
    @Enumerated(EnumType.STRING)
    @Schema(description = "Tamanho da bebida", required = true, example = "MEDIO")
    private TamanhoProduto tamanhoProduto;

}
