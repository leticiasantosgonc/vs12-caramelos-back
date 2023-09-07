package br.com.dbc.vemser.checkout.controller;

import br.com.dbc.vemser.checkout.docs.ProdutoControllerDoc;
import br.com.dbc.vemser.checkout.dtos.*;
import br.com.dbc.vemser.checkout.entities.Produto;
import br.com.dbc.vemser.checkout.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.checkout.service.PedidoService;
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
    private final PedidoService pedidoService;

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
    public Page<LancheOutDTO> findLanchesOrdenadosPorNome(@RequestParam(defaultValue= "0") Integer numeroDePaginas, @RequestParam(defaultValue= "10")  Integer quantidadeDeRegistrosPorPagina) {
        Pageable pageable = PageRequest.of(numeroDePaginas, quantidadeDeRegistrosPorPagina, Sort.by("nome"));

        return produtoService.findLanchesOrdenadosPorNome(pageable);
    }

    @GetMapping("/listar/bebidas-ordenadas-por-nome")
    public Page<BebidaOutDTO> findBebidasOrdenadasPorNome(@RequestParam(defaultValue= "0") Integer numeroDePaginas, @RequestParam(defaultValue= "10") Integer quantidadeDeRegistros) {
        Pageable pageable = PageRequest.of(numeroDePaginas, quantidadeDeRegistros, Sort.by("nome"));

        return produtoService.findBebidasOrdenadasPorNome(pageable);
    }

    @GetMapping("/listar/sobremesas-ordenadas-por-nome")
    public Page<SobremesaOutDTO> findSobremesasOrdenadasPorNome(@RequestParam(defaultValue= "0") Integer numeroDePaginas, @RequestParam(defaultValue= "10") Integer quantidadeDeRegistros) {
        Pageable pageable = PageRequest.of(numeroDePaginas, quantidadeDeRegistros, Sort.by("nome"));

        return produtoService.findSobremesasOrdenadasPorNome(pageable);
    }

    @GetMapping("/listar/lanches-ordenados-por-preco")
    public Page<LancheOutDTO> findLanchesOrdenadosPorPreco(@RequestParam(defaultValue= "0") Integer numeroDePaginas, @RequestParam(defaultValue= "10") Integer quantidadeDeRegistrosPorPagina) {
        Pageable pageable = PageRequest.of(numeroDePaginas, quantidadeDeRegistrosPorPagina, Sort.by("preco"));

        return produtoService.findLanchesOrdenadosPorPreco(pageable);
    }

    @GetMapping("/listar/bebidas-ordenadas-por-preco")
    public Page<BebidaOutDTO> findBebidasOrdenadasPorPreco(@RequestParam(defaultValue= "0") Integer numeroDePaginas, @RequestParam(defaultValue= "10") Integer quantidadeDeRegistros) {
        Pageable pageable = PageRequest.of(numeroDePaginas, quantidadeDeRegistros, Sort.by("preco"));

        return produtoService.findBebidasOrdenadasPorPreco(pageable);
    }

    @GetMapping("/listar/sobremesas-ordenadas-por-preco")
    public Page<SobremesaOutDTO> findSobremesasOrdenadasPorPreco(@RequestParam(defaultValue= "0") Integer numeroDePaginas, @RequestParam(defaultValue= "10") Integer quantidadeDeRegistros) {
        Pageable pageable = PageRequest.of(numeroDePaginas, quantidadeDeRegistros, Sort.by("preco"));

        return produtoService.findSobremesasOrdenadasPorPreco(pageable);
    }

    @PutMapping("/disponibilidade/{idProduto}")
    public ResponseEntity<Integer> getQuantidadeProdutoPorId(@PathVariable @Positive Integer idProduto) throws RegraDeNegocioException {
        return new ResponseEntity<>(produtoService.getQuantidadeProduto(idProduto), HttpStatus.OK);
    }

    @PutMapping("/atualizar-quantidade/{idProduto}")
    public ResponseEntity<Produto> updateQuantidadeProduto(@RequestBody @Positive Integer quantidade,
                                                           @PathVariable @Positive Integer idProduto) throws RegraDeNegocioException {
        return new ResponseEntity<>(produtoService.updateQuantidadeProduto(idProduto, quantidade), HttpStatus.OK);
    }

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
    public ResponseEntity<AcompanhamentoOutDTO> findAcompanhamentoById(@PathVariable ("idAcompanhamento") @Positive Integer idAcompanhamento) throws RegraDeNegocioException{
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
