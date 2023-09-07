package br.com.dbc.vemser.checkout.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Entity(name = "COMBO")
public class Combo {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_COMBO_GENERATOR")
    @SequenceGenerator(name = "SEQ_COMBO_GENERATOR", sequenceName = "SEQ_COMBO", allocationSize = 1)
    @Column(name = "ID_COMBO")
    private Integer idCombo;

    @Column(name = "NOME")
    private String nome;

    @Column(name = "DESCRICAO")
    private String descricao;

    @Lob
    @Column(name = "IMAGEM")
    private String imagem;

    @Column(name = "QUANTIDADE")
    private Integer quantidade;

    @ManyToMany
    @JoinTable(
            name = "COMBO_PRODUTO",
            joinColumns = @JoinColumn(name = "ID_COMBO"),
            inverseJoinColumns = @JoinColumn(name = "ID_PRODUTO")
    )
    private List<Produto> lanches;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "COMBO_PRODUTO",
            joinColumns = @JoinColumn(name = "ID_COMBO"),
            inverseJoinColumns = @JoinColumn(name = "ID_PRODUTO")
    )
    private List<Produto> bebidas;

    @Column(name = "PRECO")
    private BigDecimal preco;

}
