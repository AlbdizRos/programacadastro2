package br.com.cotiinformatica.models;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProdutosCadastroModel {
	
	private String nomeProduto;
	private Integer quantidadeProduto;
	private Double precoProduto;
	private String dataValidade;
	private String descricaoProduto;
}
