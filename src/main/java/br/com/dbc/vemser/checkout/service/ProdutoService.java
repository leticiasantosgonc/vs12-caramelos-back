package br.com.dbc.vemser.checkout.service;


import br.com.dbc.vemser.checkout.dtos.*;

import br.com.dbc.vemser.checkout.entities.Produto;
import br.com.dbc.vemser.checkout.enums.TipoProduto;
import br.com.dbc.vemser.checkout.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.checkout.repository.ProdutoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.lang.module.ResolutionException;
import java.util.ArrayList;
import java.util.List;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProdutoService {

    private final ProdutoRepository produtoRepository;
    private final ObjectMapper objectMapper;

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

    public BebidaOutDTO findBebidaById(Integer idBebida) throws RegraDeNegocioException {
        Produto produtoRetornado = produtoRepository.findById(idBebida)
                .orElseThrow(() -> new RegraDeNegocioException ("Bebida não encontrada"));

        if(produtoRetornado.getTipoProduto().equals(TipoProduto.BEBIDA)){
            BebidaOutDTO bebidaOutDTO = objectMapper.convertValue(produtoRetornado, BebidaOutDTO.class);
            return bebidaOutDTO;
        } else {
            throw new RegraDeNegocioException("O produto não é uma bebida");
        }

    }

    public BebidaOutDTO updateBebida(Integer idBebida, BebidaOutDTO bebidaEntrada) throws RegraDeNegocioException{
        Produto produtoRetornado = produtoRepository.findById(idBebida)
                .orElseThrow(() -> new RegraDeNegocioException("Bebida não encontrada"));

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
            throw new RegraDeNegocioException("O produto não é uma bebida");
        }
    }

    public void deleteBebidaById(Integer idBebida){
        Produto produtoRetornado = produtoRepository.findById(idBebida).get();

        if (produtoRetornado.getTipoProduto().equals(TipoProduto.BEBIDA)){
            produtoRepository.deleteById(idBebida);
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

    public LancheOutDTO findLancheById(Integer idLanche) throws RegraDeNegocioException {
        Produto produtoEncontrado = produtoRepository
                .findById(idLanche)
                .orElseThrow(() -> new RegraDeNegocioException("Lanche com id " + idLanche + " não encontrado"));

        return objectMapper.convertValue(produtoEncontrado, LancheOutDTO.class);
    }

    public LancheOutDTO updateLancheById(Integer idLanche, LancheInDTO lancheInDTO) throws RegraDeNegocioException {
        Produto produtoEncontrado = produtoRepository
                .findById(idLanche)
                .orElseThrow(() -> new RegraDeNegocioException("Lanche com id " + idLanche + " não encontrado"));

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

    public void deleteLancheById(Integer idLanche){
        Produto produto = produtoRepository.findById(idLanche).get();

        if (produto.getTipoProduto().equals(TipoProduto.LANCHE)) {
            produtoRepository.deleteById(idLanche);
        }
    }

    public List<Produto> findAll() {
        return produtoRepository.findAll();
    }
    public SobremesaOutDTO saveSobremesa(SobremesaInDTO sobremesa){
        Produto produto = objectMapper.convertValue(sobremesa, Produto.class);
        produto.setImagem("teste");
        produto.setTipoProduto(TipoProduto.SOBREMESA);
        SobremesaOutDTO sobremesaOutDTO = objectMapper.convertValue(produtoRepository.save(produto),SobremesaOutDTO.class);
        return sobremesaOutDTO;
    }

    public SobremesaOutDTO findSobremesaByid(Integer idProduto) throws RegraDeNegocioException{
        Produto produtoRetornado = findById(idProduto);
        isSobremesa(produtoRetornado);

        return objectMapper.convertValue(produtoRetornado,SobremesaOutDTO.class);

    }

    public SobremesaOutDTO updateSobremesa(SobremesaInDTO sobremesaAtualizada, Integer idSobremesa)throws RegraDeNegocioException{
        Produto produto = findById(idSobremesa);
        isSobremesa(produto);
        Produto produtoAtualizado = objectMapper.convertValue(sobremesaAtualizada,Produto.class);
        produtoAtualizado.setIdProduto(produto.getIdProduto());
        produtoAtualizado.setTipoProduto(produto.getTipoProduto());

        SobremesaOutDTO sobremesaOutDTO = objectMapper.convertValue(produtoRepository.save(produtoAtualizado),SobremesaOutDTO.class);

        return sobremesaOutDTO;
    }

    public void delete(Integer idProduto){
        Optional<Produto> produtoAchado = produtoRepository.findById(idProduto);
        Produto produtoConvertido = objectMapper.convertValue(produtoAchado,Produto.class);

        if(produtoConvertido.getTipoProduto()==TipoProduto.SOBREMESA){
            produtoRepository.delete(produtoConvertido);
        }
    }

    public List<SobremesaOutDTO> findAllByTipo(){
        return produtoRepository.findByTipoProduto(TipoProduto.SOBREMESA)
                .stream()
                .map(produto -> objectMapper.convertValue(produto,SobremesaOutDTO.class))
                .collect(Collectors.toList());
    }

    public Produto findById(Integer idProduto) throws RegraDeNegocioException{
        return produtoRepository.findById(idProduto).orElseThrow(() -> new RegraDeNegocioException("Produto não encontrado"));
    }

    public boolean isSobremesa(Produto produto) throws RegraDeNegocioException{
        if(produto.getTipoProduto() != TipoProduto.SOBREMESA){
            throw new RegraDeNegocioException("Ação não permitida");
        }
        return true;
    }

}
