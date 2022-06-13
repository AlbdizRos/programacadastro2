package br.com.cotiinformatica.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.cotiinformatica.entities.Produto;
import br.com.cotiinformatica.factories.ConnectionFactory;
import br.com.cotiinformatica.helpers.DateHelper;
import br.com.cotiinformatica.interfaces.IProdutoRepository;

public class ProdutoRepository implements IProdutoRepository {

	@Override
	public void create(Produto produto) throws Exception {
		// TODO Auto-generated method stub
		
		// Abrindo conexão com o banco de dados
				Connection connection = ConnectionFactory.getConnection();

				// Executar o comando para gravar a tarefa no banco (INSERT)
				PreparedStatement statement = connection.prepareStatement(
						"insert into produto(nome, quantidade, preco, datavalidade, descricao, idusuario) values(?, ?, ?, ?, ?, ?)");

				statement.setString(1, produto.getNomeProduto());
				statement.setInt(2, produto.getQuantidadeProduto());
				statement.setDouble(3, produto.getPrecoProduto());
				statement.setString(4, DateHelper.formatToString(produto.getDataValidade()));
				statement.setString(5, produto.getDescricaoProduto());
				statement.setInt(6, produto.getIdUsuario());

				statement.execute();
				statement.close();
				
				connection.close();
		
	}

	@Override
	public void update(Produto produto) throws Exception {
		// TODO Auto-generated method stub
		
		// Abrindo conexão com o banco de dados
				Connection connection = ConnectionFactory.getConnection();

				// Executar o comando de UPDATE no banco de dados
				PreparedStatement statement = connection.prepareStatement(
						"update produto set nome=?, quantidade=?, preco=?, datavalidade=?, descricao=? where idproduto=? and idusuario=?");
				statement.setString(1, produto.getNomeProduto());
				statement.setInt(2, produto.getQuantidadeProduto());
				statement.setDouble(3, produto.getPrecoProduto());
				statement.setString(4, DateHelper.formatToString(produto.getDataValidade()));
				statement.setString(5, produto.getDescricaoProduto());
				statement.setInt(6, produto.getIdProduto());
				statement.setInt(7, produto.getIdUsuario());

				statement.execute();
				statement.close();

				connection.close();
		
	}
	
	@Override
	public void update(Integer idUsuario, String senha) throws Exception {
		
		
		Connection connection = ConnectionFactory.getConnection();
		PreparedStatement statement = connection.prepareStatement
		("update usuario set senha=md5(?) where idusuario=?");
		statement.setString(1, senha);
		statement.setInt(2, idUsuario);
		statement.execute();
		statement.close();
		connection.close();
		
	}
		

	@Override
	public void delete(Produto produto) throws Exception {
		// TODO Auto-generated method stub
		
		// Abrindo conexão com o banco de dados
				Connection connection = ConnectionFactory.getConnection();

				// Executar o comando de UPDATE no banco de dados
				PreparedStatement statement = connection.prepareStatement(
						"delete from produto where idproduto=? and idusuario=?");
				statement.setInt(1, produto.getIdProduto());
				statement.setInt(2, produto.getIdUsuario());

				statement.execute();
				statement.close();

				connection.close();		
	}

	@Override
	public List<Produto> findAll(String nome, Integer idUsuario) throws Exception {
		// TODO Auto-generated method stub
		
Connection connection = ConnectionFactory.getConnection();
		
		PreparedStatement statement = connection.prepareStatement("select * from produto where nome like ? and idusuario = ?");
		statement.setString(1, "%" + nome + "%");
		statement.setInt(2, idUsuario);
		ResultSet resultSet = statement.executeQuery();
		
		List<Produto> produtos = new ArrayList<Produto>();
		
		while(resultSet.next()) {
			
			Produto produto= new Produto();
			
			produto.setIdProduto(resultSet.getInt("idproduto"));
			produto.setNomeProduto(resultSet.getString("nome"));
			produto.setQuantidadeProduto(resultSet.getInt("quantidade"));
			produto.setPrecoProduto(resultSet.getDouble("preco"));
			produto.setDataValidade(DateHelper.formatToDate(resultSet.getString("datavalidade")));
			produto.setDescricaoProduto(resultSet.getString("descricao"));
			produto.setIdUsuario(resultSet.getInt("idusuario"));
			
			produtos.add(produto); //adicionando na lista
		}
		
		connection.close();
		return produtos; //retornando a lista
		
	}

	@Override
	public Produto findById(Integer idProduto) throws Exception {
		// TODO Auto-generated method stub
		
Connection connection = ConnectionFactory.getConnection();
		
		PreparedStatement statement = connection.prepareStatement("select * from produto where idproduto = ?");
		statement.setInt(1, idProduto);
		ResultSet resultSet = statement.executeQuery();
		
		Produto produto= null;
		
		if(resultSet.next()) {
			
			produto= new Produto();
			
			produto.setIdProduto(resultSet.getInt("idproduto"));
			produto.setNomeProduto(resultSet.getString("nome"));
			produto.setQuantidadeProduto(resultSet.getInt("quantidade"));
			produto.setPrecoProduto(resultSet.getDouble("preco"));
			produto.setDataValidade(DateHelper.formatToDate(resultSet.getString("datavalidade")));
			produto.setDescricaoProduto(resultSet.getString("descricao"));
			produto.setIdUsuario(resultSet.getInt("idusuario"));			
		}
		connection.close();
		return produto;
	}

}
