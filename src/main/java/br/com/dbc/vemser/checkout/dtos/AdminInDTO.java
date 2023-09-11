package br.com.dbc.vemser.checkout.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Valid
public class AdminInDTO {

    @Schema(description = "Informe o login", required = true, example = "loginTeste")
    @NotBlank(message = "O campo não deve ser nulo, branco ou vazio")
    private String login;

    @Schema(description = "Informe a senha", required = true, example = "senhaTeste")
    @NotBlank(message = "O campo não deve ser nulo, vazio e/ ou branco")
    @Size(min = 5, message = "a senha deve possuir 5 caracteres")
    private String senha;

}
