<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
	<link href="assets/components/jquery-ui/jquery-ui.min.css" rel="stylesheet">
	<link href="assets/components/jquery-confirm/css/jquery-confirm.min.css" rel="stylesheet">
	<link href="assets/css/styles.css" rel="stylesheet">
	<link href="assets/css/home.css" rel="stylesheet">
	<link rel="shortcut icon" type="image/x-icon" href="favicon.ico">
</head>
<body>
	
	<c:import url="menu.jsp"/>
	<c:import url="modalLogarCadastrar.jsp"/>
	<c:import url="termo.jsp" />
	<div class="container">
		<hr>
		<div class="row" id="lista-estabelecimento">
			<div class="col-md-8">
				<h2><i class="fa fa-list-alt"></i> Mural de Avalia&#231;&#245;es</h2>
				<form action="ServletController" method="post">
					<hr>
					<ul class="list card mb-4">
						<c:forEach var="estabelecimento" items="${lista}">
							<li>
								<div class="list card mb-4">
									<div class="card-body">
										<h4 class="nome-estabelecimento card-title"><i class="fa fa-map-marker"></i> ${estabelecimento.getNome()}</h4>
										<p class="categoria-estabelecimento card-text"><i class="fa fa-caret-right"></i> <strong>Categoria:</strong> ${estabelecimento.getCategoria()}</p>
										<p class="endereco-estabelecimento card-text"><i class="fa fa-location-arrow"></i> <strong>Endereço:</strong> ${estabelecimento.getEndereco()} - ${estabelecimento.getCidade()}</p>
										<p class="media-estabelecimento card-text"><i class="fa fa-bullhorn"></i> <strong>Média geral:</strong> <i class="fa fa-star" style="color:yellow;"></i> <fmt:formatNumber type="number" maxFractionDigits="1" value="${estabelecimento.getMedia()}"/></p>
										<a href="ServletController?command=VisualizarAvaliacao&id=${estabelecimento.getId()}" class="btn btn-primary">Visualizar avalia&#231;&#245;es &rarr;</a>
									</div>
									<div class="card-footer text-muted">
										<i class="fa fa-clock-o"></i> <i>Última atualiza&#231;&#227;o em </i><i><fmt:formatDate value="${estabelecimento.getDtAvaliacao()}" pattern="dd/MM/yyyy HH:mm"/></i>
									</div>
								</div>
							</li>
						</c:forEach>
					</ul>
				</form>
				<ul class="pagination justify-content-center mb-4"></ul>
			</div>

			<div class="col-md-4">
				<div class="card my-4">
					<h5 class="card-header center"><i class="fa fa-search"></i> Pesquisar estabelecimento</h5>
					<div class="card-body">
						<div class="row">
							<div class="col-lg-12">
								<p>
									Utilize o campo abaixo para buscar o estabelecimento:
								</p>
								<div class="input-group">
									<input type="text" class="search form-control" placeholder="Ex.: Av. Vital Brasil" data-toggle="tooltip" data-html="true" data-placement="bottom" title="<em>Você pode buscar pelo <strong>nome</strong>, <strong>categoria</strong> ou <strong>endereço</strong> do estabelecimento</em>">
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- Footer -->
	<c:import url="/footer.jsp"/>

	<script src="assets/components/jquery/jquery.min.js"></script>
	<script src="assets/components/jquery-ui/jquery-ui.min.js"></script>
	<script src="assets/components/popper/popper.min.js"></script>
	<script src="assets/components/bootstrap/js/bootstrap.min.js"></script>
	<script src="assets/components/jquery-confirm/js/jquery-confirm.min.js"></script>
	<script src="assets/components/jquery-mask/js/jquery.mask.min.js"></script>
	<script src="assets/components/list-js/list.min.js"></script>
	<script src="assets/js/scripts.js"></script>
	<script src="assets/js/login.js"></script>
	
	<c:if test="${not empty BemVindo}">
		<script type="text/javascript">
			$(document).ready(function(){ 	
				$.alert({
					title: 'Bem Vindo!',
			    	content: '${BemVindo}',
				});
			});
		</script>
	</c:if>
</body>
</html>