package br.com.dbc.vemser.checkout.entities;

import br.com.dbc.vemser.checkout.enums.MarcaProduto;
import br.com.dbc.vemser.checkout.enums.TamanhoProduto;
import br.com.dbc.vemser.checkout.enums.TipoProduto;
import br.com.dbc.vemser.checkout.enums.DietaProduto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Entity(name = "PRODUTO")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_PRODUTO_GENERATOR")
    @SequenceGenerator(name = "SEQ_PRODUTO_GENERATOR", sequenceName = "SEQ_PRODUTO", allocationSize = 1)
    @Column(name = "ID_PRODUTO")
    private Integer idProduto;

    @Column(name = "NOME")
    private String nome;

    @Column(name = "DESCRICAO")
    private String descricao;
    @Lob
    @Column(name = "IMAGEM")
    private String imagem;

    @Column(name = "QUANTIDADE")
    private Integer quantidade;

    @Column(name = "MARCA")
    @Enumerated(EnumType.STRING)
    private MarcaProduto marca;

    @Column(name = "TAMANHO")
    @Enumerated(EnumType.STRING)
    private TamanhoProduto tamanhoProduto;

    @Column(name = "TIPO")
    @Enumerated(EnumType.STRING)
    private TipoProduto tipoProduto;

    @Column(name = "VEGETARIANO")
    @Enumerated(EnumType.STRING)
    private DietaProduto dietaProduto;

    @Column(name = "PRECO")
    private BigDecimal preco;

    @Column(name = "DADOS_IMG")
    @Lob
    @Type(type = "org.hibernate.type.BinaryType")
    private byte[] dados_img;

    @Column(name = "TIPO_IMG")
    private String tipo_img;

}
