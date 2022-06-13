package br.com.cotiinformatica.interfaces;

import java.util.List;

import br.com.cotiinformatica.entities.Produto;

public interface IProdutoRepository{

	void create(Produto produto) throws Exception;

	void update(Produto produto) throws Exception;
	
	void update(Integer idProduto, String senha) throws Exception;

	void delete(Produto produto) throws Exception;

	List<Produto> findAll(String nome, Integer idUsuario) throws Exception;

	Produto findById(Integer idProduto) throws Exception;

}
