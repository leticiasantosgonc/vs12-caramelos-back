package br.com.dbc.vemser.checkout.dtos;

import br.com.dbc.vemser.checkout.enums.Game;
import lombok.Data;
import lombok.Getter;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
    @Getter
    private List<ItemInDTO> itens;

    @CPF
    @Pattern(regexp = "^(|\\d{11})$")
    private String cpf;

    @NotBlank
    @Size(min = 1, max = 255)
    private String observacao;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "o campo não deve ser nulo")
    private Game game;
}
