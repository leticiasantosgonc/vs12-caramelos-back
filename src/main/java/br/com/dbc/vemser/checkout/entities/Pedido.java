package br.com.dbc.vemser.checkout.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @Column(name = "NOME")
    private String nome;

    @Column(name = "CPF")
    private String cpf;

    @Column(name = "DESCRICAO")
    private String descricao;

    @Lob
    @Column(name = "IMAGEM")
    private String imagem;

    @ManyToMany
    @JoinTable(
            name = "ITEM_PEDIDO",
            joinColumns = @JoinColumn(name = "ID_PEDIDO"),
            inverseJoinColumns = @JoinColumn(name = "ID_PRODUTO")
    )
    private List<Produto> lanches;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "ITEM_PEDIDO",
            joinColumns = @JoinColumn(name = "ID_PEDIDO"),
            inverseJoinColumns = @JoinColumn(name = "ID_PRODUTO")
    )
    private List<Produto> bebidas;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "ITEM_PEDIDO",
            joinColumns = @JoinColumn(name = "ID_PEDIDO"),
            inverseJoinColumns = @JoinColumn(name = "ID_PRODUTO")
    )
    private List<Produto> sobremesas;

    @Column(name = "DATA")
    private LocalDate dataPedido;

    @Column(name = "QUANTIDADE")
    private Integer quantidade;

    @Column(name = "PRECO")
    private BigDecimal preco;

}
