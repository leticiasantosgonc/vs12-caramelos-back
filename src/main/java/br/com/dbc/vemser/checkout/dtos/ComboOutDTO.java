package br.com.dbc.vemser.checkout.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class ComboOutDTO extends ComboInDTO {
    @Schema(description = "ID do produto", example = "1")
    private Integer idProduto;
}
