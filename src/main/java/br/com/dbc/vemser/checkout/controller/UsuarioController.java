package br.com.dbc.vemser.checkout.controller;

import br.com.dbc.vemser.checkout.docs.UsuarioControllerDoc;
import br.com.dbc.vemser.checkout.dtos.AdminInDTO;
import br.com.dbc.vemser.checkout.dtos.AdminOutDTO;
import br.com.dbc.vemser.checkout.dtos.RecuperacaoInDTO;
import br.com.dbc.vemser.checkout.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.checkout.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.validation.Valid;
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
    @PutMapping("/alterar-senha")
    public ResponseEntity<AdminOutDTO> update(@RequestBody @Valid RecuperacaoInDTO usuarioAtualizadado) throws RegraDeNegocioException {
        return new ResponseEntity<>(usuarioService.updateSenha(usuarioAtualizadado), HttpStatus.OK);
    }
    @DeleteMapping("/{idUsuario}")
    public ResponseEntity<Void> deleteAdmin(@PathVariable Integer idUsuario) throws RegraDeNegocioException{
        usuarioService.deleteAdmin(idUsuario);
        return ResponseEntity.ok().build();
    }
    @PostMapping("/enviar-email")
    public ResponseEntity<String> enviarEmailAlterarSenha(@RequestBody String email) throws MessagingException, RegraDeNegocioException {
        String linkRedefinirSenha = "http://vemser-dbc.dbccompany.com.br:39000/vemser/vs12-caramelos-front/redefinir-senha";
        usuarioService.enviarEmailAlterarSenha(email, linkRedefinirSenha);

        return ResponseEntity.ok("Email enviado com sucesso!");
    }

}
