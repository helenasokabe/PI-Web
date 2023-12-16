package com.senac.piweb.repository;

import com.senac.piweb.model.Cliente;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer>{
    public List<Cliente> findByNomeContaining(String nome);

}
    

