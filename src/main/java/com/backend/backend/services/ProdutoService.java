package com.backend.backend.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.backend.model.Produto;
import com.backend.backend.model.exception.ResourceNotFoundException;
import com.backend.backend.repository.ProdutoRepository;
import com.backend.backend.shared.ProdutoDTO;

@Service
public class ProdutoService {
    //Gerando dependencia do repository.

    @Autowired // Anotation de inversão de dependencia.
    private ProdutoRepository produtoRepository;

    /**
     * @return
     */
    public List<ProdutoDTO> obterTodos() {

        // Retorna uma lista de produtos model
        List<Produto> produtos = produtoRepository.findAll();

        return produtos.stream()
        .map(produto -> new ModelMapper()
        .map(produto, ProdutoDTO.class))
        .collect(Collectors.toList());
    }

    /**
     * @param id
     * @return
     */
    public Optional<ProdutoDTO> obterPorId(Integer id) {
        // obtendo optional de produtos pelo id
        Optional<Produto> produto = produtoRepository.findById(id);

        // Se não encontrar, lança exception
        if (produto.isEmpty()) {
            throw new ResourceNotFoundException("Produto não encontrado!");
        }

        // Convertendo o meu optional de produto em um produtoDto
        ProdutoDTO dto = new ModelMapper().map(produto.get(), ProdutoDTO.class);

        // Criando e retornando um optional de produtoDto.
        return Optional.of(dto);
    }

    /**
     * @param produto
     * @return
     */
    public ProdutoDTO adicionar(ProdutoDTO produtoDto) {
        produtoDto.setId(null);

        // Criar um objeto de mapeamento
        ModelMapper mapper = new ModelMapper();

        // converter produto dto em um produto
        Produto produto = mapper.map(produtoDto, Produto.class);

        // salvar o produto no banco
        produto = produtoRepository.save(produto);

        produtoDto.setId(produto.getId());

        // retornar o produto dto atualizado
        return produtoDto;
    }

    /**
     * @param id
     */
    public void deletar(Integer id) {
        // verificar se o produto existe.
        Optional<Produto> produto = produtoRepository.findById(id);

        // Se não existir exibe um exception.
        if (produto.isEmpty()) {
            throw new ResourceNotFoundException("Produto não encontrado!");
        }

        produtoRepository.deleteById(id);
    }

    /**
     * @param id
     * @param produto
     * @return
     */
    public ProdutoDTO atualizar(Integer id, ProdutoDTO produtoDto) {
        // Passar id para o produto dto
        produtoDto.setId(id);

        // Criar objeto de mapeamento
        ModelMapper mapper = new ModelMapper();

        // Converter dto em um produto
        Produto produto = mapper.map(produtoDto, Produto.class);

        // Atualizar os dados no banco
        produtoRepository.save(produto);

        // Retornar o produto atualizado
        return produtoDto;
    }
}