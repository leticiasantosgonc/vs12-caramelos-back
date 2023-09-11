package br.com.dbc.vemser.checkout.controller;

import br.com.dbc.vemser.checkout.dtos.AdminInDTO;
import br.com.dbc.vemser.checkout.entities.Usuario;
import br.com.dbc.vemser.checkout.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.checkout.security.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Valid
@RequestMapping("/auth")
@RestController
@RequiredArgsConstructor
public class AuthController {
    public final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    @PostMapping
    public String auth(@RequestBody @Valid AdminInDTO loginDTO) throws RegraDeNegocioException {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(
                        loginDTO.getLogin(),
                        loginDTO.getSenha()
                );

        Authentication authentication =
                authenticationManager.authenticate(
                        usernamePasswordAuthenticationToken);

        Usuario usuarioValidado = (Usuario) authentication.getPrincipal();

        return tokenService.generateToken(usuarioValidado);
    }
}
