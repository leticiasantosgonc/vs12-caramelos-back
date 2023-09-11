package br.com.dbc.vemser.checkout.dtos;

import br.com.dbc.vemser.checkout.enums.TipoProduto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Lob;

@Data
@EqualsAndHashCode(callSuper = true)
public class SobremesaOutDTO extends SobremesaInDTO {

    @Enumerated(EnumType.STRING)
    private TipoProduto tipoProduto;

    @Schema(description = "Id do produto")
    private Integer idProduto;

    @Lob
    @Type(type = "org.hibernate.type.BinaryType")
    private byte[] dados_img;

    private String tipo_img;

}
