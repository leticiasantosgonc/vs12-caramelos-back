package br.com.dbc.vemser.checkout.service;

import br.com.dbc.vemser.checkout.dtos.BebidaInDTO;
import br.com.dbc.vemser.checkout.dtos.BebidaOutDTO;
import br.com.dbc.vemser.checkout.entities.Produto;
import br.com.dbc.vemser.checkout.enums.TipoProduto;
import br.com.dbc.vemser.checkout.repository.ProdutoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProdutoService {

    private final ProdutoRepository produtoRepository;
    private final ObjectMapper objectMapper;

    public List<Produto> findAll() {
        return produtoRepository.findAll();
    }

    //CRUD BEBIDA
    public BebidaOutDTO createBebida(BebidaInDTO bebidaInDTO){
        Produto produto = objectMapper.convertValue(bebidaInDTO, Produto.class);
        produto.setTipoProduto(TipoProduto.BEBIDA);
        Produto novoProduto = produtoRepository.save(produto);

        BebidaOutDTO bebidaOutDTO = objectMapper.convertValue(novoProduto, BebidaOutDTO.class);
        return bebidaOutDTO;
    }

    public List<Produto> findAllBebidas() {
        List<Produto> produtos = produtoRepository.findAll();

        List<Produto> bebidas = produtos.stream()
                .filter(produto -> "BEBIDA".equalsIgnoreCase(String.valueOf(produto.getTipoProduto())))
                .collect(Collectors.toList());

        return bebidas;
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


}
