package com.backend.backend.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.backend.backend.model.Produto;
import com.backend.backend.model.exception.ResourceNotFoundException;

@Repository
public class ProdutoRepository_old {
    private List<Produto> produtos = new ArrayList<Produto>();
    private Long ultimoId = (long) 0;

    /**
     * Metodo para obter todos os produtos
     * @return
     */
    public List<Produto> obterTodos() {
        return produtos;
    }
    

    /**
     * Metodo para buscar produto por id
     * @param id
     * @return
     */
    public Optional<Produto> obterPorId(Long id) {
        return produtos.stream().filter(item -> item.getId() == id).findFirst();
    }

    /**
     * Metodo para adicionar produto
     * @param produto
     * @return
     */
    public Produto adicionar(Produto produto) {
        ultimoId++;

        produto.setId(ultimoId);
        produtos.add(produto);

        return produto;
    }

    /**
     * Metodo para deletar produto por id
     * @param id
     */
    public void deletar(Long id) {
        produtos.removeIf(filter -> filter.getId() == id);
    }

    /**
     * Metodo para atualizar o produto
     * @param produto
     * @return
     */
    public Produto atualizar(Produto produto) {
        Optional<Produto> produtoEncontrado = obterPorId(produto.getId());

        if (produtoEncontrado.isEmpty()) {
            throw new ResourceNotFoundException("Produto não encontrado!");
        }

        deletar(produto.getId());

        produtos.add(produto);

        return produto;
    }
}