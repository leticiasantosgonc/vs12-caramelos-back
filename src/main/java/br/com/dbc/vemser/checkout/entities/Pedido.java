package br.com.dbc.vemser.checkout.entities;

import br.com.dbc.vemser.checkout.enums.StatusPedido;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

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

    @Column(name = "CPF")
    private String cpf;

    @Column(name = "OBSERVACAO")
    private String observacao;

    @ManyToMany
    @JoinTable(
            name = "ITEM_PEDIDO",
            joinColumns = @JoinColumn(name = "ID_PEDIDO"),
            inverseJoinColumns = @JoinColumn(name = "ID_PRODUTO")
    )
    private List<Produto> itens;

    @Column(name = "STATUS")
    private StatusPedido status;

    @Column(name = "DATA")
    private LocalDate dataPedido;

    @Column(name = "QUANTIDADE")
    private Integer quantidade;

    @Column(name = "PRECO")
    private BigDecimal preco;

    @Column(name = "ID_SESSION")
    private String idSession;

}
