package br.com.dbc.vemser.checkout.service;

import br.com.dbc.vemser.checkout.dtos.*;
import br.com.dbc.vemser.checkout.entities.Pedido;
import br.com.dbc.vemser.checkout.entities.Produto;
import br.com.dbc.vemser.checkout.enums.Game;
import br.com.dbc.vemser.checkout.enums.StatusPedido;
import br.com.dbc.vemser.checkout.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.checkout.repository.PedidoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final ProdutoService produtoService;
    private final ObjectMapper objectMapper;

    public PedidoOutDTO createPedido(PedidoInDTO pedidoInDTO) throws RegraDeNegocioException {
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
            produtoService.updateQuantidadeProduto(item.getIdProduto(), produto.getQuantidade()- item.getQuantidadeProduto());
        }

        BigDecimal valorTotal = BigDecimal.ZERO;

        for (Produto produto : produtos) {
            valorTotal = valorTotal.add(produto.getPreco());
        }

        if (pedidoInDTO.getGame().equals(Game.WIN)){

            Double valorComDescontoDouble = valorTotal.doubleValue() * 0.90;

            valorTotal = BigDecimal.valueOf(valorComDescontoDouble);

        }

        Pedido pedido = new Pedido();
        pedido.setCpf(validarCpf(pedidoInDTO.getCpf()));
        pedido.setObservacao(pedidoInDTO.getObservacao());
        pedido.setItens(produtos);
        pedido.setStatus(StatusPedido.NAO_PAGO);
        pedido.setDataPedido(LocalDate.now());
        pedido.setQuantidade(produtos.size());
        pedido.setPreco(valorTotal);
        pedido.setGame(pedidoInDTO.getGame());

        Pedido pedidoPersistido = pedidoRepository.save(pedido);
        return objectMapper.convertValue(pedidoPersistido, PedidoOutDTO.class);
    }

    public List<PedidoOutDTO> findAllPedidos() {
        return pedidoRepository
                .findAll()
                .stream()
                .map(pedido -> {
                    return objectMapper.convertValue(pedido, PedidoOutDTO.class);
                })
                .toList();
    }

    public void updateSessionId(Integer idPedido, String sessionId) throws RegraDeNegocioException {
        Pedido pedidoEncontrado = pedidoRepository
                .findById(idPedido)
                .orElseThrow(() -> new RegraDeNegocioException("Erro ao atualizar Session ID"));
        pedidoEncontrado.setIdSession(sessionId);
        pedidoRepository.save(pedidoEncontrado);
    }

    public boolean deveGerarNota(Integer idPedido) throws RegraDeNegocioException, StripeException {
        Pedido pedido = pedidoRepository
                .findById(idPedido)
                .orElseThrow(() -> new RegraDeNegocioException("Erro ao gerar nota"));

        String sessionId = pedido.getIdSession();
        Session session = Session.retrieve(sessionId);

        if (session.getPaymentStatus().equals("paid")) {
            if (pedido.getStatus().equals(StatusPedido.NAO_PAGO)) {
                return true;
            } else {
                throw new RegraDeNegocioException("Nota já emitida");
            }
        } else {
            throw new RegraDeNegocioException("Pagamento não foi efetuado");
        }
    }

    public void atualizarStatusParaPago(Integer idPedido) throws RegraDeNegocioException {
        Pedido pedidoEncontrado = pedidoRepository
                .findById(idPedido)
                .orElseThrow(() -> new RegraDeNegocioException("Erro ao atualizar status do pedido"));
        pedidoEncontrado.setStatus(StatusPedido.PAGO);
        pedidoRepository.save(pedidoEncontrado);
    }

    public String validarCpf(String cpf) throws RegraDeNegocioException {
        if (cpf.equals("")) {
            return "";
        }

        if (cpf.length() == 11) {
            int sum1 = 0;
            for (int i = 0; i < 9; i++) {
                int digit = Character.getNumericValue(cpf.charAt(i));
                sum1 += digit * (10 - i);
            }

            int digit1 = 11 - (sum1 % 11);
            if (digit1 >= 10) {
                digit1 = 0;
            }

            int sum2 = 0;
            for (int i = 0; i < 10; i++) {
                int digit = Character.getNumericValue(cpf.charAt(i));
                sum2 += digit * (11 - i);
            }

            int digit2 = 11 - (sum2 % 11);
            if (digit2 >= 10) {
                digit2 = 0;
            }

            if (Character.getNumericValue(cpf.charAt(9)) == digit1 && Character.getNumericValue(cpf.charAt(10)) == digit2) {
                return cpf;
            }
        }

        throw new RegraDeNegocioException("CPF inválido");
    }

    public Map<String, Long> listarPedidosPorStatus() {
        Map<String, Long> valores = new HashMap<>();
        valores.put("pagos", pedidoRepository.countByStatus(StatusPedido.PAGO));
        valores.put("naoPagos", pedidoRepository.countByStatus(StatusPedido.NAO_PAGO));
        return valores;
    }

    public List<ListarPedidoPorDataOutDTO> listarPedidosPorData(LocalDate data) {
        List<ListarPedidoPorDataOutDTO> listaDePedidos = pedidoRepository
                .findByDataPedido(data)
                .stream()
                .map(pedido -> {
                    ListarPedidoPorDataOutDTO pedidoPorData = objectMapper.convertValue(pedido, ListarPedidoPorDataOutDTO.class);
                    Map<Integer, ListarPedidoPorDataItensOutDTO> mapaItens = new HashMap<>();

                    for (Produto item : pedido.getItens()) {
                        Integer idProduto = item.getIdProduto();
                        ListarPedidoPorDataItensOutDTO itemOutDTO = mapaItens.getOrDefault(idProduto, new ListarPedidoPorDataItensOutDTO());
                        itemOutDTO.setIdProduto(idProduto);
                        itemOutDTO.setQuantidade(item.getQuantidade() + item.getQuantidade());
                        itemOutDTO.setValorDoPedido(item.getPreco().multiply(BigDecimal.valueOf(item.getQuantidade())));
                        itemOutDTO.setTipoProduto(item.getTipoProduto());
                        mapaItens.put(idProduto, itemOutDTO);
                    }

                    pedidoPorData.setItensPedido(new ArrayList<>(mapaItens.values()));
                    pedidoPorData.setValorTotal(pedido.getPreco().multiply(BigDecimal.valueOf(pedido.getQuantidade())));
                    return pedidoPorData;
                })
                .toList();

        return listaDePedidos;
    }

    public Pedido findPedidoUtils(Integer idPedido) throws RegraDeNegocioException {
        return pedidoRepository
                .findById(idPedido)
                .orElseThrow(() -> new RegraDeNegocioException("Pedido não encontrado"));
    }

    private Pedido findByIdPedido(Integer idPedido) {
        return pedidoRepository.findByIdPedido(idPedido);
    }

}
