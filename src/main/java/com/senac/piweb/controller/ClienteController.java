package com.senac.piweb.controller;

import com.senac.piweb.model.Cliente;
import com.senac.piweb.model.ItemVenda;
import com.senac.piweb.service.ClienteService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ClienteController {

    @Autowired
    ClienteService clienteService;
   
    static List<ItemVenda> peguei = new ArrayList();
    static double total;
    static Cliente cliente = new Cliente();    

    @GetMapping("/")
    public String inicio() {
        return "index";
    }

    @GetMapping("/inserir-cliente")
    public String cadastroForm(Model model) {
        model.addAttribute("cliente", new Cliente());
        return "cadastroCli";
    }

    @GetMapping("/listaCliente")
    public String listaForm(Model model) {
        model.addAttribute("listacliente", clienteService.listarTodosClientes());
        return "listacliente";
    }

    @PostMapping("/gravarCliente")
    public String processarFormularioCliente(@RequestBody @ModelAttribute Cliente cliente, Model model) {
        if (cliente.getId() != null) {
            clienteService.atualizarCliente(cliente.getId(), cliente);
      
        } else {        
            clienteService.criarCliente(cliente);
        }
        return "redirect:/listaCliente";
    }     
    
    @GetMapping("/excluirCliente")
    public String excluirCliente(Model model, @RequestParam String id) {
        Integer idCliente = Integer.parseInt(id);
        clienteService.excluirCliente(idCliente);
        return "redirect:/listaCliente";
    }

    @GetMapping("/alteraCliente")
    public String alterarcliente(Model model, @RequestParam String id) {
        Integer idCliente = Integer.parseInt(id);
        Cliente clienteEncontrado = clienteService.buscarClientePorId(idCliente);
        model.addAttribute("cliente", clienteEncontrado);
        return "alteraCliente";
    }   
    
    @PostMapping("/buscarClienteNome")
    public String pesquisar(Model model, @RequestParam String nome) {
        List<Cliente> listacliente = clienteService.buscarClientePorNome(nome);
        model.addAttribute("listacliente", listacliente);
        return "listaCliente";
    }    
}
