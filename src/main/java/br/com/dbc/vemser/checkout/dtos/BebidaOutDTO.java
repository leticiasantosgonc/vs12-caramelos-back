package br.com.dbc.vemser.checkout.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.Valid;

@Data
@Valid
public class BebidaOutDTO extends BebidaInDTO{

    @Schema(description = "ID da bebida")
    private Integer idBebida;
}
