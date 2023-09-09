package br.com.dbc.vemser.checkout.dtos;

import br.com.dbc.vemser.checkout.enums.TipoProduto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
@EqualsAndHashCode(callSuper = true)
public class SobremesaOutDTO extends SobremesaInDTO {

    @Enumerated(EnumType.STRING)
    private TipoProduto tipoProduto;

    @Schema(description = "Id do produto")
    private Integer idProduto;

}
