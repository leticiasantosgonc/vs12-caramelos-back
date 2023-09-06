package br.com.dbc.vemser.checkout.service;


import br.com.dbc.vemser.checkout.dtos.*;

import br.com.dbc.vemser.checkout.entities.Combo;
import br.com.dbc.vemser.checkout.entities.Produto;
import br.com.dbc.vemser.checkout.enums.TipoProduto;
import br.com.dbc.vemser.checkout.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.checkout.repository.ProdutoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.lang.module.ResolutionException;
import javax.sql.rowset.serial.SerialClob;
import java.sql.Clob;
import java.util.ArrayList;
import java.util.Base64;
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
            produtoRetornado.setImagem(bebidaEntrada.getImagem());
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
        //produtoParaPersistir.setImagem(stringToClob(lancheInDTO.getImagem()));
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

        if (produtoEncontrado.getTipoProduto().equals(TipoProduto.LANCHE)) {
            return objectMapper.convertValue(produtoEncontrado, LancheOutDTO.class);
        } else {
            throw new RegraDeNegocioException("O produto não é um lanche");
        }
    }

    public LancheOutDTO updateLancheById(Integer idLanche, LancheInDTO lancheInDTO) throws RegraDeNegocioException {
        Produto produtoEncontrado = produtoRepository
                .findById(idLanche)
                .orElseThrow(() -> new RegraDeNegocioException("Lanche com id " + idLanche + " não encontrado"));

        if (produtoEncontrado.getTipoProduto().equals(TipoProduto.LANCHE)) {
            Produto produtoParaPersistir = new Produto();
            produtoParaPersistir.setTipoProduto(TipoProduto.LANCHE);
            produtoParaPersistir.setIdProduto(produtoEncontrado.getIdProduto());
            produtoParaPersistir.setNome(lancheInDTO.getNome());
            produtoParaPersistir.setDescricao(lancheInDTO.getDescricao());
            produtoParaPersistir.setImagem(lancheInDTO.getImagem());
            produtoParaPersistir.setQuantidade(lancheInDTO.getQuantidade());
            produtoParaPersistir.setTamanhoProduto(lancheInDTO.getTamanhoProduto());
            produtoParaPersistir.setDietaProduto(lancheInDTO.getDietaProduto());
            produtoParaPersistir.setPreco(lancheInDTO.getPreco());
            Produto produtoPersistido = produtoRepository.save(produtoParaPersistir);

            return objectMapper.convertValue(produtoPersistido, LancheOutDTO.class);
        } else {
            throw new RegraDeNegocioException("Ação não permitida");
        }
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
    public SobremesaOutDTO saveSobremesa(SobremesaInDTO sobremesa) {
        Produto produto = objectMapper.convertValue(sobremesa, Produto.class);
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
        produtoAtualizado.setImagem(sobremesaAtualizada.getImagem());
        produtoAtualizado.setTipoProduto(produto.getTipoProduto());

        SobremesaOutDTO sobremesaOutDTO = objectMapper.convertValue(produtoRepository.save(produtoAtualizado),SobremesaOutDTO.class);

        return sobremesaOutDTO;
    }

    public void delete(Integer idProduto){
        Optional<Produto> produtoAchado = produtoRepository.findById(idProduto);
        Produto produtoConvertido = objectMapper.convertValue(produtoAchado,Produto.class);

        if (produtoConvertido.getTipoProduto()==TipoProduto.SOBREMESA) {
            produtoRepository.delete(produtoConvertido);
        }
    }

    public List<SobremesaOutDTO> findAllSobremesas() {
        return produtoRepository.findByTipoProduto(TipoProduto.SOBREMESA)
                .stream()
                .map(produto -> objectMapper.convertValue(produto,SobremesaOutDTO.class))
                .collect(Collectors.toList());
    }

    public Produto findById(Integer idProduto) throws RegraDeNegocioException {
        return produtoRepository.findById(idProduto).orElseThrow(() -> new RegraDeNegocioException("Produto não encontrado"));
    }

    public boolean isSobremesa(Produto produto) throws RegraDeNegocioException {
        if(produto.getTipoProduto() != TipoProduto.SOBREMESA){
            throw new RegraDeNegocioException("Ação não permitida");
        }
        return true;
    }

    public Page<LancheOutDTO> findLanchesOrdenadosPorNome(Pageable pageable) {
        List<LancheOutDTO> lancheOutDTOList = produtoRepository
                .findAll(Sort.by("nome"))
                .stream()
                .filter(produto -> produto.getTipoProduto().equals(TipoProduto.LANCHE))
                .map(this::converterProdutoParaLancheOutDTO)
                .toList();
        int quantidade = lancheOutDTOList.size();

        return new PageImpl<>(lancheOutDTOList, pageable, quantidade);
    }

    public Page<BebidaOutDTO> findBebidasOrdenadasPorNome(Pageable pageable) {
        List<BebidaOutDTO> bebidaOutDTOList = produtoRepository
                .findAll(Sort.by("nome"))
                .stream()
                .filter(produto -> produto.getTipoProduto().equals(TipoProduto.BEBIDA))
                .map(this::converterProdutoParaBebidaOutDTO)
                .toList();
        int quantidade = bebidaOutDTOList.size();

        return new PageImpl<>(bebidaOutDTOList, pageable, quantidade);
    }

    public Page<SobremesaOutDTO> findSobremesasOrdenadasPorNome(Pageable pageable) {
        List<SobremesaOutDTO> sobremesaOutDTOList = produtoRepository
                .findAll(Sort.by("nome"))
                .stream()
                .filter(produto -> produto.getTipoProduto().equals(TipoProduto.BEBIDA))
                .map(this::converterProdutoParaSobremesaOutDTO)
                .toList();
        int quantidade = sobremesaOutDTOList.size();

        return new PageImpl<>(sobremesaOutDTOList, pageable, quantidade);
    }

    public Page<LancheOutDTO> findLanchesOrdenadosPorPreco(Pageable pageable) {
        List<LancheOutDTO> lancheOutDTOList = produtoRepository
                .findAll(Sort.by("preco"))
                .stream()
                .filter(produto -> produto.getTipoProduto().equals(TipoProduto.LANCHE))
                .map(this::converterProdutoParaLancheOutDTO)
                .toList();
        int quantidade = lancheOutDTOList.size();

        return new PageImpl<>(lancheOutDTOList, pageable, quantidade);
    }

    public Page<BebidaOutDTO> findBebidasOrdenadasPorPreco(Pageable pageable) {
        List<BebidaOutDTO> bebidaOutDTOList = produtoRepository
                .findAll(Sort.by("preco"))
                .stream()
                .filter(produto -> produto.getTipoProduto().equals(TipoProduto.BEBIDA))
                .map(this::converterProdutoParaBebidaOutDTO)
                .toList();
        int quantidade = bebidaOutDTOList.size();

        return new PageImpl<>(bebidaOutDTOList, pageable, quantidade);
    }

    public Page<SobremesaOutDTO> findSobremesasOrdenadasPorPreco(Pageable pageable) {
        List<SobremesaOutDTO> sobremesaOutDTOList = produtoRepository
                .findAll(Sort.by("preco"))
                .stream()
                .filter(produto -> produto.getTipoProduto().equals(TipoProduto.SOBREMESA))
                .map(this::converterProdutoParaSobremesaOutDTO)
                .toList();
        int quantidade = sobremesaOutDTOList.size();

        return new PageImpl<>(sobremesaOutDTOList, pageable, quantidade);
    }

    public LancheOutDTO converterProdutoParaLancheOutDTO(Produto produto) {
        return objectMapper.convertValue(produto, LancheOutDTO.class);
    }

    public BebidaOutDTO converterProdutoParaBebidaOutDTO(Produto produto) {
        return objectMapper.convertValue(produto, BebidaOutDTO.class);
    }

    public SobremesaOutDTO converterProdutoParaSobremesaOutDTO(Produto produto) {
        return objectMapper.convertValue(produto, SobremesaOutDTO.class);
    }

    public static Clob stringToClob(String str) {
        if (null == str) {
            return null;
        } else {
            try {
                return new SerialClob(str.toCharArray());
            } catch (Exception e) {
                return null;
            }
        }
    }

}
