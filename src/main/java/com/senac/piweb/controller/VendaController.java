package com.senac.piweb.controller;

import com.senac.piweb.model.Cliente;
import com.senac.piweb.model.Produto;
import com.senac.piweb.model.Venda;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class VendaController {

    private final List<Venda> listaVendas = new ArrayList();

    @GetMapping("/inserir-venda")
    public String cadastroFormvenda(Model model) {
        model.addAttribute("vendapro", new Venda());
        return "venda";
    }

    @GetMapping("/listagemVenda")
    public String listavenda(){
        return "listaVenda"; 
    }    
    @PostMapping("/gravar-venda")
    public String processarFormularioVenda(@ModelAttribute Venda ven, Model model){
        ven.setId(listaVendas.size() + 1);
        listaVendas.add(ven);
        return "redirect:/listaVenda";
    }
    /*
    public String listaFormVenda(Model model) {
        model.addAttribute("listavenda", listaVendas);
        return "listaVenda";
    }*/
}
