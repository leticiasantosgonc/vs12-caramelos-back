package br.com.dbc.vemser.checkout.controller;

import br.com.dbc.vemser.checkout.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.checkout.service.ComboService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequestMapping("/combo")
@RequiredArgsConstructor
@Validated
public class ComboController {

    private final ComboService comboService;

//    @PostMapping("/criar")
//    public ResponseEntity<ComboOutDTO> createCombo(@RequestBody ComboInDTO comboInDTO) throws RegraDeNegocioException {
//        return new ResponseEntity<>(comboService.createCombo(comboInDTO), HttpStatus.CREATED);
//    }
//
//    @GetMapping("/listar/combos")
//    public ResponseEntity<List<ComboOutDTO>> findAllCombos() {
//        return new ResponseEntity<>(comboService.findAllCombos(), HttpStatus.OK);
//    }
//
//    @GetMapping("/listar/{idCombo}")
//    public ResponseEntity<ComboOutDTO> findComboById(@PathVariable @Positive Integer idCombo) throws RegraDeNegocioException {
//        return new ResponseEntity<>(comboService.findComboById(idCombo), HttpStatus.OK);
//    }

}
