package br.com.senai.domain.service;

import br.com.senai.api.assembler.ProdutoAssembler;
import br.com.senai.api.model.ProdutoDTO;
import br.com.senai.domain.exception.NegocioException;
import br.com.senai.domain.model.Pessoa;
import br.com.senai.domain.model.Produto;
import br.com.senai.domain.repository.ProdutoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@AllArgsConstructor
@Service
public class ProdutoService {

    private ProdutoRepository produtoRepository;
    private ProdutoAssembler produtoAssembler;

    public List<ProdutoDTO> listar() {
        return produtoAssembler.toCollectionModel(produtoRepository.findAll());
    }

    @Transactional
    public Produto cadastrar(Produto produto){
        produto.setValorTotalEmEstoque(produto.getQuantidade()*produto.getValorUnitario());
        return produtoRepository.save(produto);
    }

    @Transactional
    public void excluir(Long produtoId){
        produtoRepository.deleteById(produtoId);
    }

    public Produto buscar(Long produtoId){
        return produtoRepository.findById(produtoId)
                .orElseThrow(() -> new NegocioException("Produto não encontrada."));
    }
}
