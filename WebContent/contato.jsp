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
				<h2 class="d-inline-block"><i class="fa fa-envelope-o"></i> Envie seu feedback</h2>

				<div class="clearfix"></div>

				<c:if test="${not empty mensagem}">
					<div class="alert alert-success" role="alert">${mensagem}</div>
				</c:if>
				<hr>
				<form action="ServletController" method="post" id="contato" style="max-width: 650px; margin: 0 auto;">
					<fieldset>
						<div class="form-group">
							<label for="assunto"><strong>Assunto:</strong></label>
							<input class="form-control" id="assunto" name="assunto" type="text" required="required">
						</div>
						<div class="form-group">
							<label for="descricao"><strong>Descrição:</strong></label>
							<textarea class="form-control" id="descricao" name="descricao" style="overflow-y:auto;" rows="6" required="required"></textarea>
						</div>
					</fieldset>
					<br>
					<div>
						<button type="submit" class="pull-right btn btn-primary" name="command" value="Contato"><i class="fa fa-share"></i> Enviar mensagem</button>
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
</body>
</html>