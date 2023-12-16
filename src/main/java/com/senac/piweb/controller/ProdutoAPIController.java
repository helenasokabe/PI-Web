package com.senac.piweb.controller;

import com.senac.piweb.model.Produto;
import com.senac.piweb.service.ProdutoService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/produto")
public class ProdutoAPIController {
@Autowired
    ProdutoService produtoService;

    @PostMapping("/adicionarProduto")
    public ResponseEntity<Produto> addProduto(@Valid @RequestBody Produto pro) {
        var novoproduto = produtoService.criarProduto(pro);
        return new ResponseEntity<>(novoproduto, HttpStatus.CREATED);
    }

    @GetMapping("/listarProduto")
    public ResponseEntity<List> listar() {
        List<Produto> produtos = produtoService.listarTodosProdutos();
        return new ResponseEntity<>(produtos, HttpStatus.OK);
    }

    @GetMapping("/buscarProduto/{id}")
    public ResponseEntity<Produto> pesquisarProduto(@PathVariable Integer id) {
        Produto produtoEncontrado = produtoService.buscarProdutoPorId(id);
        return new ResponseEntity<>(produtoEncontrado, HttpStatus.OK);
    }

    @PutMapping("/atualizarProduto/{id}")
    public ResponseEntity<Produto> editProduto(@Valid @PathVariable Integer id, @RequestBody Produto prod) {
        var editarProduto = produtoService.atualizar(id, prod);
        return new ResponseEntity<>(editarProduto, HttpStatus.OK);
    }

    @DeleteMapping("/excluirproduto/{id}")
    public ResponseEntity<?> deleteProduto(@PathVariable Integer id) {
        produtoService.excluirproduto(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
}
