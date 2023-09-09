package br.com.dbc.vemser.checkout.service;

import br.com.dbc.vemser.checkout.dtos.CheckoutItemDTO;
import br.com.dbc.vemser.checkout.dtos.ItemInDTO;
import br.com.dbc.vemser.checkout.dtos.PedidoInDTO;
import br.com.dbc.vemser.checkout.entities.Pedido;
import br.com.dbc.vemser.checkout.entities.Produto;
import br.com.dbc.vemser.checkout.enums.Game;
import br.com.dbc.vemser.checkout.exceptions.RegraDeNegocioException;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class PagamentoService {

    private final ProdutoService produtoService;
    private final PedidoService pedidoService;

    @Value("${stripe.api.secretkey}")
    private String secretKey = "";

    SessionCreateParams.LineItem.PriceData createPriceData(CheckoutItemDTO checkoutItemDto) {
        return SessionCreateParams.LineItem.PriceData.builder()
                .setCurrency("BRL")
                .setUnitAmount((long)(checkoutItemDto.getPrice() * 100))
                .setProductData(
                        SessionCreateParams.LineItem.PriceData.ProductData.builder()
                                .setName(checkoutItemDto.getProductName())
                                .build())
                .build();
    }

    public Session criarSessionCheckout (PedidoInDTO pedidoInDTO, Integer idPedido) throws StripeException, RegraDeNegocioException {
        Stripe.apiKey = secretKey;
        SessionCreateParams params = SessionCreateParams.builder()
                            .setMode(SessionCreateParams.Mode.PAYMENT)
                            .setSuccessUrl("https://google.com/")
                            .setCancelUrl("https://google.com")
                            .addAllLineItem(criarItensParaPagamento(pedidoInDTO, idPedido))
                            .build();
        return Session.create(params);
    }

    private List<SessionCreateParams.LineItem> criarItensParaPagamento(PedidoInDTO pedidoInDTO, Integer idPedido) throws RegraDeNegocioException {
        List<SessionCreateParams.LineItem> itensParaPagamento = new ArrayList<>();
        List<ItemInDTO> itensParaRequisicao = pedidoInDTO.getItens();

        if (pedidoInDTO.getGame().equals(Game.LOSE)) {
            for (ItemInDTO item : itensParaRequisicao) {
                Produto produto = produtoService.findById(item.getIdProduto());
                SessionCreateParams.LineItem produtoEspecifico = SessionCreateParams.LineItem.builder()
                        .setQuantity((long) item.getQuantidadeProduto())
                        .setPriceData(createPriceData(new CheckoutItemDTO(
                                produto.getNome(),
                                item.getQuantidadeProduto(),
                                produto.getPreco().doubleValue(),
                                produto.getIdProduto(),
                                1
                        )))
                        .build();
                itensParaPagamento.add(produtoEspecifico);
            }
        } else {
            for (ItemInDTO item : itensParaRequisicao) {
                Produto produto = produtoService.findById(item.getIdProduto());
                SessionCreateParams.LineItem produtoEspecifico = SessionCreateParams.LineItem.builder()
                        .setQuantity((long) item.getQuantidadeProduto())
                        .setPriceData(createPriceData(new CheckoutItemDTO(
                                produto.getNome(),
                                item.getQuantidadeProduto(),
                                produto.getPreco().doubleValue()*0.90,
                                produto.getIdProduto(),
                                1
                        )))
                        .build();
                itensParaPagamento.add(produtoEspecifico);
            }
        }

        return itensParaPagamento;
    }

}
