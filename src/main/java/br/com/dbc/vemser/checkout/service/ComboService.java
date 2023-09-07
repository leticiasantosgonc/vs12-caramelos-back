package br.com.dbc.vemser.checkout.service;

import br.com.dbc.vemser.checkout.dtos.*;
import br.com.dbc.vemser.checkout.entities.Combo;
import br.com.dbc.vemser.checkout.entities.Produto;
import br.com.dbc.vemser.checkout.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.checkout.repository.ComboRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ComboService {

//    private final ComboRepository comboRepository;
//    private final ProdutoService produtoService;
//    private final ObjectMapper objectMapper;
//
//    public ComboOutDTO createCombo(ComboInDTO comboInDTO) throws RegraDeNegocioException {
//        List<LancheOutDTO> lanches = new ArrayList<>();
//        List<BebidaOutDTO> bebidas = new ArrayList<>();
//
//        Combo combo = new Combo();
//        combo.setNome(comboInDTO.getNome());
//        combo.setDescricao(comboInDTO.getDescricao());
//        combo.setImagem(comboInDTO.getImagem());
//        combo.setQuantidade(comboInDTO.getQuantidade());
//
//        List<Produto> produtosLanches = new ArrayList<>();
//        List<Produto> produtosBebidas = new ArrayList<>();
//
//        for (Integer id : comboInDTO.getIndexesLanches()) {
//            LancheOutDTO lancheOutDTO = produtoService.findLancheById(id);
//            lanches.add(objectMapper.convertValue(lancheOutDTO, LancheOutDTO.class));
//            Produto produto = objectMapper.convertValue(lancheOutDTO, Produto.class);
//            produtosLanches.add(produto);
//        }
//
//        for (Integer id : comboInDTO.getIndexesBebidas()) {
//            BebidaOutDTO bebidaOutDTO = produtoService.findBebidaById(id);
//            bebidas.add(objectMapper.convertValue(bebidaOutDTO, BebidaOutDTO.class));
//            Produto produto = objectMapper.convertValue(bebidaOutDTO, Produto.class);
//            produtosBebidas.add(produto);
//        }
//
//        combo.setLanches(produtosLanches);
//        combo.setBebidas(produtosBebidas);
//        comboRepository.save(combo);
//
//        ComboOutDTO comboOutDTO = new ComboOutDTO();
//        comboOutDTO.setNome(combo.getNome());
//        comboOutDTO.setDescricao(combo.getDescricao());
//
//        // todo: refatorar
//        List<ComboProdutoOutDTO> lanchesProdutoOutDTOList = lanches.stream().map(lanche -> {
//            return objectMapper.convertValue(lanche, ComboProdutoOutDTO.class);
//        }).toList();
//
//        // todo: refatorar
//        List<ComboProdutoOutDTO> bebidasProdutoOutDTOList = bebidas.stream().map(bebida -> {
//            return objectMapper.convertValue(bebida, ComboProdutoOutDTO.class);
//        }).toList();
//
//        comboOutDTO.setLanches(lanchesProdutoOutDTOList);
//        comboOutDTO.setBebidas(bebidasProdutoOutDTOList);
//        comboOutDTO.setQuantidadeDisponivel(combo.getQuantidade());
//
//        return comboOutDTO;
//    }
//
//    public List<ComboOutDTO> findAllCombos() {
//        List<Combo> combosEncontados = comboRepository.findAll();
//        List<ComboOutDTO> comboOutDTOList = new ArrayList<>();
//
//        for (Combo combo : combosEncontados) {
//            comboOutDTOList.add(objectMapper.convertValue(combo, ComboOutDTO.class));
//        }
//
//        return comboOutDTOList;
//    }
//
//    public ComboOutDTO findComboById(Integer idCombo) throws RegraDeNegocioException {
//        Combo comboEncontrado = comboRepository
//                .findById(idCombo)
//                .orElseThrow(() -> new RegraDeNegocioException("Combo não encontrado"));
//
//        return objectMapper.convertValue(comboEncontrado, ComboOutDTO.class);
//    }

}
