package br.com.dbc.vemser.checkout.controller;

import br.com.dbc.vemser.checkout.dtos.PedidoInDTO;
import br.com.dbc.vemser.checkout.entities.Pedido;
import br.com.dbc.vemser.checkout.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.checkout.service.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pedido")
@RequiredArgsConstructor
@Validated
public class PedidoController {

    private final PedidoService pedidoService;

    @PostMapping("/criar")
    public ResponseEntity<Pedido> createPedido(@RequestBody PedidoInDTO pedidoInDTO) throws RegraDeNegocioException {
        return new ResponseEntity<>(pedidoService.createPedido(pedidoInDTO), HttpStatus.OK);
    }

}
