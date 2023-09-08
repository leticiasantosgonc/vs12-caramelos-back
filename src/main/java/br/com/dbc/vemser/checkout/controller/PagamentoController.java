package br.com.dbc.vemser.checkout.controller;

import br.com.dbc.vemser.checkout.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.checkout.service.PagamentoService;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/pagamento")
@CrossOrigin
public class PagamentoController {

    private final PagamentoService pagamentoService;

    @GetMapping("/criar-checkout")
    public ResponseEntity<Object> criarSessionCheckout() throws StripeException, RegraDeNegocioException {
        Session session = pagamentoService.criarSessionCheckout();
        Map<String, Object> responseJson = new HashMap<>();
        responseJson.put("url", session.getUrl());

        return ResponseEntity.ok(responseJson);
    }

}
