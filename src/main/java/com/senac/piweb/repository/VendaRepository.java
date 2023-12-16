package com.senac.piweb.repository;

import com.senac.piweb.model.Venda;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface VendaRepository extends JpaRepository<Venda, Integer>{
    List<Venda> findByClienteId(Integer id);
    
    Venda findFirstByOrderByIdDesc();
}
