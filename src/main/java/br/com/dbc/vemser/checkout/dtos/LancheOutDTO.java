package br.com.dbc.vemser.checkout.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class LancheOutDTO extends LancheInDTO {

    @Schema(description = "ID do produto", example = "1")
    private Integer idProduto;

}
