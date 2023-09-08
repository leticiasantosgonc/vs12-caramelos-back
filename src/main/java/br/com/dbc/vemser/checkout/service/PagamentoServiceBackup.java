
/*

package br.com.dbc.vemser.checkout.service;

import br.com.dbc.vemser.checkout.dtos.CheckoutItemDto;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PagamentoService {

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

    public Session criarSessionCheckout () throws StripeException {
        Stripe.apiKey = secretKey;

        SessionCreateParams params =
                SessionCreateParams.builder()
                        .setMode(SessionCreateParams.Mode.PAYMENT)
                        .setSuccessUrl("https://mayra.dev")
                        .setCancelUrl("https://mayra.dev")

                        // todo: inserir lista de pedidos aqui

                        .addLineItem(
                                SessionCreateParams.LineItem.builder()
                                        .setQuantity(1L)
                                        // Provide the exact Price ID (for example, pr_1234) of the product you want to sell
                                        .setPriceData(createPriceData(new CheckoutItemDto("teste", 1, 1.0, 1, 1)))
                                        .build())
                        .addLineItem(
                                SessionCreateParams.LineItem.builder()
                                        .setQuantity(1L)
                                        // Provide the exact Price ID (for example, pr_1234) of the product you want to sell
                                        .setPriceData(createPriceData(new CheckoutItemDto("teste", 1, 1.0, 1, 1)))
                                        .build())
                        .build();
        Session session = Session.create(params);

        return session;
    }
}
*/