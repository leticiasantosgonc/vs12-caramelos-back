package br.com.dbc.vemser.checkout.repository;

import br.com.dbc.vemser.checkout.entities.Produto;
import br.com.dbc.vemser.checkout.enums.TipoProduto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

    @Query("SELECT p FROM PRODUTO p where p.tipoProduto =:tipoProduto")
    public List<Produto> findByTipoProduto(TipoProduto tipoProduto);
}
