package br.com.dbc.vemser.checkout.service;

import br.com.dbc.vemser.checkout.dtos.CheckoutItemDto;
import br.com.dbc.vemser.checkout.dtos.ItemInDTO;
import br.com.dbc.vemser.checkout.dtos.PedidoInDTO;
import br.com.dbc.vemser.checkout.entities.Pedido;
import br.com.dbc.vemser.checkout.entities.Produto;
import br.com.dbc.vemser.checkout.enums.Game;
import br.com.dbc.vemser.checkout.enums.StatusPedido;
import br.com.dbc.vemser.checkout.exceptions.RegraDeNegocioException;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.LineItem;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import lombok.RequiredArgsConstructor;
import org.hibernate.collection.internal.PersistentList;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@RequiredArgsConstructor
@Service
public class PagamentoService {

    private final ProdutoService produtoService;
    private final PedidoService pedidoService;

      @Value("${stripe.api.secretkey}")
      private String secretKey = "";

      //@Value("${stripe.api.publickey}")
      //private String publicKey = "";

    SessionCreateParams.LineItem.PriceData createPriceData(CheckoutItemDto checkoutItemDto) {
        return SessionCreateParams.LineItem.PriceData.builder()
                .setCurrency("BRL")
                .setUnitAmount((long)(checkoutItemDto.getPrice() * 100))
                .setProductData(
                        SessionCreateParams.LineItem.PriceData.ProductData.builder()
                                .setName(checkoutItemDto.getProductName())
                                .build())
                .build();
    }

      public Session criarSessionCheckout (PedidoInDTO pedidoInDTO,Integer idPedido) throws StripeException, RegraDeNegocioException {
            Stripe.apiKey = secretKey;

            SessionCreateParams params =
                    SessionCreateParams.builder()
                            .setMode(SessionCreateParams.Mode.PAYMENT)
                            .setSuccessUrl("https://google.com")
                            .setCancelUrl("https://google.com")
                            .addAllLineItem(criarItensParaPagamento(pedidoInDTO, idPedido))

                            /*
                            .addLineItem(
                                    SessionCreateParams.LineItem.builder()
                                            .setQuantity(1L)
                                            // Provide the exact Price ID (for example, pr_1234) of the product you want to sell
                                            .setPriceData(createPriceData(new CheckoutItemDto("teste", 1, 1.0, 1, 1)))
                                            .build())

                             */

                            .build();
            Session session = Session.create(params);

            return session;
    }

    private List<SessionCreateParams.LineItem> criarItensParaPagamento(PedidoInDTO pedidoInDTO, Integer idPedido) throws RegraDeNegocioException {
        List<SessionCreateParams.LineItem> itensParaPagamento = new ArrayList<>();
        List<ItemInDTO> itensParaRequisicao = pedidoInDTO.getItens();

        if(pedidoInDTO.getGame().equals(Game.LOSE)) {
            for (ItemInDTO item : itensParaRequisicao) {
                Produto produto = produtoService.findById(item.getIdProduto());
                SessionCreateParams.LineItem produtoEspecifico = SessionCreateParams.LineItem.builder()
                        .setQuantity((long) item.getQuantidadeProduto())
                        .setPriceData(createPriceData(new CheckoutItemDto(
                                produto.getNome(),
                                item.getQuantidadeProduto(),
                                produto.getPreco().doubleValue(),
                                produto.getIdProduto(),
                                1
                        )))
                        .build();
                itensParaPagamento.add(produtoEspecifico);
            }
        }else {
            for (ItemInDTO item : itensParaRequisicao) {
                Produto produto = produtoService.findById(item.getIdProduto());
                SessionCreateParams.LineItem produtoEspecifico = SessionCreateParams.LineItem.builder()
                        .setQuantity((long) item.getQuantidadeProduto())
                        .setPriceData(createPriceData(new CheckoutItemDto(
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

        /*
        SessionCreateParams.LineItem item1 = SessionCreateParams.LineItem.builder()
                .setQuantity(1L)
                // Provide the exact Price ID (for example, pr_1234) of the product you want to sell
                .setPriceData(createPriceData(new CheckoutItemDto(
                        pedido.getItens().get(0).getNome(),
                        pedido.getQuantidade(),
                        pedido.getPreco().doubleValue(),
                        pedido.getItens().get(0).getIdProduto(),
                        1)))
                .build();

        SessionCreateParams.LineItem item2 = SessionCreateParams.LineItem.builder()
                .setQuantity(1L)
                // Provide the exact Price ID (for example, pr_1234) of the product you want to sell
                .setPriceData(createPriceData(new CheckoutItemDto(
                        pedido.getItens().get(1).getNome(),
                        pedido.getQuantidade(),
                        pedido.getPreco().doubleValue(),
                        pedido.getItens().get(1).getIdProduto(),
                        1)))
                .build();
         */

        return itensParaPagamento;
    }

}
