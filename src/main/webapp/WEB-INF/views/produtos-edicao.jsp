<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
    
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Edição de produto</title>

	<!-- folhas de estilo CSS -->
	<link rel="stylesheet" href="resources/css/bootstrap.min.css" />
	
	<!-- estilos para o jquery validation -->
	<style>
		label.error { color: red; }
		input.error, select.error, textarea.error { border: 2px solid red; }
	</style>

</head>
<body>

	<!-- menu do sistema -->
	<jsp:include page="/WEB-INF/components/menu.jsp"></jsp:include>
	
	<!-- mensagens do sistema -->
	<jsp:include page="/WEB-INF/components/mensagens.jsp"></jsp:include>
	
	<div class="container mt-3">
	
		<h5>Edição de tarefa</h5>
		<p>Utilize o formulário abaixo para atualizar os dados do produto.</p>
		<hr/>
		
		<form id="formEdicao" action="atualizar-produto" method="post">
		
			<!-- campo oculto -->
			<form:input path="model.idProduto" type="hidden"/>			
			
			<div class="row mb-3">
				<div class="col-md-6">
					<label>Nome do produto:</label>
					<form:input path="model.nomeProduto" type="text" id="nomeProduto" name="nomeProduto" class="form-control" placeholder="Digite aqui"/>
				</div>
				<div class="col-md-2">
					<label>Quantidade:</label>
					<form:input path="model.quantidadeProduto" type="number" id="quantidadeProduto" name="quantidadeProduto" class="form-control"/>
				</div>
				<div class="col-md-2">
					<label>Preço:</label>
					<form:input path="model.precoProduto" type="number" step="0.01"  id="precoProduto" name="precoProduto"  class="form-control"/>
				</div>
				
				<div class="col-md-2">
					<label>Data de validade:</label>
					<form:input path="model.dataValidade" type="date"  id="dataValidade" name="dataValidade" class="form-control"/>
				</div>
			</div>
			
			<div class="row mb-3">
				<div class="col-md-12">
					<label>Descrição do produto:</label>
					<form:textarea path="model.descricaoProduto" class="form-control" id="descricaoProduto" name="descricaoProduto" rows="4"></form:textarea>
				</div>
			</div>
			
			<div class="row mb-3">
				<div class="col-md-12">
					<input type="submit" value="Salvar Alterações" class="btn btn-primary"/>
				</div>			
			</div>
			
		</form>
	
	</div>
	
	<!-- arquivos de extensão javascript -->
	<script src="resources/js/bootstrap.min.js"></script>
	
	<!-- biblioteca do jquery -->
	<script src="resources/js/jquery-3.6.0.min.js"></script>
	
	<!-- biblioteca do jquery validation -->
	<script src="resources/js/jquery.validate.min.js"></script>
	<script src="resources/js/additional-methods.min.js"></script>
	<script src="resources/js/messages_pt_BR.min.js"></script>
	
	<script>
	
		//função para inicializar o código JavaScript (JQuery)
		$(document).ready(function(){
			
			$("#formEdicao").validate({
				rules : {
					'nomeProduto' : { required : true },
					'quantidadeProduto' : { required : true },
					'precoProduto' : { required : true },
					'dataValidade' : { required : true },
					'descricaoProduto' : { required : true }
				}
			});			
		});
	
	</script>
	

</body>
</html>




