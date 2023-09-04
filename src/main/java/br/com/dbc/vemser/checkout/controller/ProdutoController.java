package br.com.dbc.vemser.checkout.controller;

import br.com.dbc.vemser.checkout.dtos.BebidaInDTO;
import br.com.dbc.vemser.checkout.dtos.BebidaOutDTO;
import br.com.dbc.vemser.checkout.entities.Produto;
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
@RequestMapping("/produto")
@RequiredArgsConstructor
@Validated
public class ProdutoController {

    private final ProdutoService produtoService;

    @GetMapping
    public List<Produto> findAll() {
        return produtoService.findAll();
    }


    //rotas bebida
    @GetMapping("/listar-bebidas")
    public List<Produto> findAllBebidas() {
        return produtoService.findAllBebidas();
    }
    @GetMapping("/{idBebida}")
    public ResponseEntity<BebidaOutDTO> findBebidaById(@PathVariable ("idBebida") @Positive Integer idBebida) throws Exception {
        return new ResponseEntity<>(produtoService.findBebidaById(idBebida), HttpStatus.OK);
    }
    @PostMapping("/criar-bebida")
    public ResponseEntity<BebidaOutDTO>createBebida(@RequestBody @Valid BebidaInDTO bebidaInDTO){
        return new ResponseEntity<>(produtoService.createBebida(bebidaInDTO), HttpStatus.CREATED);
    }
    @PutMapping("/{idBebida}")
    public ResponseEntity<BebidaOutDTO> updateBebida(@PathVariable("idBebida") @Positive Integer idBebida, @RequestBody @Valid BebidaOutDTO bebidaEntrada) throws Exception {
        return new ResponseEntity<>(produtoService.updateBebida(idBebida, bebidaEntrada), HttpStatus.OK);
    }
    @DeleteMapping("/{idBebida}")
    public ResponseEntity<Void> delete(@PathVariable("idBebida") @Positive Integer idBebida) throws Exception {
        produtoService.deleteBebidaById(idBebida);
        return ResponseEntity.ok().build();
    }

}
