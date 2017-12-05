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

	<link href="assets/components/bootstrap/css/bootstrap.min.css" rel="stylesheet">
	<link href="assets/components/font-awesome/css/font-awesome.min.css" rel="stylesheet">
	<link href="assets/css/styles.css" rel="stylesheet">
	<link rel="shortcut icon" type="image/x-icon" href="./favicon.ico">
</head>
<body>
	<c:import url="menu.jsp" />
	<div class="container">
		<div class="row">
			<div class="col-lg-12">
				<hr>
				<h2 class="d-inline-block">Editar Perfil</h2>

				<div class="clearfix"></div>

				<c:if test="${not empty sucesso }">
					<div class="alert alert-success" role="alert">${sucesso}</div>
				</c:if>

				<c:if test="${not empty erro }">
					<div class="alert alert-danger" role="alert">${erro}</div>
				</c:if>

				<hr>
				<form action="ServletController" method="post" id="cadastro"
					style="max-width: 650px; margin: 0 auto;">
					<fieldset>
						<h4>Seus dados</h4>
						<div class="form-group">
							<label for="nome">Nome:</label> <input class="form-control"
								id="nome" name="nome" placeholder="Nome"
								value="${usuario.getNome()}" type="text">
						</div>
						<div class="form-group">
							<label for="apelido">Apelido:</label> <input class="form-control"
								id="apelido" name="apelido" placeholder="Usu&#225;rio"
								value="${usuario.getApelido()}" type="text">
						</div>
						<div class="form-group">
							<label for="email">E-mail:</label> <input class="form-control"
								id="email" name="email" placeholder="E-mail" type="email"
								disabled="disabled" value="${usuario.getEmail()}">
						</div>
						<div class="form-group">
							<label for="cpf">CPF:</label> <input class="form-control"
								id="cpf" name="cpf" type="text" value="${usuario.getCpf()}"
								disabled="disabled">
						</div>
						<div class="form-group">
							<label class="pull-left" for="data_nascimento">Data de Nascimento:</label>
							<input class="form-control" type="text" id="data_nascimento" value="<fmt:formatDate value="${usuario.getDtNasc()}" pattern="dd/MM/yyyy" />" name="data_nascimento" disabled="disabled">
						</div>
						<hr>
						<div id="accordion" role="tablist" aria-multiselectable="true">
							<div class="card">
								<a data-toggle="collapse" data-parent="#accordion" href="#alterar-senha" aria-expanded="true" aria-controls="alterar-senha">
									<div class="card-header" role="tab" id="headingOne">
										<h5 class="mb-0">Alterar Senha</h5>
									</div>
								</a>
							</div>

							<div id="alterar-senha" class="collapse hidden" role="tabpanel" aria-labelledby="headingOne">
								<div class="card-block">
									<div class="form-group">
										<input class="form-control" id="senha-atual" name="senha" placeholder="Senha atual" type="password">
									</div>
									<div class="form-group">
										<input class="form-control" id="senha" name="nova_senha" placeholder="Nova senha" type="password">
									</div>
									<div class="form-group">
										<input class="form-control" id="confirmar-senha" name="confirmar-senha" placeholder="Confirme a nova senha" type="password" oninput="validaSenha(this)">
									</div>
								</div>
							</div>
						</div>
					</fieldset>

					<br>
					<div>
						<button type="submit" class="pull-right btn btn-primary" name="command" value="EditarUsuario">Salvar</button>
					</div>
				</form>
			</div>
		</div>
		<hr>
	</div>
	<c:import url="footer.jsp" />

	<script src="assets/components/jquery/jquery.min.js"></script>
	<script src="assets/components/popper/popper.min.js"></script>
	<script src="assets/components/bootstrap/js/bootstrap.min.js"></script>
	<script src="assets/components/jquery-mask/js/jquery.mask.min.js"></script>
	<script src="assets/js/login.js"></script>
	<script src="assets/js/editar-perfil.js"></script>
</body>
</html>