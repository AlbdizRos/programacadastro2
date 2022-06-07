<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Consulta de produtos</title>

	<!-- folhas de estilo CSS -->
	<link rel="stylesheet" href="resources/css/bootstrap.min.css" />

</head>
<body class="bg-light">

	<!-- menu do sistema -->
	<jsp:include page="/WEB-INF/components/menu.jsp"></jsp:include>
	
	<!-- mensagens do sistema -->
<jsp:include page="/WEB-INF/components/mensagens.jsp"></jsp:include>

	
	<div class="container mt-3">
	
		<h5>Consulta de produtos</h5>
		<p>Informe o nome do produto desejado para realizar a consulta.</p>
		<hr/>
		
		<form action="consultar-produtos" method="post">
		
			<div class="row mb-3">
				<div class="col-md-3">
					<label>Nome do produto:</label>
					<form:input path="model.nomeProduto" id="nomeProduto" name="nomeProduto" type="text" class="form-control" placeholder="Digite aqui"/>
				</div>
			</div>
			
			<div class="row mb-3">
				<div class="col-md-3">
					<input type="submit" value="Pesquisar produtos" class="btn btn-success"/>
				</div>
			</div>
		
		</form>
		
		<c:if test="${produtos.size() > 0}">
		
			<table class="table table-hover table-sm mt-3">
				<thead>
					<tr>
						<th>Nome do produto</th>
						<th>Quantidade</th>
						<th>Preço</th>
						<th>Data de Validade</th>
						<th>Descrição</th>
						<th>Operações</th>
					</tr>
				</thead>
				<tbody>
				
					<c:forEach items="${produtos}" var="t">
						<tr>
							<td>${t.nomeProduto}</td>
							<td>${t.quantidadeProduto}</td>
							<td>${t.precoProduto}</td>
							<td><fmt:formatDate value="${t.dataValidade}" pattern="dd/MM/yyyy"/></td>
							<td>${t.descricaoProduto}</td>
							
							<td>
								<a href="/programacadastro2/produtos-edicao?id=${t.idProduto}" 
									class="btn btn-primary btn-sm">
									Editar
								</a>
							</td>	
							<td>
								<a href="/programacadastro2/excluirproduto?idProduto=${t.idProduto}" 
									class="btn btn-danger btn-sm"
									onclick="return confirm('Deseja excluir o produto selecionado?');">
									Excluir
								</a>
								
							</td>
							
							
						</tr>
						
					</c:forEach>				
							
				</tbody>
				<tfoot>
					<tr>
						<td class="6">
							Quantidade de registros: ${produtos.size()}
						</td>
					</tr>
				</tfoot>	
			</table>	
		
		</c:if>
	
	</div>

	<!-- arquivos de extensão javascript -->
	<script src="resources/js/bootstrap.min.js"></script>

</body>
</html>