package br.com.dbc.vemser.checkout.entities;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity(name = "ROLE")
public class Role implements GrantedAuthority {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column (name = "ID_ROLE")
    private Integer idRole;

    @Column(name = "NOME")
    private String nome;

    @ManyToMany
    @JoinTable(
            name = "USUARIO_ROLE",
            joinColumns = @JoinColumn(name = "ID_ROLE"),
            inverseJoinColumns = @JoinColumn(name = "ID_USUARIO")
    )
    private Set<Usuario> usuarios = new HashSet<>();

    @Override
    public String getAuthority() {
        return nome;
    }
}
