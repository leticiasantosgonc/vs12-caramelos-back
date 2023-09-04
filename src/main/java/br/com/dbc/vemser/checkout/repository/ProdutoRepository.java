package br.com.dbc.vemser.checkout.repository;

import br.com.dbc.vemser.checkout.entities.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
}
