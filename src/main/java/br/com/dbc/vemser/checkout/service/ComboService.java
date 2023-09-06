package br.com.dbc.vemser.checkout.service;

import br.com.dbc.vemser.checkout.dtos.BebidaOutDTO;
import br.com.dbc.vemser.checkout.dtos.ComboInDTO;
import br.com.dbc.vemser.checkout.dtos.ComboOutDTO;
import br.com.dbc.vemser.checkout.dtos.LancheOutDTO;
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

    private final ComboRepository comboRepository;
    private final ProdutoService produtoService;
    private final ObjectMapper objectMapper;

    public ComboOutDTO createCombo(ComboInDTO comboInDTO) throws RegraDeNegocioException {
        List<LancheOutDTO> lanches = new ArrayList<>();
        List<BebidaOutDTO> bebidas = new ArrayList<>();

        Combo combo = new Combo();
        combo.setNome(comboInDTO.getNome());
        combo.setDescricao(comboInDTO.getDescricao());
        combo.setImagem(comboInDTO.getImagem());

        List<Produto> produtosLanches = new ArrayList<>();
        List<Produto> produtosBebidas = new ArrayList<>();

        for (Integer id : comboInDTO.getIndexesLanches()) {
            LancheOutDTO lancheOutDTO = produtoService.findLancheById(id);
            Produto produto = objectMapper.convertValue(lancheOutDTO, Produto.class);
            produtosLanches.add(produto);
        }

        for (Integer id : comboInDTO.getIndexesBebidas()) {
            BebidaOutDTO bebidaOutDTO = produtoService.findBebidaById(id);
            Produto produto = objectMapper.convertValue(bebidaOutDTO, Produto.class);
            produtosBebidas.add(produto);
        }

        combo.setLanches(produtosLanches);
        combo.setBebidas(produtosBebidas);
        comboRepository.save(combo);

        ComboOutDTO comboOutDTO = new ComboOutDTO();
        comboOutDTO.setLanches(lanches);
        comboOutDTO.setBebidas(bebidas);
        comboOutDTO.setQuantidadeDisponivel(10);

        return comboOutDTO;
    }

    public List<ComboOutDTO> findAllCombos() {
        List<Combo> combosEncontados = comboRepository.findAll();
        List<ComboOutDTO> comboOutDTOList = new ArrayList<>();

        for (Combo combo : combosEncontados) {
            comboOutDTOList.add(objectMapper.convertValue(combo, ComboOutDTO.class));
        }

        return comboOutDTOList;
    }

    public ComboOutDTO findComboById(Integer idCombo) throws RegraDeNegocioException {
        Combo comboEncontrado = comboRepository
                .findById(idCombo)
                .orElseThrow(() -> new RegraDeNegocioException("Combo não encontrado"));

        return objectMapper.convertValue(comboEncontrado, ComboOutDTO.class);
    }

}
