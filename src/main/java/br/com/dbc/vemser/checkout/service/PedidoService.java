package br.com.dbc.vemser.checkout.service;

import br.com.dbc.vemser.checkout.dtos.*;
import br.com.dbc.vemser.checkout.entities.Pedido;
import br.com.dbc.vemser.checkout.entities.Produto;
import br.com.dbc.vemser.checkout.enums.StatusPedido;
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

        BigDecimal valorTotal = BigDecimal.ZERO;

        for (Produto produto : produtos) {
            valorTotal = valorTotal.add(produto.getPreco());
        }

        Pedido pedido = new Pedido();
        pedido.setCpf(pedidoInDTO.getCpf());
        pedido.setObservacao(pedidoInDTO.getObservacao());
        pedido.setItens(produtos);
        pedido.setStatus(StatusPedido.NAO_PAGO);
        pedido.setDataPedido(LocalDate.now());
        pedido.setQuantidade(produtos.size());
        pedido.setPreco(valorTotal);

        return pedidoRepository.save(pedido);
    }

    public List<Pedido> findAllPedidos() {
        return pedidoRepository.findAll();
    }

    public RelatorioPedido relatorioItemPedido() throws RegraDeNegocioException{
        Pedido pedidoAchado = pedidoRepository.findById(43).orElseThrow(()-> new RegraDeNegocioException("Pedido não existe"));

        RelatorioPedido relatorio = new RelatorioPedido();
        relatorio.setIdPedido(pedidoAchado.getIdPedido());
        relatorio.setValorTotal(pedidoAchado.getPreco());
        relatorio.setDataPedido(pedidoAchado.getDataPedido());
        relatorio.setStatus(pedidoAchado.getStatus());
        relatorio.setItens(pedidoRepository.findAllByIdPedido(43));
        relatorio.setDataRelatorio(LocalDate.now());

        return relatorio;
    }

}
