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
@RequestMapping("/admin")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @GetMapping
    public List<AdminOutDTO> findAllUsuarios() {
        List<AdminOutDTO> admins = usuarioService.findAll();
        return admins;
    }
    @PostMapping("/cadastrar/admin")
    public ResponseEntity<AdminOutDTO> createAdmin(@RequestBody AdminInDTO usuario) throws Exception{
        return new ResponseEntity<>(usuarioService.createAdmin(usuario), HttpStatus.OK);
    }
    @PutMapping("/alterar-senha/{idUsuario}")
    public ResponseEntity<AdminOutDTO> updateSenha(@PathVariable Integer idUsuario, @RequestBody String senha) throws Exception {
        return new ResponseEntity<>(usuarioService.updateSenha(idUsuario, senha), HttpStatus.OK);
    }
    @DeleteMapping("/{idUsuario}")
    public ResponseEntity<Void> deleteAdmin(@PathVariable Integer idUsuario) {
        usuarioService.deleteAdmin(idUsuario);
        return ResponseEntity.ok().build();
    }

}
