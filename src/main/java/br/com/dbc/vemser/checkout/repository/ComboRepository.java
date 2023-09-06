package br.com.dbc.vemser.checkout.repository;

import br.com.dbc.vemser.checkout.entities.Combo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComboRepository extends JpaRepository<Combo, Integer> {
}
