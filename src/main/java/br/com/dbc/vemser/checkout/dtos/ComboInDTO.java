package br.com.dbc.vemser.checkout.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@Valid
public class ComboInDTO {

    @NotBlank(message = "O campo nome não pode estar vazio")
    @Size(min = 1, max = 100)
    @Schema(description = "Nome do lanche", example = "Cachorro-quente", required = true)
    private String nome;

    @NotBlank(message = "O campo descrição não pode estar vazio")
    @Size(min = 1, max = 300)
    @Schema(description = "Descrição do lanche", example = "É definitivamente um cachorro quente", required = true)
    private String descricao;

    @NotBlank(message = "O campo imagem não pode estar vazio")
    @Size(max = 1000000, message = "A string de Base64 não pode ter mais de 1MB")
    private String imagem;

    @NotNull(message = "O campo quantidae não pode ser nulo")
    @Positive(message = "O campo quantidade não pode ser negativo")
    private Integer quantidade;

    private List<Integer> indexesLanches;
    private List<Integer> indexesBebidas;

}
