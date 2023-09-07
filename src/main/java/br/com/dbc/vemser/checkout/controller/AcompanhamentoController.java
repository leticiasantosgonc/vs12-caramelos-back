package br.com.dbc.vemser.checkout.controller;

import br.com.dbc.vemser.checkout.docs.AcompanhamentoControllerDoc;
import br.com.dbc.vemser.checkout.dtos.AcompanhamentoInDTO;
import br.com.dbc.vemser.checkout.dtos.AcompanhamentoOutDTO;
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
@RequestMapping("/acompanhamento")
public class AcompanhamentoController implements AcompanhamentoControllerDoc {
    private final ProdutoService produtoService;

    @PostMapping("/criar/acompanhamento")
    public ResponseEntity<AcompanhamentoOutDTO> createAcompanhamento(@RequestBody @Valid AcompanhamentoInDTO acompanhamentoInDTO) {
        AcompanhamentoOutDTO acompanhamentoOutDTO = produtoService.createAcompanhamento(acompanhamentoInDTO);
        return new ResponseEntity<>(acompanhamentoOutDTO, HttpStatus.CREATED);
    }

    @GetMapping("/listar/acompanhamentos")
    public List<AcompanhamentoOutDTO> findAllAcompanhamentos() {
        return produtoService.findAllAcompanhamento();
    }

    @GetMapping("/acompanhamento/{idAcompanhamento}")
    public ResponseEntity<AcompanhamentoOutDTO> findAcompanhamentoById(@PathVariable ("idAcompanhamento") @Positive Integer idAcompanhamento) throws RegraDeNegocioException {
        return new ResponseEntity<>(produtoService.findAcompanhamentoById(idAcompanhamento), HttpStatus.OK);
    }

    @PutMapping("/acompanhamento/{idAcompanhamento}")
    public ResponseEntity<AcompanhamentoOutDTO> updateAcompanhamento(@PathVariable("idAcompanhamento") @Positive Integer idAcompanhamento, @RequestBody @Valid AcompanhamentoOutDTO acompanhamentoEntrada) throws RegraDeNegocioException {
        return new ResponseEntity<>(produtoService.updateAcompanhamento(idAcompanhamento, acompanhamentoEntrada), HttpStatus.OK);
    }

    @DeleteMapping("/acompanhamento/{idAcompanhamento}")
    public ResponseEntity<Void> deleteAcompanhamentoById(@PathVariable("idAcompanhamento") @Positive Integer idAcompanhamento){
        produtoService.deleteAcompanhamentoById(idAcompanhamento);
        return ResponseEntity.ok().build();
    }
}
