package br.com.dbc.vemser.checkout.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Type;

import javax.persistence.Lob;

@Data
@EqualsAndHashCode(callSuper = true)
public class AcompanhamentoOutDTO extends AcompanhamentoInDTO {

    @Schema(description = "ID do produto", example = "1")
    private Integer idProduto;
    @Lob
    @Type(type = "org.hibernate.type.BinaryType")
    private byte[] dados_img;

    private String tipo_img;

}
