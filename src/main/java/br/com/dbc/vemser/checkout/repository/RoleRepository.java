package br.com.dbc.vemser.checkout.repository;

import br.com.dbc.vemser.checkout.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    public Optional<Role> findById(Integer idRole);
}
