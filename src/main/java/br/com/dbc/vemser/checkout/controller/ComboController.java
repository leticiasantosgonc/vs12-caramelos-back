package br.com.dbc.vemser.checkout.controller;

import br.com.dbc.vemser.checkout.dtos.ComboInDTO;
import br.com.dbc.vemser.checkout.dtos.ComboOutDTO;
import br.com.dbc.vemser.checkout.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.checkout.service.PedidoService;
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

    private final PedidoService pedidoService;

    @PostMapping("/criar")
    public ResponseEntity<ComboOutDTO> createCombo(@RequestBody ComboInDTO comboInDTO) throws RegraDeNegocioException {
        return new ResponseEntity<>(pedidoService.createCombo(comboInDTO), HttpStatus.CREATED);
    }

    @GetMapping("/listar/combos")
    public ResponseEntity<List<ComboOutDTO>> findAllCombos() {
        return new ResponseEntity<>(pedidoService.findAllCombos(), HttpStatus.OK);
    }

    @GetMapping("/listar/{idCombo}")
    public ResponseEntity<ComboOutDTO> findComboById(@PathVariable @Positive Integer idCombo) throws RegraDeNegocioException {
        return new ResponseEntity<>(pedidoService.findComboById(idCombo), HttpStatus.OK);
    }

}
