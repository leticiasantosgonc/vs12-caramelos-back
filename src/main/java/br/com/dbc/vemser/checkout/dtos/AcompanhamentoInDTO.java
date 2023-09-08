package br.com.dbc.vemser.checkout.dtos;

import br.com.dbc.vemser.checkout.enums.DietaProduto;
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
public class AcompanhamentoInDTO {
    @NotBlank(message = "O campo nome não pode estar vazio")
    @Size(min = 1, max = 100)
    @Pattern(regexp = "^[a-zA-ZÀ-ÖØ-öø-ÿ -]*$")
    @Schema(description = "Nome do acompanhamento", example = "Batata Frita", required = true)
    private String nome;

    @NotBlank(message = "O campo descrição não pode estar vazio")
    @Size(min = 1, max = 300)
    @Schema(description = "Descrição do acompanhamento", example = "É definitivamente uma batata frita", required = true)
    private String descricao;

    @NotBlank(message = "O campo imagem não pode estar vazio")
    @Size(max = 1000000, message = "A string de Base64 não pode ter mais de 1MB")
    private String imagem;

    @NotNull(message = "O campo quantidade não pode estar nulo")
    @Positive(message = "O número deve ser positivo")
    @Schema(description = "Quantidade do acompanhamento", example = "10", required = true)
    private Integer quantidade;

    @NotNull(message = "O campo tamanho não pode ser nulo")
    @Enumerated(EnumType.STRING)
    @Schema(description = "Tamanho do acompanhamento", example = "PEQUENO", required = true)
    private TamanhoProduto tamanhoProduto;

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
