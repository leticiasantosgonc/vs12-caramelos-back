package br.com.dbc.vemser.checkout.service;

import br.com.dbc.vemser.checkout.entities.Pedido;
import br.com.dbc.vemser.checkout.enums.Game;
import br.com.dbc.vemser.checkout.enums.StatusPedido;
import br.com.dbc.vemser.checkout.repository.PedidoRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
class PedidoServiceTest {
    @InjectMocks
    private PedidoService pedidoService;
    @Mock
    private PedidoRepository pedidoRepository;

    @Test
    public void testFindAllPedidos() {
        Pedido pedido1 = new Pedido();
        pedido1.setIdPedido(1);
        pedido1.setCpf("11111111111");
        pedido1.setObservacao("observação");
        pedido1.setItens(new ArrayList<>());
        pedido1.setStatus(StatusPedido.PAGO);
        pedido1.setDataPedido(LocalDate.now());
        pedido1.setQuantidade(1);
        pedido1.setPreco(new BigDecimal("10.99"));
        pedido1.setGame(Game.WIN);
        pedido1.setIdSession("id_session");

        Pedido pedido2 = new Pedido();
        pedido2.setIdPedido(1);
        pedido2.setCpf("22222222222");
        pedido2.setObservacao("observação");
        pedido2.setItens(new ArrayList<>());
        pedido2.setStatus(StatusPedido.PAGO);
        pedido2.setDataPedido(LocalDate.now());
        pedido2.setQuantidade(1);
        pedido2.setPreco(new BigDecimal("10.99"));
        pedido2.setGame(Game.WIN);
        pedido2.setIdSession("id_session");

        List<Pedido> pedidosSimulados = new ArrayList<>();
        pedidosSimulados.add(pedido1);
        pedidosSimulados.add(pedido2);

        when(pedidoRepository.findAll()).thenReturn(pedidosSimulados);

        assertEquals(pedidosSimulados.get(0).getIdPedido(), pedido1.getIdPedido());
    }

}