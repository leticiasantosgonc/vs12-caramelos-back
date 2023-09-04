package br.com.dbc.vemser.checkout.service;


import br.com.dbc.vemser.checkout.dtos.BebidaInDTO;
import br.com.dbc.vemser.checkout.dtos.BebidaOutDTO;

import br.com.dbc.vemser.checkout.dtos.LancheInDTO;
import br.com.dbc.vemser.checkout.dtos.LancheOutDTO;
import br.com.dbc.vemser.checkout.entities.Produto;
import br.com.dbc.vemser.checkout.enums.TipoProduto;
import br.com.dbc.vemser.checkout.repository.ProdutoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProdutoService {

    private final ProdutoRepository produtoRepository;
    private final ObjectMapper objectMapper;


    //CRUD BEBIDA
    public BebidaOutDTO createBebida(BebidaInDTO bebidaInDTO){
        Produto produto = objectMapper.convertValue(bebidaInDTO, Produto.class);
        produto.setTipoProduto(TipoProduto.BEBIDA);
        Produto novoProduto = produtoRepository.save(produto);

        BebidaOutDTO bebidaOutDTO = objectMapper.convertValue(novoProduto, BebidaOutDTO.class);
        return bebidaOutDTO;
    }

    public List<BebidaOutDTO> findAllBebidas() {
        return produtoRepository.findByTipoProduto(TipoProduto.BEBIDA)
                .stream()
                .map(produto -> objectMapper.convertValue(produto,BebidaOutDTO.class))
                .collect(Collectors.toList());
    }

    public BebidaOutDTO findBebidaById(Integer idBebida) throws Exception{
        Produto produtoRetornado = produtoRepository.findById(idBebida)
                .orElseThrow(() -> new Exception("Bebida não encontrada"));

        if(produtoRetornado.getTipoProduto().equals(TipoProduto.BEBIDA)){
            BebidaOutDTO bebidaOutDTO = objectMapper.convertValue(produtoRetornado, BebidaOutDTO.class);
            return bebidaOutDTO;
        } else {
            throw new Exception("O produto não é uma bebida");
        }
    }

    public BebidaOutDTO updateBebida(Integer idBebida, BebidaOutDTO bebidaEntrada) throws Exception{
        Produto produtoRetornado = produtoRepository.findById(idBebida)
                .orElseThrow(() -> new Exception("Bebida não encontrada"));

        if(produtoRetornado.getTipoProduto().equals(TipoProduto.BEBIDA)){
            produtoRetornado.setNome(bebidaEntrada.getNome());
            produtoRetornado.setDescricao(bebidaEntrada.getDescricao());
            produtoRetornado.setQuantidade(bebidaEntrada.getQuantidade());
            produtoRetornado.setPreco(bebidaEntrada.getPreco());
            produtoRetornado.setTipoProduto(TipoProduto.BEBIDA);
            produtoRetornado.setMarca(bebidaEntrada.getMarca());
            produtoRetornado.setIdProduto(idBebida);

            Produto produtoAtualizado = produtoRepository.save(produtoRetornado);
            BebidaOutDTO bebidaOutDTO = objectMapper.convertValue(produtoAtualizado, BebidaOutDTO.class);

            return bebidaOutDTO;

        } else {
            throw new Exception("O produto não é uma bebida");
        }
    }

    public void deleteBebidaById(Integer idBebida) throws Exception{
        Produto produtoRetornado = produtoRepository.findById(idBebida)
                .orElseThrow(() -> new Exception("Bebida não encontrada"));

        if (produtoRetornado.getTipoProduto().equals(TipoProduto.BEBIDA)){
            produtoRepository.deleteById(idBebida);
        }else {
            throw new Exception("O produto não é uma bebida");
        }
    }

    public LancheOutDTO createLanche(LancheInDTO lancheInDTO) {
        Produto produtoParaPersistir = objectMapper.convertValue(lancheInDTO, Produto.class);
        produtoParaPersistir.setTipoProduto(TipoProduto.LANCHE);
        Produto produtoPersistido = produtoRepository.save(produtoParaPersistir);

        return objectMapper.convertValue(produtoPersistido, LancheOutDTO.class);
    }

    public List<LancheOutDTO> findAllLanches() {
        List<Produto> produtosEncontrados = produtoRepository.findAll();
        List<LancheOutDTO> lancheOutDTOList = new ArrayList<>();

        for (Produto produto : produtosEncontrados) {
            if (produto.getTipoProduto().equals(TipoProduto.LANCHE)) {
                lancheOutDTOList.add(objectMapper.convertValue(produto, LancheOutDTO.class));
            }
        }

        return lancheOutDTOList;
    }

    public LancheOutDTO findLancheById(Integer idLanche) throws Exception {
        Produto produtoEncontrado = produtoRepository
                .findById(idLanche)
                .orElseThrow(() -> new Exception("Lanche com id " + idLanche + " não encontrado"));

        return objectMapper.convertValue(produtoEncontrado, LancheOutDTO.class);
    }

    public LancheOutDTO updateLancheById(Integer idLanche, LancheInDTO lancheInDTO) throws Exception {
        Produto produtoEncontrado = produtoRepository
                .findById(idLanche)
                .orElseThrow(() -> new Exception("Lanche com id " + idLanche + " não encontrado"));

        Produto produtoParaPersistir = new Produto();
        produtoParaPersistir.setTipoProduto(TipoProduto.LANCHE);
        produtoParaPersistir.setIdProduto(produtoEncontrado.getIdProduto());
        produtoParaPersistir.setNome(lancheInDTO.getNome());
        produtoParaPersistir.setDescricao(lancheInDTO.getDescricao());
        produtoParaPersistir.setQuantidade(lancheInDTO.getQuantidade());
        produtoParaPersistir.setTamanhoProduto(lancheInDTO.getTamanhoProduto());
        produtoParaPersistir.setDietaProduto(lancheInDTO.getDietaProduto());
        produtoParaPersistir.setPreco(lancheInDTO.getPreco());

        Produto produtoPersistido = produtoRepository.save(produtoParaPersistir);

        return objectMapper.convertValue(produtoPersistido, LancheOutDTO.class);
    }

    public void deleteLancheById(Integer idLanche) throws Exception {
        Produto produto = produtoRepository
                .findById(idLanche)
                .orElseThrow(() -> new Exception("Lanche com id " + idLanche + " não encontrado"));

        if (produto.getTipoProduto().equals(TipoProduto.LANCHE)) {
            produtoRepository.deleteById(idLanche);
        }
    }

    public List<Produto> findAll() {
        return produtoRepository.findAll();
    }

}
