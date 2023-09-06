package br.com.dbc.vemser.checkout.controller;

import br.com.dbc.vemser.checkout.docs.UsuarioControllerDoc;
import br.com.dbc.vemser.checkout.dtos.AdminInDTO;
import br.com.dbc.vemser.checkout.dtos.AdminOutDTO;
import br.com.dbc.vemser.checkout.entities.Usuario;
import br.com.dbc.vemser.checkout.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.checkout.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.plaf.synth.Region;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class UsuarioController implements UsuarioControllerDoc {

    private final UsuarioService usuarioService;

    @GetMapping
    public List<AdminOutDTO> findAllUsuarios() {
        List<AdminOutDTO> admins = usuarioService.findAll();
        return admins;
    }
    @PostMapping("/cadastrar/admin")
    public ResponseEntity<AdminOutDTO> createAdmin(@RequestBody AdminInDTO usuario) throws RegraDeNegocioException {
        return new ResponseEntity<>(usuarioService.createAdmin(usuario), HttpStatus.OK);
    }
    @PutMapping("/alterar-senha/{idUsuario}")
    public ResponseEntity<AdminOutDTO> update(@PathVariable Integer idUsuario,
                                                   @RequestBody @Valid AdminInDTO usuarioAtualizadado) throws RegraDeNegocioException {
        return new ResponseEntity<>(usuarioService.updateSenha(idUsuario, usuarioAtualizadado), HttpStatus.OK);
    }
    @DeleteMapping("/{idUsuario}")
    public ResponseEntity<Void> deleteAdmin(@PathVariable Integer idUsuario) {
        usuarioService.deleteAdmin(idUsuario);
        return ResponseEntity.ok().build();
    }

}
