package com.senac.piweb.repository;

import com.senac.piweb.model.Produto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
    public List<Produto> findByNomeprodutoContaining(String nome);
    List<Produto> findByClienteId(Integer id);
    
}
