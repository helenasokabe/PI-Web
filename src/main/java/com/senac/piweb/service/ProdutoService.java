package com.senac.piweb.service;

import com.senac.piweb.model.Cliente;
import com.senac.piweb.model.Produto;
import com.senac.piweb.repository.ProdutoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService {
     @Autowired
   ProdutoRepository produtoRepository;  
   
   public Produto criarProduto(Produto prod){
       prod.setId(null);
       produtoRepository.save(prod);
       return prod;
   }
   
   public List<Produto> listarTodosProdutos() {
        return produtoRepository.findAll();
    }
   public List<Produto> listar(Integer id){
       return produtoRepository.findByClienteId(id);
   }
   public Produto buscarProdutoPorId(Integer id){
       return produtoRepository.findById(id).orElseThrow();
   }
   
   public List<Produto> buscarProdutoporNome(String nome){
        return produtoRepository.findByNomeprodutoContaining(nome);
    }
   public void excluirproduto(Integer id){
       Produto objEncontrado = buscarProdutoPorId(id); //valida se existe id informado
       produtoRepository.deleteById(objEncontrado.getId());
   }
    public Produto atualizar(Integer id, Produto editarProduto) {
        Produto produtoEncontrado = buscarProdutoPorId(id);
        produtoEncontrado.setNomeproduto(editarProduto.getNomeproduto());
        produtoEncontrado.setDescricao(editarProduto.getDescricao());
        produtoEncontrado.setEstoque(editarProduto.getEstoque());
        produtoEncontrado.setTamanho(editarProduto.getTamanho());
        produtoEncontrado.setValor(editarProduto.getValor());
        produtoRepository.save(editarProduto);
        return editarProduto;
    }
}
