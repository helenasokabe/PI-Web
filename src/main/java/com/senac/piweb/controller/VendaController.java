package com.senac.piweb.controller;

import static com.senac.piweb.controller.ClienteController.cliente;
import static com.senac.piweb.controller.ClienteController.peguei;
import static com.senac.piweb.controller.ClienteController.total;
import com.senac.piweb.model.Cliente;
import com.senac.piweb.model.ItemVenda;
import com.senac.piweb.model.Produto;
import com.senac.piweb.model.Venda;
import com.senac.piweb.service.ClienteService;
import com.senac.piweb.service.ItemVendaService;
import com.senac.piweb.service.ProdutoService;
import com.senac.piweb.service.VendaService;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller

public class VendaController {

    @Autowired
    ClienteService clienteService;

    @Autowired
    ProdutoService produtoService;

    @Autowired
    VendaService vendaService;

    @Autowired
    ItemVendaService itemVendaService;

    Venda venda = new Venda();

    @GetMapping("/listagemVenda")
    public String mostraListaVenda(Model model) {
        model.addAttribute("listaVenda", vendaService.listarTodasVendas());
        return "listaVenda";
    }

    @GetMapping("/detalhes-venda")
    public String listaFormProdutosVendidos(@RequestParam String id, Model model) {
        Integer idVend = Integer.parseInt(id);
        Venda selecionado = vendaService.buscarVendasPorId(idVend);
        ItemVenda itc = new ItemVenda();
        List<ItemVenda> itens = itemVendaService.listarPorIdVendas(selecionado.getId());

        model.addAttribute("venda", selecionado);
        model.addAttribute("reg", itc);
        model.addAttribute("itemvenda", itens);
        return "detalhes";
    }

    @GetMapping("/exibir-venda")
    public String mostraFormVenda(Model model, @RequestParam String id) {
        Integer idCliente = Integer.parseInt(id);
        cliente = clienteService.buscarClientePorId(idCliente);
        List<Produto> produtosEncontrados = produtoService.listarTodosProdutos();
        venda.setTotal(total);
        model.addAttribute("cliente", cliente);
        model.addAttribute("vendas", venda);
        model.addAttribute("produto", new Produto());
        model.addAttribute("produtos", produtosEncontrados);
        model.addAttribute("lista", peguei);
        return "exibir";
    }

    @PostMapping("/addProdutoVenda")
    public String adicionarProdutoComprar(Model model, @RequestParam String produtoId,
            @RequestParam Integer quantidade) {
        Integer idProd = Integer.parseInt(produtoId);
        Produto acheiprod = produtoService.buscarProdutoPorId(idProd);
        ItemVenda item = new ItemVenda();
        item.setQuant(quantidade);
        item.setProduto(acheiprod);
        ItemVenda ivs = itemVendaService.criar(item);
        peguei.add(ivs);
        total = total + (acheiprod.getValor() * item.getQuant());
        venda.setTotal(total);
        model.addAttribute("cliente", cliente);
        //model.addAttribute("itemvenda", new ItemVenda());       
        model.addAttribute("vendas", venda);
        model.addAttribute("lista", peguei);
        model.addAttribute("produtos", produtoService.listarTodosProdutos());
        return "exibir";
    }

    @GetMapping("/excluirProdutoAdd")
    public String excluirProdutoAdicionado(Model model, @RequestParam String id) {
        Integer idItem = Integer.parseInt(id);
        ItemVenda achei = itemVendaService.buscarPorIdItemVenda(idItem);
        peguei.remove(achei);
        itemVendaService.excluirItemVenda(achei.getId());
        total = total - (achei.getProduto().getValor() * achei.getQuant());
        venda.setTotal(total);
        model.addAttribute("cliente", cliente);
        model.addAttribute("produtos", produtoService.listarTodosProdutos());
        model.addAttribute("item", new ItemVenda());
        model.addAttribute("vendas", venda);
        model.addAttribute("lista", peguei);
        return "exibir";
    }

    @GetMapping("/desistiu")
    public String excluirTodosProdutosAdicionados(Model model) {
        peguei.clear();
        total = 0;
        venda.setTotal(total);
        return "index";
    }

    @GetMapping("/comprar")
    public String finalizaCompra(@RequestParam String tipoPagamento,
            @RequestParam Integer parcela, Model mode1) {
        LocalDateTime diaHora = LocalDateTime.now();
        venda.setCliente(cliente);
        venda.setTipoPagamento(tipoPagamento);
        venda.setParcela(parcela);
        venda.setTotal(total);
        venda.setDataVenda(diaHora);
        vendaService.criar(venda);
        Venda vendacliente = vendaService.UltimaVenda();
        for (ItemVenda it : peguei) {
            it.setVenda(vendacliente);
            itemVendaService.atualizar(it.getId(), it);
        }
        peguei.clear();
        total = 0;
        venda.setTotal(total);
        return "redirect:/listagemVenda";
    }

    @GetMapping("/exibir-novo")
    public String mostraFormVenda(Model model) {
        List<Produto> produtosEncontrados = produtoService.listarTodosProdutos();
        model.addAttribute("cliente", new Cliente());
        model.addAttribute("vendas", venda);
        model.addAttribute("produtos", produtosEncontrados);
        model.addAttribute("lista", peguei);
        peguei.clear();
        total = 0;
        return "exibir";
    }

    @GetMapping("/busca-venda")
    public String buscaVendaPorNome(Model model, @RequestParam String nome) {
        List<Venda> acheiVend = new ArrayList();
        List<Cliente> acheiCli = clienteService.buscarClientePorNome(nome);

        if (nome.equals("")) {
            acheiVend = vendaService.listarTodasVendas();
        } else {
            for (Cliente c : acheiCli) {
                acheiVend = vendaService.buscarPorNomeCliente(c.getId());
            }
        }

        model.addAttribute("cliente", new Cliente());
        model.addAttribute("listaVenda", acheiVend);

        return "listaVenda";
    }
}
