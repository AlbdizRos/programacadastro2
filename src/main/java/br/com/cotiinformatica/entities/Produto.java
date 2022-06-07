package br.com.cotiinformatica.entities;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Produto {
	
	private Integer idProduto;
	private String nomeProduto;
	private Integer	quantidadeProduto;
	private Double precoProduto;
	private Date dataValidade;
	private String descricaoProduto;
	private Integer idUsuario;
	
	//Associação (TER-1)
	private Usuario usuario;

}
