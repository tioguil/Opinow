<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
	<div class="container">
		<div class="navbar-header">
			<a class="navbar-brand" href="./"><img src="./assets/img/logo-opinow-vector.svg" alt="OpiNow"></a>
		</div>

		<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>

		<div class="collapse navbar-collapse" id="navbarResponsive">
			<ul class="navbar-nav">
				<li class="nav-item">
					<a class="nav-link" href="ServletController?command=ListarEstabelecimento">Home</a>
				</li>
				<c:if test="${not empty usuario}">
					<li class="nav-item">
						<a class="nav-link" href="ServletController?command=ListarAvaliacao">Minhas Avalia&#231;&#245;es</a>
					</li>
					<li class="nav-item">
						<a class="nav-link" href="novaAvaliacao.jsp">Nova Avalia&#231;&#227;o</a>
					</li>
				</c:if>
				<c:if test="${empty usuario}">
					<li class="nav-item">
						<a href="#" class="nav-link" data-toggle="modal" data-target="#modLog" >Minhas Avalia&#231;&#245;es</a>
					</li>
					<li class="nav-item">
						<a href="#" class="nav-link" data-toggle="modal" data-target="#modLog">Nova Avalia&#231;&#227;o</a>
					</li>
				</c:if>
			</ul>
			<c:if test="${not empty usuario}">
				<ul class="navbar-nav ml-auto">
					<li class="nav-item dropdown">
						<a class="nav-link dropdown-toggle" data-toggle="dropdown" href="#"> ${usuario.getApelido()} <i class="fa fa-user fa-fw"></i></a>
						<ul class="dropdown-menu dropdown-user">
							<li class="nav-item">
								<a class="nav-link" href="contato.jsp"><i class="fa fa-envelope"></i> Envie seu feedback</a>
							</li>
							<hr>
							<li class="nav-item">
								<a class="nav-link" href="editar_perfil.jsp"><i class="fa fa-user fa-fw"></i>Editar Perfil</a>
							</li>
							<hr>
							<li class="nav-item">
								<a class="nav-link" href="#" data-toggle="modal" data-target="#logoutModal"><i class="fa fa-sign-out fa-fw"></i> Sair</a>
							</li>
						</ul>
					</li>
				</ul>
			</c:if>
		</div>
	</div>
</nav>

<!-- Modal -->
<div class="modal fade" id="logoutModal" tabindex="-1" role="dialog" aria-labelledby="logoutModalLabel" aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="logoutModalLabel"></h5>
				<button class="close" type="button" data-dismiss="modal" aria-label="Close">
					<i class="fa fa-times"></i>
				</button>
			</div>
			<div class="modal-body text-center">
				<h2><i class="fa fa-exclamation-triangle fa-lg text-warning"></i></h2>
				Deseja realmente sair do sistema?
			</div>
			<div class="modal-footer">
				<button class="btn btn-secondary" type="button" data-dismiss="modal">Cancelar</button>
				<form action="ServletController" method="post">
					<button class="btn btn-primary" type ="submit" name="command" value="LogOut">Sair</button>
				</form>
			</div>
		</div>
	</div>
</div>