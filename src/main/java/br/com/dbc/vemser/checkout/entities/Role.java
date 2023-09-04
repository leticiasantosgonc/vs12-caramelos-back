package br.com.dbc.vemser.checkout.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.Set;
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity(name = "ROLE")
public class Role {

    @Id
    @Column (name = "ID_ROLE")
    private Integer idRole;

    @Column(name = "NOME")
    private String nome;

//    @JsonIgnore
//    @ManyToMany(mappedBy = "ROLES")
//    private Set<Usuario> usuarios;

    public String getAuthority(){return nome;}
}
