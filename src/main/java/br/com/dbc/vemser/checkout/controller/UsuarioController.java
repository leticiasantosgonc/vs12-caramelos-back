package br.com.dbc.vemser.checkout.controller;

import br.com.dbc.vemser.checkout.dtos.AdminInDTO;
import br.com.dbc.vemser.checkout.dtos.AdminOutDTO;
import br.com.dbc.vemser.checkout.entities.Usuario;
import br.com.dbc.vemser.checkout.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @GetMapping
    public List<Usuario> findAll(){
        return usuarioService.findAll();
    }
    @PostMapping("/cadastrar/admin")
    public ResponseEntity<AdminOutDTO> createAdmin(@RequestBody AdminInDTO usuario) throws Exception{
        return new ResponseEntity<>(usuarioService.createAdmin(usuario), HttpStatus.OK);
    }

}
