package com.senac.piweb.service;

import com.senac.piweb.model.Cliente;
import com.senac.piweb.repository.ClienteRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
 
public class ClienteService {

    @Autowired
    ClienteRepository clienteRepository;

    public Cliente criarCliente(Cliente cli) {
        cli.setId(null);
        clienteRepository.save(cli);
        return cli;
    }

    public List<Cliente> listarTodosClientes() {
        return clienteRepository.findAll();
    }

    public Cliente buscarClientePorId(Integer id) {
        return clienteRepository.findById(id).orElseThrow();
    }
    public List<Cliente> buscarClientePorNome(String nome){
        return clienteRepository.findByNomeContaining(nome);
    }

    public void excluirCliente(Integer id) {
        Cliente clienteEncontrado = buscarClientePorId(id);
        clienteRepository.deleteById(clienteEncontrado.getId());
    }

    public Cliente atualizarCliente(Integer id, Cliente clienteEnviado) {
        Cliente clienteEncontrado = buscarClientePorId(id);
        clienteEncontrado.setNome(clienteEnviado.getNome());
        clienteEncontrado.setCpf(clienteEnviado.getCpf());
        clienteEncontrado.setNascimento(clienteEnviado.getNascimento());
        clienteEncontrado.setEmail(clienteEnviado.getEmail());
        clienteEncontrado.setNivel(clienteEnviado.getNivel());
        clienteEncontrado.setTelefone(clienteEnviado.getTelefone());
        clienteRepository.save(clienteEncontrado);
        return clienteEncontrado;
    }

}
