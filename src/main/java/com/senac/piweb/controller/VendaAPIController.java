package com.senac.piweb.controller;

import com.senac.piweb.model.Venda;
import com.senac.piweb.service.VendaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/vendas")

public class VendaAPIController {
    @Autowired
    VendaService vendaService;    
    
    @PostMapping("/adicionarVenda")
    public ResponseEntity<Venda> addVenda(@RequestBody Venda ven) {
        var novoVenda = vendaService.criar(ven);
        return new ResponseEntity<>(novoVenda, HttpStatus.CREATED);
    }
    
    @GetMapping("/listarVenda")
    public ResponseEntity<List> listarVen() {
        List<Venda> vend = vendaService.listarTodasVendas();
        return new ResponseEntity<>(vend, HttpStatus.OK);
    }
    @DeleteMapping("/excluirVenda/{id}")
    public ResponseEntity<?> deleteVenda(@PathVariable Integer id) {
        vendaService.excluirVenda(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }    
    
    
}
