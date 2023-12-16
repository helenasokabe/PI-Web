
package com.senac.piweb.repository;

import com.senac.piweb.model.ItemVenda;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemVendaRepository extends JpaRepository<ItemVenda, Integer>{

    List<ItemVenda> findByVendaId(Integer id);

//    List<ItemVenda> findByProdutoId(Integer id);

//    List<Produto> findByVendaId(Integer id);
    
}
