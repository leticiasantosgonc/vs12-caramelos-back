package br.com.dbc.vemser.checkout.docs;

import br.com.dbc.vemser.checkout.dtos.AdminInDTO;
import br.com.dbc.vemser.checkout.dtos.AdminOutDTO;
import br.com.dbc.vemser.checkout.exceptions.RegraDeNegocioException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

public interface UsuarioControllerDoc {
    @Operation(summary = "Buscar todos os usuários", description = "Deve buscar todos os usuários")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "201", description = "Requisição realizada com sucesso"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @GetMapping
    public List<AdminOutDTO> findAllUsuarios();

    @Operation(summary = "Criar um administrador", description = "Deve criar um administrador")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "201", description = "Requisição realizada com sucesso"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @PostMapping("/cadastrar/admin")
    public ResponseEntity<AdminOutDTO> createAdmin(@RequestBody AdminInDTO usuario) throws RegraDeNegocioException;

    @Operation(summary = "Atualizar uma senha", description = "Deve atualizar uma senha")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "201", description = "Requisição realizada com sucesso"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @PutMapping("/alterar-senha/{idUsuario}")
    public ResponseEntity<AdminOutDTO> updateSenha(@PathVariable Integer idUsuario, @RequestBody @Valid AdminInDTO usuarioAtualizado) throws RegraDeNegocioException;

    @Operation(summary = "Deletar uma administrador", description = "Deve deletar um administrador")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "201", description = "Requisição realizada com sucesso"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @DeleteMapping("/{idUsuario}")
    public ResponseEntity<Void> deleteAdmin(@PathVariable Integer idUsuario);
}
