package br.com.dbc.vemser.checkout.service;

import br.com.dbc.vemser.checkout.dtos.CheckoutItemDto;
import br.com.dbc.vemser.checkout.entities.Pedido;
import br.com.dbc.vemser.checkout.entities.Produto;
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

@RequiredArgsConstructor
@Service
public class PagamentoService {

    private final ProdutoService produtoService;

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

      public Session criarSessionCheckout () throws StripeException, RegraDeNegocioException {
            Stripe.apiKey = secretKey;

            SessionCreateParams params =
                    SessionCreateParams.builder()
                            .setMode(SessionCreateParams.Mode.PAYMENT)
                            .setSuccessUrl("https://mayra.dev")
                            .setCancelUrl("https://mayra.dev")

                            // todo: inserir lista de pedidos aqui

                            .addAllLineItem(criarItensParaTeste())

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

    public List<SessionCreateParams.LineItem> criarItensParaTeste() throws RegraDeNegocioException {
        List<SessionCreateParams.LineItem> itens = new ArrayList<>();

        Pedido pedido = new Pedido();
        pedido.setCpf("74686622077");
        pedido.setObservacao("Observação");


        List<Produto> produtos = new ArrayList<>();
        produtos.add(produtoService.findById(15));
        produtos.add(produtoService.findById(79));

        pedido.setItens(produtos);

        pedido.setStatus(StatusPedido.NAO_PAGO);
        pedido.setDataPedido(LocalDate.now());
        pedido.setQuantidade(1);
        pedido.setPreco(new BigDecimal("19.99"));

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

        itens.add(item1);
        itens.add(item2);

        return itens;
    }

}
