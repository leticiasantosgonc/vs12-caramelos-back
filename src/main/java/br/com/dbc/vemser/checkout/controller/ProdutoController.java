package br.com.dbc.vemser.checkout.controller;

import br.com.dbc.vemser.checkout.dtos.LancheInDTO;
import br.com.dbc.vemser.checkout.dtos.LancheOutDTO;
import br.com.dbc.vemser.checkout.entities.Produto;
import br.com.dbc.vemser.checkout.service.ProdutoService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
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

    @PostMapping
    public ResponseEntity<LancheOutDTO> createLanche(@RequestBody @Valid LancheInDTO lancheInDTO) {
        LancheOutDTO lancheOutDTO = produtoService.createLanche(lancheInDTO);

        return new ResponseEntity<>(lancheOutDTO, HttpStatus.CREATED);
    }

    @GetMapping("/{idLanche}")
    public ResponseEntity<LancheOutDTO> findLancheById(@PathVariable @Positive Integer idLanche) throws Exception {
        LancheOutDTO lancheOutDTO = produtoService.findLancheById(idLanche);

        return new ResponseEntity<>(lancheOutDTO, HttpStatus.OK);
    }

    @GetMapping("/lanches")
    public ResponseEntity<List<LancheOutDTO>> findAllLanches() {
        List<LancheOutDTO> lancheOutDTOList = produtoService.findAllLanches();

        return new ResponseEntity<>(lancheOutDTOList, HttpStatus.OK);
    }

    @PutMapping("/{idLanche}")
    public ResponseEntity<LancheOutDTO> updateLancheById(@PathVariable @Positive Integer idLanche, @RequestBody @Valid LancheInDTO lancheInDTO) throws Exception {
        LancheOutDTO lancheOutDTO = produtoService.updateLancheById(idLanche, lancheInDTO);

        return new ResponseEntity<>(lancheOutDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{idLanche}")
    public ResponseEntity<Void> deleteById(@PathVariable @Positive Integer idLanche) throws Exception {
        produtoService.deleteLancheById(idLanche);

        return ResponseEntity.ok().build();
    }

    @GetMapping
    public List<Produto> findAll() {
        return produtoService.findAll();
    }

}
