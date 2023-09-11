package br.com.dbc.vemser.checkout.repository;

import br.com.dbc.vemser.checkout.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    public Optional<Role> findById(Integer idRole);
}
