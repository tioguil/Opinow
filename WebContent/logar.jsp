<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>OpiNow - Avalia&#231;&#227;o de Acessibilidades em Estabelecimento</title>

	<link href="./assets/components/bootstrap/css/bootstrap.min.css" rel="stylesheet">
	<link href="./assets/components/font-awesome/css/font-awesome.min.css" rel="stylesheet">
	<link href="./assets/components/jquery-confirm/css/jquery-confirm.min.css" rel="stylesheet">
	<link href="./assets/css/styles.css" rel="stylesheet">
	<link href="./assets/css/index.css" rel="stylesheet">
	<link href="https://fonts.googleapis.com/css?family=Satisfy" rel="stylesheet">
	<link rel="shortcut icon" type="image/x-icon" href="./favicon.ico">
</head>
<body>

	<c:import url="termo.jsp" />

	<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
		<div class="container">
			<div class="navbar-header">
				<a class="navbar-brand" href="./"><img src="./assets/img/logo-opinow-vector.svg" alt="OpiNow"></a>
			</div>

			<c:if test="${empty usuario}">
				<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
					<i class="fa fa-lg fa-sign-in"></i> Acessar
				</button>

				<div class="collapse navbar-collapse" id="navbarResponsive">
					<div class="navbar-nav ml-auto">
						<div class="nav-item form-inline">
							<form action="ServletController" method="post">
								<input class="form-control" type="text" placeholder="E-mail" name="email" type="email" required>
								<input class="form-control" placeholder="Senha" name="password" type="password" required>
								<button type="submit" class="btn btn-success " name="command" value="LoginUsuario">Entrar</button>
							</form>
							<div class="vertical-line"></div>
							<button class="btn btn-secondary" data-toggle="modal" data-target="#signupModal" id="abrirCadastro">Cadastre-se</button>
						</div>
						<div class="form-inline">
							<a href="#" data-toggle="modal" data-target="#forgotPasswordModal" id="abrirSenha">Esqueceu a senha?</a>
						</div>
					</div>
				</div>
			</c:if>

			<c:if test="${not empty usuario}">
				<div class="navbar-nav ml-auto">
					<div class="nav-item form-inline">
						<a href="ServletController?command=ListarEstabelecimento" class="btn btn-acessar"><i class="fa fa-lg fa-sign-in"></i> Acessar</a>
					</div>
				</div>
			</c:if>

		</div>
	</nav>

	<div class="container-fluid bgded overlay content">
		<div class="row fill-height">
			<div class="col-md-12 header-home">
				<div class="text-center">
					<h5 class="text-center header-text">
						"As melhores avaliações você encontra aqui,<br>relate a sua experiência com apenas um click" 
					</h5>
					<a href="ServletController?command=ListarEstabelecimento" class="btn btn-primary btn-lg btn-experimente">Experimente agora!</a>
				</div>
				<br> 
				
				<div>
					<strong class="btn-compartilhe">Compartilhe!</strong><br>
					<a class="fa fa-facebook-square social-media" href="https://www.facebook.com/sharer/sharer.php?u=http://opinow.azurewebsites.net/" target="_blank"></a> 
					<a class="fa fa-twitter-square social-media" href="https://twitter.com/share?text=%22Acesse%20o%20Opinow%20Avalia&#231;&#227;o%20de%20Acessibilidades%20em%20Estabelecimentos%22&url=http://opinow.azurewebsites.net/" target="_blank"></a>
					<a class="fa fa-linkedin-square social-media" href="https://www.linkedin.com/shareArticle?mini=true&url=http://opinow.azurewebsites.net/&title=Opinow-%20Avalia&#231;&#227;o%20de%20Acessibilidade%20em%20Estabelecimento" target="_blank"></a>
				</div>
			</div>
			<div class="col-md-12 melhores-estabelecimentos">
				<h2>Os melhores estabelecimentos</h2>
				<div class="flexslider">
					<ul class="slides">
						<c:forEach var="estabelecimento" items="${lista}">
							<li>
								<article class="content-melhores-estabelecimentos">
									<p>Categoria: ${estabelecimento.getCategoria()}</p>
									<strong class="mobile"><a href="ServletController?command=VisualizarAvaliacao&id=${estabelecimento.getId()}">${estabelecimento.getNome()}</a></strong>
									<p>${estabelecimento.getEndereco()}</p>
									<i class="fa fa-star"></i> <fmt:formatNumber type="number" maxFractionDigits="1" value="${estabelecimento.getMedia()}" />
									<i class="fa fa-star"></i>
								</article>
							</li>
						</c:forEach>
					</ul>
				</div>
			</div>
		</div>
	</div>

	<!-- Modal -->
	<c:import url="modalLogarCadastrar.jsp"/>
	<!-- Modal -->
	<div class="modal fade" id="forgotPasswordModal" tabindex="-1" role="dialog" aria-labelledby="forgotPasswordModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title">Recuperar senha</h5>
					<button class="close" type="button" data-dismiss="modal" aria-label="Close">
						<i class="fa fa-times"></i>
					</button>
				</div>
				<div class="modal-body text-center">
					<form action="ServletController" method="post">
						<fieldset>
							<div class="form-group">
								<input class="form-control" id="email" name="email" placeholder="E-mail" type="text" required="required">
							</div>
							<div class="form-group">
								<input class="form-control" id="cpf-recuperar" name="cpf" placeholder="CPF" type="text" required="required">
							</div>
							<div class="clearfix"></div>
						</fieldset>
						<button class="btn btn-success" type="submit" value="RecuperarSenha" name="command">Recuperar</button>
					</form>
					<br>
				</div>
				<div class="modal-footer">
					<button class="btn btn-secondary" type="button" data-dismiss="modal">Voltar</button>
				</div>
			</div>
		</div>
	</div>

	<script src="./assets/components/jquery/jquery.min.js"></script>
	<script src="./assets/components/jquery/jquery.flexslider-min.js"></script>
	<script src="./assets/components/popper/popper.min.js"></script>
	<script src="./assets/components/bootstrap/js/bootstrap.min.js"></script>
	<script src="./assets/components/jquery-confirm/js/jquery-confirm.min.js"></script>
	<script src="./assets/components/jquery-mask/js/jquery.mask.min.js"></script>
	<script src="./assets/js/index.js"></script>
	<script src="./assets/js/login.js"></script>
	<script type="text/javascript">
		$('#cpf-recuperar').mask('000.000.000-00');
		<c:if test="${not empty mensagemLogar}">
			$(document).ready(function(){ 	
				$.alert({
					theme: 'dark',
					title: 'Erro ao logar!',
					content: '${mensagemLogar}',
				});
			});
		</c:if>
		<c:if test="${not empty mensagemRecuperar}">
			$(document).ready(function(){ 	
				$.alert({
					theme: 'dark',
					title: 'Recupera&#231;&#227;o de Senha!',
					content: '${mensagemRecuperar}',
				});
			});
		</c:if>
		<c:if test="${not empty mensagemCadastro}">
			window.setTimeout(function(){
				document.getElementById("abrirCadastro").click();
				}, 100);
		</c:if>
		<c:if test="${not empty mensagemRecuperar}">
			window.setTimeout(function(){
				document.getElementById("abrirSenha").click();
			}, 100);
		</c:if>
	</script>
</body>
</html>