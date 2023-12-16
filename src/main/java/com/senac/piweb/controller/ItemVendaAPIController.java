package com.senac.piweb.controller;

import com.senac.piweb.model.ItemVenda;
import com.senac.piweb.service.ItemVendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/itemvenda")

public class ItemVendaAPIController {
    @Autowired
    ItemVendaService itemvendaService;    
    
    @GetMapping("/adicionarItemVenda")
    public ResponseEntity<ItemVenda> additemVenda(@RequestBody ItemVenda iven) {
        var novaIVenda = itemvendaService.buscarPorIdItemVenda(Integer.SIZE);
        return new ResponseEntity<>(novaIVenda, HttpStatus.CREATED);
    }

    @DeleteMapping("/excluiritemVenda/{id}")
    public ResponseEntity<?> deleteItemVenda(@PathVariable Integer id) {
        itemvendaService.excluirItemVenda(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }    
}
