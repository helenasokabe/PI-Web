package com.senac.piweb.controller;

import com.senac.piweb.model.Produto;
import com.senac.piweb.service.ProdutoService;
import jakarta.validation.Valid;
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
public class ProdutoController {

    @Autowired
    ProdutoService produtoService;
    private final List<Produto> listaProdutopro = new ArrayList();

    @GetMapping("/inserir-produto")
    public String cadastroFormPro(Model model) {
        model.addAttribute("prod", new Produto());
        return "cadastroProd";
    }

    @PostMapping("/gravarProduto")
    public String processarProduto(@Valid @RequestBody @ModelAttribute Produto prod, Model model) {
        if (prod.getId() != null) {
            produtoService.atualizar(prod.getId(), prod);
        } else {
            produtoService.criarProduto(prod);
        }
        return "redirect:/listaProduto";
    }

    @GetMapping("/alteraProduto")
    public String alteraProduto(Model model, @RequestParam String id) {
        Integer idProd = Integer.parseInt(id);
        Produto produtoEncontrado = produtoService.buscarProdutoPorId(idProd);
        model.addAttribute("prod", produtoEncontrado);
        return "alteraProduto";
    }

    @GetMapping("/listaProduto")
    public String mostraListaProdutos(Model model) {
        model.addAttribute("listaproduto", produtoService.listarTodosProdutos());
        return "listaProduto";
    }

    @GetMapping("/listagempro")
    public String listaFormpro(Model model) {
        model.addAttribute("listaProd", listaProdutopro);
        return "listaProduto";
    }
   
    @GetMapping("/excluirProduto")
    public String excluirProduto(Model model, @RequestParam String id) {
        Integer idProduto = Integer.parseInt(id);
        produtoService.excluirproduto(idProduto);
        return "redirect:/listaProduto";
    }

    @PostMapping("/buscarProdutoNome")
    public String pesquisarProduto(Model model, @RequestParam String nome) {
        List<Produto> listaprod = produtoService.buscarProdutoporNome(nome);
        model.addAttribute("listaproduto", listaprod);
        return "listaProduto";
    }
}
