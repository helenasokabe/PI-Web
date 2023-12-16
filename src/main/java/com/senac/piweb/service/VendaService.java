package com.senac.piweb.service;

import com.senac.piweb.model.Cliente;
import com.senac.piweb.model.Produto;
import com.senac.piweb.model.Venda;
import com.senac.piweb.repository.VendaRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VendaService {

    @Autowired
    VendaRepository vendaRepository;

    public Venda criar(Venda vda) {
        vda.setId(null);
        vendaRepository.save(vda);
        return vda;
    }

    public List<Venda> listarTodasVendas() {
        return vendaRepository.findAll();
    }

    public Venda buscarVendasPorId(Integer id) {
        return vendaRepository.findById(id).orElseThrow();
    }

    public void excluirVenda(Integer id) {
        Venda objEncontrado = buscarVendasPorId(id);
        vendaRepository.deleteById(objEncontrado.getId());
    }

    public Venda UltimaVenda() {
        return vendaRepository.findFirstByOrderByIdDesc();
    }

    public List<Venda> buscarPorNomeCliente(Integer id) {
        return vendaRepository.findByClienteId(id);
    }
}
