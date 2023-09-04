package br.com.dbc.vemser.checkout.service;

import br.com.dbc.vemser.checkout.dtos.LancheInDTO;
import br.com.dbc.vemser.checkout.dtos.LancheOutDTO;
import br.com.dbc.vemser.checkout.entities.Produto;
import br.com.dbc.vemser.checkout.enums.TipoProduto;
import br.com.dbc.vemser.checkout.repository.ProdutoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProdutoService {

    private final ProdutoRepository produtoRepository;
    private final ObjectMapper objectMapper;

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
