package br.com.dbc.vemser.checkout.dtos;

import br.com.dbc.vemser.checkout.enums.DietaProduto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.math.BigDecimal;

@Data
@Valid
public class ComboInDTO {

    @NotBlank(message = "O campo nome não pode estar vazio")
    @Size(min = 1, max = 100)
    @Pattern(regexp = "^(?!\\d+$)[a-zA-Z\\d\\s]*$", message = "O campo nome não pode conter apenas números")
    @Schema(description = "Nome do combo", example = "Vira-Lata Combão Tudo", required = true)
    private String nome;

    @NotBlank(message = "O campo descrição não pode estar vazio")
    @Size(min = 1, max = 300)
    @Schema(description = "Descrição do combo", example = "Esse combo tem tudo, lanche, bebida e sobremesa", required = true)
    private String descricao;

    @NotBlank(message = "O campo imagem não pode estar vazio")
    @Size(max = 1000000, message = "A string de Base64 não pode ter mais de 1MB")
    private String imagem;

    @NotNull(message = "O campo quantidade não pode estar nulo")
    @Positive(message = "O número deve ser positivo")
    @Schema(description = "Quantidade do combo", example = "10", required = true)
    private Integer quantidade;

    @NotNull(message = "O campo dieta não pode estar vazio")
    @Enumerated(EnumType.STRING)
    @Schema(description = "Dieta do produto: vegetariano ou não", example = "LANCHE_VEGETARIANO", required = true)
    private DietaProduto dietaProduto;

    @NotNull(message = "O campo preço não pode ser nulo")
    @DecimalMin(value = "0.01", inclusive = false, message = "O preço deve ser maior do que zero")
    @DecimalMax(value = "999.99", message = "O preço não pode ser maior do que 999.99")
    @Digits(integer = 3, fraction = 2, message = "O preço deve ter no máximo três dígitos inteiros e dois dígitos decimais")
    @Schema(description = "Preço do acompanhamento", example = "29.99", required = true)
    private BigDecimal preco;
}
