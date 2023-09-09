package br.com.dbc.vemser.checkout.entities;

import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity(name = "IMG_TESTE")
public class Imagem {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IMG_SEQ")
    @SequenceGenerator(name = "IMG_SEQ", sequenceName = "SEQ_IMG", allocationSize = 1)
    @Column(name = "ID_IMG")
    private Integer idImg;

    @Column(name = "NOME")
    private String nome;

    @Column(name = "TIPO")
    private String tipo;

    @Column(name = "DADO")
    @Lob
    @Type(type = "org.hibernate.type.BinaryType")
    private byte[] data;

}
