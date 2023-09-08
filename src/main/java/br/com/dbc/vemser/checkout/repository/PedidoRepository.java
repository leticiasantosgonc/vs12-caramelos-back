package br.com.dbc.vemser.checkout.repository;

import br.com.dbc.vemser.checkout.dtos.RelatorioItemPedidoDTO;
import br.com.dbc.vemser.checkout.entities.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer> {

    @Query("SELECT NEW br.com.dbc.vemser.checkout.dtos.RelatorioItemPedidoDTO(p.nome, COUNT(p.idProduto)) " +
            "FROM PEDIDO p2 " +
            "LEFT JOIN p2.itens p " +
            "WHERE p2.idPedido = :idPedido " +
            "GROUP BY p.nome " +
            "ORDER BY p.nome ASC")
    List<RelatorioItemPedidoDTO> findAllByIdPedido(@Param("idPedido") Integer idPedido);
}
//@Query("""
//        SELECT NEW br.com.dbc.vemser.walletlife.dto.UsuarioComInvestimentoDTO
//        (u.idUsuario, u.nome, i.idInvestimento, i.valor,i.descricao, i.corretora)
//        FROM Usuario u
//        JOIN u.investimentoEntities i
//        WHERE (:corretora is null OR trim(upper(i.corretora)) = trim(upper(:corretora))) AND u.idUsuario=:idPessoa
//    """)
