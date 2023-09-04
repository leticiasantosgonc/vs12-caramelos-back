package br.com.dbc.vemser.checkout.service;

import br.com.dbc.vemser.checkout.entities.Produto;
import br.com.dbc.vemser.checkout.repository.ProdutoRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    public List<Produto> findAll() {
        return produtoRepository.findAll();
    }

}
