<%@ page language="java" contentType="text/html; charset=Utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>OpiNow - Avalia&#231;&#227;o de Acessibilidades em Estabelecimento</title>

	<link href="assets/components/bootstrap/css/bootstrap.min.css" rel="stylesheet">
	<link href="assets/components/font-awesome/css/font-awesome.min.css" rel="stylesheet">
	<link href="assets/components/DataTables/datatables.min.css" rel="stylesheet">
	<link href="assets/css/styles.css" rel="stylesheet">
	<link href="assets/css/avaliacoes.css" rel="stylesheet">
	<link rel="shortcut icon" type="image/x-icon" href="../favicon.ico">

</head>
<body>
	<!-- Menu -->
	<c:import url="/menu.jsp"/>

	<div class="container">
		<div class="row">
			<div class="col-lg-12">
				<hr>
				<h2>Minhas Avalia&#231;&#245;es</h2>
				<hr>
				<form method="GET" action="ServletController">
					<table id="list-minhas-avaliacoes" class="table table-striped table-bordered table-hover" style="width: 100%;">
						<thead>
							<tr>
								<th>Avaliado em:</th>
								<th>Estabelecimento:</th>
								<th>Tipo do Estabelecimento:</th>
								<th>Categoria de Avalia&#231;&#227;o:</th>
								<th>Nota:</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="avaliacao" items="${lista}">
								<tr data-href="ServletController?command=VisualizarAvaliacao&id=${avaliacao.getIdEstabelecimento()}">
									<td><fmt:formatDate value="${avaliacao.getDtAvaliacao()}" pattern="dd/MM/yyyy HH:mm"/></td>
									<td>${avaliacao.getNome_estabelecimento()}</td>
									<td>${avaliacao.getCategoria_estabelecimento()}</td>
									<td>${avaliacao.getTipo_categoria()}</td>
									<td>${avaliacao.getNota()}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</form>
				<hr>	
			</div>
		</div>
	</div>

	<!-- Footer -->
	<c:import url="/footer.jsp"/>

	<script src="assets/components/jquery/jquery.min.js"></script>
	<script src="assets/components/popper/popper.min.js"></script>
	<script src="assets/components/bootstrap/js/bootstrap.min.js"></script>
	<script src="assets/components/DataTables/datatables.min.js"></script>
	<script src="assets/components/DataTables/sorting/date-euro.js"></script>
	<script src="assets/js/avaliacoes.js"></script>
</body>
</html>