package br.com.dbc.vemser.checkout.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity(name = "PEDIDO")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PEDIDO_SEQ")
    @SequenceGenerator(name = "PEDIDO_SEQ", sequenceName = "SEQ_PEDIDO", allocationSize = 1)
    @Column(name = "ID_PEDIDO")
    private Integer idPedido;

    @Column(name = "PRECO")
    private BigDecimal preco;

    @Column(name = "DATA")
    private LocalDate dataPedido;

    @Column(name = "CPF")
    private String cpf;

//    private List<Produto> produtos;
//
//    @OneToOne(mappedBy = "pedido")
//    private Pagamento pagamento;

}
