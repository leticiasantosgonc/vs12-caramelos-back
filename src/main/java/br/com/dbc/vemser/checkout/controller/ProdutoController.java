package br.com.dbc.vemser.checkout.controller;


import br.com.dbc.vemser.checkout.docs.ProdutoControllerDoc;
import br.com.dbc.vemser.checkout.dtos.*;
import br.com.dbc.vemser.checkout.entities.Produto;
import br.com.dbc.vemser.checkout.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.checkout.service.ProdutoService;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
public class ProdutoController implements ProdutoControllerDoc {

    private final ProdutoService produtoService;

    @PostMapping("/criar/lanche")
    public ResponseEntity<LancheOutDTO> createLanche(@RequestBody @Valid LancheInDTO lancheInDTO) {
        LancheOutDTO lancheOutDTO = produtoService.createLanche(lancheInDTO);

        return new ResponseEntity<>(lancheOutDTO, HttpStatus.CREATED);
    }

    @GetMapping("/lanche/{idLanche}")
    public ResponseEntity<LancheOutDTO> findLancheById(@PathVariable @Positive Integer idLanche) throws RegraDeNegocioException {
        LancheOutDTO lancheOutDTO = produtoService.findLancheById(idLanche);

        return new ResponseEntity<>(lancheOutDTO, HttpStatus.OK);
    }

    @GetMapping("/listar/lanches")
    public ResponseEntity<List<LancheOutDTO>> findAllLanches() {
        List<LancheOutDTO> lancheOutDTOList = produtoService.findAllLanches();

        return new ResponseEntity<>(lancheOutDTOList, HttpStatus.OK);
    }

    @PutMapping("/lanche/{idLanche}")
    public ResponseEntity<LancheOutDTO> updateLanche(@PathVariable @Positive Integer idLanche, @RequestBody @Valid LancheInDTO lancheInDTO) throws RegraDeNegocioException {
        LancheOutDTO lancheOutDTO = produtoService.updateLancheById(idLanche, lancheInDTO);

        return new ResponseEntity<>(lancheOutDTO, HttpStatus.OK);
    }

    @DeleteMapping("/lanche/{idLanche}")
    public ResponseEntity<Void> deleteLancheById(@PathVariable @Positive Integer idLanche) {
        produtoService.deleteLancheById(idLanche);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/criar/bebida")
    public ResponseEntity<BebidaOutDTO> createBebida(@RequestBody @Valid BebidaInDTO bebidaInDTO) {
        return new ResponseEntity<>(produtoService.createBebida(bebidaInDTO), HttpStatus.CREATED);
    }

    @GetMapping("/listar/bebidas")
    public List<BebidaOutDTO> findAllBebidas() {
        return produtoService.findAllBebidas();
    }

    @GetMapping("/bebida/{idBebida}")
    public ResponseEntity<BebidaOutDTO> findBebidaById(@PathVariable ("idBebida") @Positive Integer idBebida) throws RegraDeNegocioException{
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
    public ResponseEntity<Void> deleteSobremesa(@PathVariable @Positive Integer idSobremesa) {
        produtoService.delete(idSobremesa);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/listar/lanches-ordenados-por-nome")
    public Page<LancheOutDTO> findLanchesOrdenadosPorNome(Integer numeroDePaginas, Integer quantidadeDeRegistrosPorPagina) {
        Pageable pageable = PageRequest.of(numeroDePaginas, quantidadeDeRegistrosPorPagina, Sort.by("nome"));

        return produtoService.findLanchesOrdenadosPorNome(pageable);
    }

    @GetMapping("/listar/bebidas-ordenadas-por-nome")
    public Page<BebidaOutDTO> findBebidasOrdenadasPorNome(Integer numeroDePaginas, Integer quantidadeDeRegistros) {
        Pageable pageable = PageRequest.of(numeroDePaginas, quantidadeDeRegistros, Sort.by("nome"));

        return produtoService.findBebidasOrdenadasPorNome(pageable);
    }

    @GetMapping("/listar/sobremesas-ordenadas-por-nome")
    public Page<SobremesaOutDTO> findSobremesasOrdenadasPorNome(Integer numeroDePaginas, Integer quantidadeDeRegistros) {
        Pageable pageable = PageRequest.of(numeroDePaginas, quantidadeDeRegistros, Sort.by("nome"));

        return produtoService.findSobremesasOrdenadasPorNome(pageable);
    }

    @GetMapping("/listar/lanches-ordenados-por-preco")
    public Page<LancheOutDTO> findLanchesOrdenadosPorPreco(Integer numeroDePaginas, Integer quantidadeDeRegistrosPorPagina) {
        Pageable pageable = PageRequest.of(numeroDePaginas, quantidadeDeRegistrosPorPagina, Sort.by("preco"));

        return produtoService.findLanchesOrdenadosPorPreco(pageable);
    }

    @GetMapping("/listar/bebidas-ordenadas-por-preco")
    public Page<BebidaOutDTO> findBebidasOrdenadasPorPreco(Integer numeroDePaginas, Integer quantidadeDeRegistros) {
        Pageable pageable = PageRequest.of(numeroDePaginas, quantidadeDeRegistros, Sort.by("preco"));

        return produtoService.findBebidasOrdenadasPorPreco(pageable);
    }

    @GetMapping("/listar/sobremesas-ordenadas-por-preco")
    public Page<SobremesaOutDTO> findSobremesasOrdenadasPorPreco(Integer numeroDePaginas, Integer quantidadeDeRegistros) {
        Pageable pageable = PageRequest.of(numeroDePaginas, quantidadeDeRegistros, Sort.by("preco"));

        return produtoService.findSobremesasOrdenadasPorPreco(pageable);
    }

}
