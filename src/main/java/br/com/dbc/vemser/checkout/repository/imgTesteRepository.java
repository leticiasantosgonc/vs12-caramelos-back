package br.com.dbc.vemser.checkout.repository;

import br.com.dbc.vemser.checkout.entities.ImgTeste;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface imgTesteRepository extends JpaRepository<ImgTeste, Integer> {
}
