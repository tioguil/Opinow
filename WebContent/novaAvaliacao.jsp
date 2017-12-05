<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
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
	<link href="assets/css/styles.css" rel="stylesheet">
	<link href="assets/css/estabelecimento.css" rel="stylesheet">
	<link rel="shortcut icon" type="image/x-icon" href="favicon.ico">
	
</head>
<body>
	<!-- Menu -->
	<c:import url="/menu.jsp"/>
	<div class="container">
		<form method="POST" action="ServletController">
			<hr>
			<h2 class="d-inline-block"><i class="fa fa-search"></i> Passo 1: Pesquisar estabelecimento</h2>
			<c:if test="${not empty success }">
				<div class="alert alert-success" role="alert">${success}</div>
			</c:if>
			<c:if test="${not empty error }">
				<div class="alert alert-danger" role="alert">${error}</div>
			</c:if>
			<hr>
			<div class="row">
				<div class="col-lg-6">
					<div class="text-center">
						<div class="form-group buscar-estabelecimento">
							<div class="form-group row">
								<div class="col-lg-12 d-flex">
									<div class="input-group">
										<span class="input-group-addon" id="search-addon"><i class="fa fa-search"></i></span>
										<input class="form-control" aria-describedby="search-addon" id="value-search" type="text" placeholder="Ex: Universidade São Judas Tadeu">
									</div>  
									<button class="btn btn-secondary btn-clear-estabelecimento" type="reset"><i class="fa fa-eraser"></i> Limpar</button>
								</div>
								<div class="col-lg-12">
									<p id="result-list-title">Selecione o estabelecimento abaixo:</p>
									<div id="result-list">
										<table class="table table-striped table-hover" style="height:50px">
											<tbody id="result"></tbody>
										</table>
									</div>
								</div>
							</div>
							<p class="pull-right novo-estabelecimento text-right"><em>N&#227;o encontrou o estabelecimento?<br><a href="#">Cadastre agora! <i class="fa fa-plus-circle fa-lg"></i></a></em></p>
						</div>
					</div>
				</div>
				<div class="col-lg-6">
					<div class="form-group">
						<input class="form-control" id="nome_estabelecimento" name="nome_estabelecimento" placeholder="Nome do estabelecimento" type="text" readonly="true" required="required">
					</div>
					<div class="form-group">
						<input class="form-control" id="endereco_estabelecimento" name="endereco_estabelecimento" placeholder="Endereço" type="text" readonly="true" required="required">
					</div>
					<div class="form-group">
						<input class="form-control" id="cidade_estabelecimento" name="cidade_estabelecimento" placeholder="Cidade" type="text" readonly="true" required="required">
					</div>
					<div class="form-group">
						<input class="form-control" id="categoria_estabelecimento" name="categoria_estabelecimento" placeholder="Categoria" type="text" readonly="true" required="required">
					</div>
				</div>
				<hr>
				<div class="col-lg-12">
					<h2><i class="fa fa-plus"></i> Passo 2: Faça sua avalia&#231;&#227;o</h2>
					<div class="clearfix"></div>
					<hr>
					<div class="estabelecimento-empty text-center">
						<h1><i class="fa fa-exclamation-circle fa-lg"></i></h1>
						<h5>Selecione primeiro um estabelecimento para registrar a sua avalia&#231;&#227;o.</h5>
					</div>
					<fieldset class="nova-avaliacao">
						<div class="form-group">
							<select class="custom-select w-10" id="categoriaAval" name="categoriaAval" required="required">
								<option value="" selected="selected" disabled="disabled">Selecione a categoria de avalia&#231;&#227;o</option>
								<option value="categoriaCadeirante">Acesso para cadeirantes</option>
								<option value="categoriaPiso">Sinaliza&#231;&#227;o de piso para deficientes visuais</option>
								<option value="categoriaBraile">Instru&#231;&#245;es para deficientes visuais em braile</option>
								<option value="categoriaSanitario">Sanit&#225;rios para cadeirantes</option>
							</select>	
						</div>
						<div id="listas">
						</div> 
						<div class="form-group">
							<label for="comment">Coment&#225;rio <small><em>(max 140 caracteres)</em></small>:</label>
							<textarea class="form-control" maxlength="140" rows="5" id="comentario_estabelecimento" placeholder="Digite um texto" name="comentario"></textarea>
						</div>
						<button class="btn-primary btn btn-success pull-right" type="submit" name="command" value="CriarAvaliacao">Concluir</button>
					</fieldset>
					<hr>
				</div>
			</div>
		</form>
	</div>
	<!-- Footer -->
    <c:import url="/footer.jsp"/>
	<!-- Scripts -->
	<script src="assets/components/jquery/jquery.min.js"></script>
	<script src="assets/components/jquery-ui/jquery-ui.min.js"></script>
	<script src="assets/components/popper/popper.min.js"></script>
	<script src="assets/components/bootstrap/js/bootstrap.min.js"></script>
	<script src="assets/js/estabelecimento.js"></script>
	<c:if test="${not empty estabelecimento}">
		<script>
			$('.estabelecimento-empty').hide();
			$('.nova-avaliacao').show();
			$('#nome_estabelecimento').attr('readonly', true).val('${estabelecimento.getNome()}');
			$('#endereco_estabelecimento').attr('readonly', true).val('${estabelecimento.getEndereco()}');
			$('#cidade_estabelecimento').attr('readonly', true).val('${estabelecimento.getCidade()}');
			$('#categoria_estabelecimento').attr('readonly', true).val('${estabelecimento.getCategoria()}');
		</script>
	</c:if>
</body>
</html>