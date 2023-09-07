package br.com.dbc.vemser.checkout.controller;

import br.com.dbc.vemser.checkout.docs.ComboControllerDoc;
import br.com.dbc.vemser.checkout.dtos.ComboInDTO;
import br.com.dbc.vemser.checkout.dtos.ComboOutDTO;
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
@RequestMapping("/combo")
@RequiredArgsConstructor
@Validated
public class ComboController implements ComboControllerDoc {

    private final ProdutoService produtoService;

    @PostMapping("/criar/combo")
    public ResponseEntity<ComboOutDTO> createCombo(@RequestBody @Valid ComboInDTO comboInDTO) {
        ComboOutDTO comboOutDTO = produtoService.createCombo(comboInDTO);
        return new ResponseEntity<>(comboOutDTO, HttpStatus.CREATED);
    }

    @GetMapping("/listar/combo")
    public List<ComboOutDTO> findAllCombos() {
        return produtoService.findAllCombo();
    }

    @PutMapping("/combo/{idCombo}")
    public ResponseEntity<ComboOutDTO> updateCombo(@PathVariable("idCombo") @Positive Integer idCombo, @RequestBody @Valid ComboOutDTO comboEntrada) throws RegraDeNegocioException {
        return new ResponseEntity<>(produtoService.updateCombo(idCombo, comboEntrada), HttpStatus.OK);
    }

    @DeleteMapping("/combo/{idCombo}")
    public ResponseEntity<Void> deleteComboById(@PathVariable("idCombo") @Positive Integer idCombo){
        produtoService.deleteComboById(idCombo);
        return ResponseEntity.ok().build();
    }

}
