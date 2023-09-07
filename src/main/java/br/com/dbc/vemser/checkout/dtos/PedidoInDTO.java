package br.com.dbc.vemser.checkout.dtos;

import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@Valid
public class PedidoInDTO {

    @NotNull
    private List<ItemInDTO> itens;

    @CPF
    @Pattern(regexp = "^(|\\d{11})$")
    private String cpf;

    @NotBlank
    @Size(min = 1, max = 255)
    private String observacao;

}
