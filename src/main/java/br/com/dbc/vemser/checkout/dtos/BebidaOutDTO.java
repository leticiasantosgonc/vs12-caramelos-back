package br.com.dbc.vemser.checkout.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Data
public class BebidaOutDTO extends BebidaInDTO{

    @Schema(description = "ID da bebida")
    private Integer idProduto;

}
