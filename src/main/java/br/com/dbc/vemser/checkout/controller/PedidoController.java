package br.com.dbc.vemser.checkout.controller;

import br.com.dbc.vemser.checkout.dtos.PedidoInDTO;
import br.com.dbc.vemser.checkout.dtos.RelatorioItemPedidoDTO;
import br.com.dbc.vemser.checkout.dtos.RelatorioPedido;
import br.com.dbc.vemser.checkout.entities.Pedido;
import br.com.dbc.vemser.checkout.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.checkout.service.PDFService;
import br.com.dbc.vemser.checkout.service.PagamentoService;
import br.com.dbc.vemser.checkout.service.PedidoService;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/pedido")
@RequiredArgsConstructor
@Validated
public class PedidoController {

    private final PedidoService pedidoService;
    private final PDFService pdfService;
    private final PagamentoService pagamentoService;

    @PostMapping("/criar")
    public ResponseEntity<Map<String, Object>> createPedido(@RequestBody PedidoInDTO pedidoInDTO) throws RegraDeNegocioException, IOException, StripeException {
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
            response.setContentType("application/pdf");
            DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd:hh:mm:ss");
            String currentDateTime = dateFormatter.format(new Date());

            String headerKey = "Content-Disposition";
            String headerValue = "attachment; filename=pdf_" + currentDateTime + ".pdf";
            response.setHeader(headerKey, headerValue);
            Pedido pedido = pedidoService.createPedido(pedidoInDTO);

            Session session = pagamentoService.criarSessionCheckout(pedidoInDTO);
            Map<String, Object> responseJson = new HashMap<>();
            responseJson.put("url", session.getUrl());

            pdfService.generatePDF(response, pedido);
            return ResponseEntity.ok(responseJson);
            //return new ResponseEntity<>(pedidoService.createPedido(pedidoInDTO), HttpStatus.OK);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Pedido>> findAllPedidos() {
        return new ResponseEntity<>(pedidoService.findAllPedidos(), HttpStatus.OK);
    }
    @GetMapping("/listar/itens")
    public ResponseEntity<RelatorioPedido> findByPedido() throws RegraDeNegocioException{
        return new ResponseEntity<>(pedidoService.relatorioItemPedido(), HttpStatus.OK);
    }
}
