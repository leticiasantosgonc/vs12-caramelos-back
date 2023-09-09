package br.com.dbc.vemser.checkout.controller;

import br.com.dbc.vemser.checkout.docs.SobremesaControllerDoc;
import br.com.dbc.vemser.checkout.dtos.SobremesaInDTO;
import br.com.dbc.vemser.checkout.dtos.SobremesaOutDTO;
import br.com.dbc.vemser.checkout.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.checkout.service.ProdutoService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/sobremesa")
@RequiredArgsConstructor
@Validated
public class SobremesaController implements SobremesaControllerDoc {
    private final ProdutoService produtoService;

    @PostMapping("/criar/sobremesa")
    public ResponseEntity<SobremesaOutDTO> saveSobremesa(@RequestBody @Valid SobremesaInDTO sobremesa) {
        return new ResponseEntity<>(produtoService.saveSobremesa(sobremesa), HttpStatus.CREATED);
    }

    @GetMapping("/listar/sobremesas")
    public ResponseEntity<List<SobremesaOutDTO>> findAllSobremesas() {
        return new ResponseEntity<>(produtoService.findAllSobremesas(),HttpStatus.OK);
    }

    @GetMapping("/sobremesa/{idSobremesa}")
    public ResponseEntity<SobremesaOutDTO>findSobremesaById(@PathVariable @Positive Integer idSobremesa) throws RegraDeNegocioException {
        return new ResponseEntity<>(produtoService.findSobremesaByid(idSobremesa),HttpStatus.OK);
    }

    @PutMapping("/sobremesa/{idSobremesa}")
    public ResponseEntity<SobremesaOutDTO> updateSobremesa(@RequestBody @Valid SobremesaInDTO sobremesaAtualizada,
                                                           @PathVariable @Positive Integer idSobremesa) throws RegraDeNegocioException {
        return new ResponseEntity<>(produtoService.updateSobremesa(sobremesaAtualizada,idSobremesa),HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/sobremesa/{idSobremesa}")
    public ResponseEntity<Void> deleteSobremesa(@PathVariable @Positive Integer idSobremesa) throws RegraDeNegocioException, DataIntegrityViolationException{

            produtoService.delete(idSobremesa);

        return ResponseEntity.ok().build();
    }
}
