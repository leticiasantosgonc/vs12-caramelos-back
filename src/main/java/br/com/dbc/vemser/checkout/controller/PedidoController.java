package br.com.dbc.vemser.checkout.controller;

import br.com.dbc.vemser.checkout.docs.PedidoControllerDoc;
import br.com.dbc.vemser.checkout.dtos.ListarPedidoPorDataOutDTO;
import br.com.dbc.vemser.checkout.dtos.PedidoInDTO;
import br.com.dbc.vemser.checkout.dtos.PedidoOutDTO;
import br.com.dbc.vemser.checkout.dtos.RelatorioPedido;
import br.com.dbc.vemser.checkout.entities.Pedido;
import br.com.dbc.vemser.checkout.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.checkout.service.PDFService;
import br.com.dbc.vemser.checkout.service.PagamentoService;
import br.com.dbc.vemser.checkout.service.PedidoService;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/pedido")
@RequiredArgsConstructor
@Validated
public class PedidoController implements PedidoControllerDoc {

    private final PedidoService pedidoService;
    private final PDFService pdfService;
    private final PagamentoService pagamentoService;

    @PostMapping("/criar")
    public ResponseEntity<Object> createPedido(@RequestBody PedidoInDTO pedidoInDTO) throws RegraDeNegocioException, StripeException {
        PedidoOutDTO pedido = pedidoService.createPedido(pedidoInDTO);
        Session session = pagamentoService.criarSessionCheckout(pedidoInDTO, pedido.getIdPedido());
        pedidoService.updateSessionId(pedido.getIdPedido(), session.getId());

        Map<String, Object> responseJson = new HashMap<>();
        responseJson.put("url", session.getUrl());
        responseJson.put("idPedido", pedido.getIdPedido());

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(pedido.getIdPedido())
                .toUri();

        return ResponseEntity.created(location).body(responseJson);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<PedidoOutDTO>> findAllPedidos() {
        return new ResponseEntity<>(pedidoService.findAllPedidos(), HttpStatus.OK);
    }

    @GetMapping("/listar-por-status")
    public ResponseEntity<Map<String, Long>> listarPedidosPorStatus() {
        return new ResponseEntity<>(pedidoService.listarPedidosPorStatus(), HttpStatus.OK);
    }

    @GetMapping("/listar-por-data")
    public ResponseEntity<List<ListarPedidoPorDataOutDTO>> listarPedidosPorData(@RequestParam("data") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate data) {
        System.out.println(data);
        return new ResponseEntity<>(pedidoService.listarPedidosPorData(data), HttpStatus.OK);
    }

    @GetMapping("/nota/{idPedido}")
    public ResponseEntity<Void> gerarNota(@PathVariable Integer idPedido) throws RegraDeNegocioException, StripeException, IOException {
        Pedido pedido = pedidoService.findPedidoUtils(idPedido);
        boolean deveGerarNota = pedidoService.deveGerarNota(idPedido);

        if (deveGerarNota) {
            HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
            response.setContentType("application/pdf");
            DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd:hh:mm:ss");
            String currentDateTime = dateFormatter.format(new Date());

            String headerKey = "Content-Disposition";
            String headerValue = "attachment; filename=pdf_" + currentDateTime + ".pdf";
            response.setHeader(headerKey, headerValue);
            response.setHeader("id-pedido", String.valueOf(idPedido));

            pdfService.generatePDF(response, pedido);
            pedidoService.atualizarStatusParaPago(idPedido);

            return ResponseEntity.ok().build();
        }

        return ResponseEntity.noContent().build();
    }

}
