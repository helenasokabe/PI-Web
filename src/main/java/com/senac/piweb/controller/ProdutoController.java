/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.senac.piweb.controller;

import com.senac.piweb.model.Cliente;
import com.senac.piweb.model.Produto;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ProdutoController{
    private final List<Cliente> listaClientepro = new ArrayList();
    private final List<Produto> listaProdutopro = new ArrayList(); 
    
    @GetMapping("/inserir-produto") 
    public String cadastroFormpro(Model model){
        model.addAttribute("prod", new Produto());
        return "cadastroProd"; 
    }  
     @GetMapping("/listagempro")
    public String listaFormpro(Model model){
        model.addAttribute("listaProd", listaProdutopro);
        return "listaProduto"; 
    } 
    
    @PostMapping("/gravarpro")
    public String processarFormulariopro(@ModelAttribute Produto prod, Model model){
        prod.setId(listaProdutopro.size() + 1);
        listaProdutopro.add(prod);
        return "redirect:/listagempro";
    }
}
