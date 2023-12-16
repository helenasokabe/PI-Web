package com.senac.piweb.service;

import com.senac.piweb.model.ItemVenda;
import com.senac.piweb.repository.ItemVendaRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

@Service
public class ItemVendaService {

    @Autowired
    ItemVendaRepository itemvendaRepository;

    public ItemVenda criar(ItemVenda iv) {
        iv.setId(null);
        itemvendaRepository.save(iv);        
        return iv;
    }

    public ItemVenda buscarPorIdItemVenda(Integer id) {
        return itemvendaRepository.findById(id).orElseThrow();
    }

    public void excluirItemVenda(Integer id) {
        ItemVenda ivEncontrado = buscarPorIdItemVenda(id);
        itemvendaRepository.deleteById(ivEncontrado.getId());
        ItemVenda tutu = new ItemVenda();

    }

    public ItemVenda atualizar(Integer id, ItemVenda item) {
        ItemVenda encontrado = buscarPorIdItemVenda(id);
        encontrado.setProduto(item.getProduto());
        encontrado.setQuant(item.getQuant());
        encontrado.setVenda(item.getVenda());
        itemvendaRepository.save(encontrado);
        return encontrado;
    }
    
    public List<ItemVenda> listarPorIdVendas(Integer id){
        return itemvendaRepository.findByVendaId(id);
    }

}
