package com.backend.backend.services;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.backend.model.Produto;
import com.backend.backend.repository.ProdutoRepository_old;

@Service
public class ProdutoService {
    //Gerando dependencia do repository.

    @Autowired // Anotation de inversão de dependencia.
    private ProdutoRepository_old produtoRepository;

    /**
     * Retorna todos os produtos
     * @return
     */
    public List<Produto> obterTodos() {
        return produtoRepository.obterTodos();
    }

    /**
     * 
     * @param id
     * @return
     */
    public Optional<Produto> obterPorId(Long id) {
        return produtoRepository.obterPorId(id);
    }

    /**
     * 
     * @param produto
     * @return
     */
    public Produto adicionar(Produto produto) {
        return produtoRepository.adicionar(produto);
    }

    /**
     * 
     * @param id
     */
    public void deletar(Long id) {
        produtoRepository.deletar(id);
    }

    /**
     * 
     * @param id
     * @param produto
     * @return
     */
    public Produto atualizar(Long id, Produto produto) {
        produto.setId(id);

        return produtoRepository.atualizar(produto);
    }
}