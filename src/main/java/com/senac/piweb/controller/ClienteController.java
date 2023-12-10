package com.senac.piweb.controller;

import com.senac.piweb.model.Cliente;
import com.senac.piweb.model.Produto;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ClienteController {
    private final List<Cliente> listaCliente = new ArrayList();
    private final List<Produto> listaProduto = new ArrayList();
    
    @GetMapping("/") 
    public String inicio(){
        return "index"; 
    }
    
    @GetMapping("/inserir-cliente") 
    public String cadastroForm(Model model){
        model.addAttribute("cliente", new Cliente());
        return "cadastroCli"; 
    }  
    
    @GetMapping("/listagem")
    public String listaForm(Model model){
        model.addAttribute("listacliente", listaCliente);
        return "listacliente"; 
    } 
    
    @PostMapping("/gravar")
    public String processarFormulario(@ModelAttribute Cliente cli, Model model){
        cli.setId(listaCliente.size() + 1);
        listaCliente.add(cli);
        return "redirect:/listagem";
    }
    //@PostMapping("/gravar-venda")
    //public String gravarComentarioUsuario(@ModelAttribute Cliente cliente, @ModelAttribute Produto prod, Model model){
      //  prod.setId(listaProduto.size()+1);
        //prod.
        //listaAnalise.add(analise);
       // return "redirect:/listagem";
    //}    
    
   @GetMapping("/exibir")
    public String mostravenda(Model model){
        return "exibir";
    }
   /*public String mostraDetalhes(Model model, @RequestParam String id){
        Integer idCliente = Integer.parseInt(id);
        Cliente registroEncontrado = new Cliente();
        for(Cliente l: listaCliente){
           if(l.getId() == idCliente) {
               registroEncontrado = l;
               break;
           }
        }
        
        Produto produtoEncontrado = new Produto();
        for (Produto com: listaProduto) {
            if (com.getId() == idProduto) {
                analiseEncontrada = com;
                break;
            } else {
            }
        }
        
        model.addAttribute("filme", registroEncontrado);
        model.addAttribute("analise", analiseEncontrada);
        return "exibir";
    }    */
}
