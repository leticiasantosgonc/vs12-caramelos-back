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

import javax.validation.constraints.Positive;


@RestController
@RequestMapping("/produto")
@RequiredArgsConstructor
@Validated
public class ProdutoController implements ProdutoControllerDoc {

    private final ProdutoService produtoService;

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
}
