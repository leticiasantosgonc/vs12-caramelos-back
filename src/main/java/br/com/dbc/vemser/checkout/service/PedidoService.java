package br.com.dbc.vemser.checkout.service;

import br.com.dbc.vemser.checkout.dtos.*;
import br.com.dbc.vemser.checkout.entities.Combo;
import br.com.dbc.vemser.checkout.entities.Pedido;
import br.com.dbc.vemser.checkout.entities.Produto;
import br.com.dbc.vemser.checkout.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.checkout.repository.PedidoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final ProdutoService produtoService;
    private final ObjectMapper objectMapper;

    public Pedido createPedido(PedidoInDTO pedidoInDTO) throws RegraDeNegocioException {
        List<Produto> produtos = new ArrayList<>();
        for (ItemInDTO item : pedidoInDTO.getItens()) {
            Produto produto = produtoService.findById(item.getIdProduto());
            if (produto.getQuantidade() < item.getQuantidadeProduto()) {
                throw new RegraDeNegocioException("Quantidade indisponível");
            }
            if (!produto.getTipoProduto().equals(item.getTipoProduto())) {
                throw new RegraDeNegocioException("Tipo diferente");
            }
            for (int i = 0; i < item.getQuantidadeProduto(); i++) {
                produtos.add(produto);
            }
        }

        BigDecimal teste = BigDecimal.ZERO;

        for (Produto produto : produtos) {
            teste = teste.add(produto.getPreco());
        }

        Pedido pedido = new Pedido();
        pedido.setItens(produtos);
        pedido.setQuantidade(produtos.size());
        pedido.setDataPedido(LocalDate.now());
        pedido.setCpf(pedidoInDTO.getCpf());
        pedido.setObservacao(pedidoInDTO.getObservacao());
        pedido.setPreco(teste);
        return pedidoRepository.save(pedido);
    }

}
