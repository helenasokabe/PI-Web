package com.senac.piweb.controller;

import com.senac.piweb.model.Cliente;
import com.senac.piweb.service.ClienteService;
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
@RequestMapping("/cliente")

public class ClienteAPIController {

    @Autowired
    ClienteService clienteService;

    @PostMapping("/adicionarCliente")
    public ResponseEntity<Cliente> addCliente(@RequestBody Cliente clie) {
        var novocliente = clienteService.criarCliente(clie);
        return new ResponseEntity<>(novocliente, HttpStatus.CREATED);
    }

    @GetMapping("/listarCliente")
    public ResponseEntity<List> listarCli() {
        List<Cliente> cliente = clienteService.listarTodosClientes();
        return new ResponseEntity<>(cliente, HttpStatus.OK);
    }

    @GetMapping("/buscarClienteId/{id}")
    public ResponseEntity<Cliente> pesquisar(@PathVariable Integer id) {
        Cliente clienteEncontrado = clienteService.buscarClientePorId(id);
        return new ResponseEntity<>(clienteEncontrado, HttpStatus.OK);
    }

    @PutMapping("/atualizarCliente/{id}")
    public ResponseEntity<Cliente> alteraCliente(@PathVariable Integer id, @RequestBody Cliente cliente) {
        var editarCliente = clienteService.atualizarCliente(id, cliente);
        return new ResponseEntity<>(editarCliente, HttpStatus.OK);
    }

    @DeleteMapping("/excluirCliente/{id}")
    public ResponseEntity<?> deleteCliente(@PathVariable Integer id) {
        clienteService.excluirCliente(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
