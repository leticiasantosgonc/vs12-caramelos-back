package br.com.dbc.vemser.checkout.controller;

import br.com.dbc.vemser.checkout.docs.BebidaControllerDoc;
import br.com.dbc.vemser.checkout.dtos.BebidaInDTO;
import br.com.dbc.vemser.checkout.dtos.BebidaOutDTO;
import br.com.dbc.vemser.checkout.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.checkout.service.ProdutoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/bebida")
public class BebidaController implements BebidaControllerDoc {
    private final ProdutoService produtoService;
    @PostMapping("/criar/bebida")
    public ResponseEntity<BebidaOutDTO> createBebida(@RequestBody @Valid BebidaInDTO bebidaInDTO) {
        return new ResponseEntity<>(produtoService.createBebida(bebidaInDTO), HttpStatus.CREATED);
    }

    @GetMapping("/listar/bebidas")
    public List<BebidaOutDTO> findAllBebidas() {
        return produtoService.findAllBebidas();
    }

    @GetMapping("/bebida/{idBebida}")
    public ResponseEntity<BebidaOutDTO> findBebidaById(@PathVariable("idBebida") @Positive Integer idBebida) throws RegraDeNegocioException {
        return new ResponseEntity<>(produtoService.findBebidaById(idBebida), HttpStatus.OK);
    }

    @PutMapping("/bebida/{idBebida}")
    public ResponseEntity<BebidaOutDTO> updateBebida(@PathVariable("idBebida") @Positive Integer idBebida, @RequestBody @Valid BebidaOutDTO bebidaEntrada) throws RegraDeNegocioException {
        return new ResponseEntity<>(produtoService.updateBebida(idBebida, bebidaEntrada), HttpStatus.OK);
    }

    @DeleteMapping("/bebida/{idBebida}")
    public ResponseEntity<Void> deleteBebidaById(@PathVariable("idBebida") @Positive Integer idBebida){
        produtoService.deleteBebidaById(idBebida);
        return ResponseEntity.ok().build();
    }
}
