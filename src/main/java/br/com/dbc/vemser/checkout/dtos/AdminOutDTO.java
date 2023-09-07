package br.com.dbc.vemser.checkout.dtos;

import br.com.dbc.vemser.checkout.entities.Role;
import lombok.Data;

@Data
public class AdminOutDTO {
    private Integer idUsuario;
    private String login;
    private Role role;
}
