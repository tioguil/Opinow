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
	<link href="assets/css/styles.css" rel="stylesheet">
	<link href="assets/css/visualizar-avaliacao.css" rel="stylesheet">
	<link rel="shortcut icon" type="image/x-icon" href="favicon.ico">
</head>
<body onload="initMap()">

	<c:import url="menu.jsp"/>
	<c:import url="termo.jsp" />
	<c:import url="modalLogarCadastrar.jsp"/>

	<div class="container">
		<div class="row">
			<div class="col-lg-8">
				<hr>
				<h1 class="mt-4" id="nome_estabelecimento">${estabelecimento.getNome()}</h1>
				<div class="text-right">
					<c:if test="${not empty usuario}">
						<a href="ServletController?command=CriarAvaliacao&estabelecimento_id=${estabelecimento.getId()}" class="btn btn-outline-info"><i class="fa fa-plus fa-lg"></i> Avalie esse estabelecimento!</a>
					</c:if>
					<c:if test="${empty usuario}">
					<a href="#" data-toggle="modal" data-target="#modLog" class="btn btn-outline-info"><i class="fa fa-plus fa-lg"></i> Avalie esse estabelecimento!</a>
					</c:if>
				</div>
				<hr>
				<p><i>Última atualiza&#231;&#227;o em <fmt:formatDate value="${estabelecimento.getDtAvaliacao()}" pattern="dd/MM/yyyy HH:mm"/></i></p>
				<hr>
				<div class="form-group">
					<label for="endereco_estabelecimento">Endereço:</label>
					<input class="form-control" id="endereco_estabelecimento" name="endereco_estabelecimento" placeholder="Endereço" type="text" disabled="disabled" value="${estabelecimento.getEndereco()}">
				</div>
				<div class="form-group">
					<label for="cidade_estabelecimento">Cidade:</label>
					<input class="form-control" id="cidade_estabelecimento" name="cidade_estabelecimento" placeholder="Cidade" type="text" disabled="disabled" value="${estabelecimento.getCidade()}">
				</div>
				<div class="form-group">
					<label for="categoria_estabelecimento">Categoria:</label>
					<input class="form-control" id="categoria_estabelecimento" name="categoria_estabelecimento" placeholder="Categoria" type="text" disabled="disabled" value="${estabelecimento.getCategoria()}">
				</div>
				<hr>
				<div class="estrelas">
					<label>Média geral:</label> <p style="color:yellow" class="fa fa-star"> </p>
					<fmt:formatNumber type="number" maxFractionDigits="1" value="${estabelecimento.getMedia()}"/>
				</div>
				<hr>
				<h2><i class="fa fa-comments-o"></i> Avalia&#231;&#245;es:</h2>
				<hr>
				<div class="tab-content">
					<div class="table table-striped">
						<br>
						<c:forEach var="avaliacao" items="${listAvaliacao}">
						<div class="media mb-4">
							<div class="media-body">
								<h5 class="mt-0"><i class="fa fa-user-o"></i> ${avaliacao.usuario.getNome()} <small>(${avaliacao.usuario.getApelido()})</small></h5>
								<c:forEach var="categoria" items="${avaliacao.getCategoria()}">
								<div class="estrelas">
									<label><span class="fa fa-caret-right"></span> <strong>Categoria:</strong> ${categoria.getNome() }</label>
									<label><strong>Nota:</strong> ${categoria.getNota() }</label>
								</div>
								</c:forEach>
								<c:if test="${not empty avaliacao.getAvaliacao()}">
									<span class="fa fa-commenting-o"></span> <strong>Coment&#225;rio:</strong>
									<blockquote>
										<p>${avaliacao.getAvaliacao()}</p>
									</blockquote>
								</c:if>
								<i class="fa fa-clock-o" aria-hidden="true"></i> <em>Avaliado em: <fmt:formatDate value="${avaliacao.getDtAvaliacao()}" pattern="dd/MM/yyyy - HH:mm"/></em>
							</div>
						</div>
						<hr>
						</c:forEach>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div id="map"></div>

	<c:import url="footer.jsp"/>

	<script src="assets/components/jquery/jquery.min.js"></script>
	<script src="assets/components/popper/popper.min.js"></script>
	<script src="assets/components/bootstrap/js/bootstrap.min.js"></script>
	<script src="assets/js/visualizar-avaliacao.js"></script>
	<script async defer src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBlsENC1vWHESIy6OxJToosF1ibk3YVz1I&callback=initMap"></script>
</body>
</html>