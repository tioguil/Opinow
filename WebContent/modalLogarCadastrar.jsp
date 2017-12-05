<!--  Modal Login  -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<div class="modal fade" id="modLog" role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">
			<form action="ServletController" method="post">
				<div class="modal-header">
					<h3>Login</h3>
					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>
				<div class="modal-body">
					<div class=form-group>
						<input class="form-control" id="email" name="email" placeholder="E-mail" type="email" required="required">
					</div>
					<div class=form-group>
						<input class="form-control" name="password" placeholder="Senha" type="password" required="required">
					</div>
				</div>

				<div class="alert alert-warning" role="alert">
					<strong>Ol&#225;!</strong> Para ter acesso a essa &#225;rea &#233; necess&#225;rio estar logado!
				</div>

				<div class="modal-footer">
					<a href="#" id="modalCadastro" data-toggle="modal" data-target="#signupModal"> N&#227;o possui cadastro?</a>
					<button type="submit" value="LoginUsuario" name="command" class="btn btn-success">Entrar</button>
				</div>
			</form>
		</div>
	</div>
</div>

<!--  Modal Cadastro  -->
<div class="modal fade" data-backdrop="static" id="signupModal" tabindex="-1" role="dialog" aria-labelledby="signupModalLabel" aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title">Cadastre-se</h5>
				<button class="close" type="button" data-dismiss="modal" aria-label="Close">
					<i class="fa fa-times"></i>
				</button>
			</div>
			<c:if test="${not empty mensagemCadastro }">
				<div class="alert alert-danger" role="alert">

					<span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span> <span class="sr-only">Erro:</span>
					${mensagemCadastro}
				</div>
			</c:if>
			<div class="modal-body text-center">
				<form action="ServletController" method="post" id="cadastro">
					<fieldset>
						<div class="form-group">
							<input class="form-control" id="nome" name="nome" placeholder="Nome completo" type="text" value="${novoUsuario.getNome()}" required="required">
						</div>
						<div class="form-group">
							<input class="form-control" id="apelido" name="apelido" placeholder="Apelido" type="text" value="${novoUsuario.getApelido()}" required="required">
						</div>
						<div class="form-group">
							<input class="form-control" id="cpf" name="cpf" placeholder="CPF" type="text" maxLength=14 value="${novoUsuario.getCpf()}" required="required">
						</div>
						<div class="form-group">
							<input class="form-control" id="data_nascimento" name="data_nascimento" placeholder="Data de Nascimento" type="date" value="${novoUsuario.getDtNasc()}" required="required">
						</div>
						<div class="form-group">
							<input class="form-control" id="email" name="email" placeholder="E-mail" type="email" value="${novoUsuario.getEmail()}" required="required">
						</div>
						<div class="form-group">
							<input class="form-control" id="senha" name="senha" placeholder="Senha" type="password" required="required">
						</div>
						<div class="form-group">
							<input class="form-control" id="confirmar_senha" name="confirmar-senha" placeholder="Confirme sua senha" type="password" oninput="validaSenha(this)" required="required">
						</div>
						<div class="form-group">
							<label><input type="checkbox" required="required"><a href="#" id="termo-link" data-toggle="modal" data-target="#termo-uso"> Eu li e concordo com os termos de uso</a></label>
						</div>
					</fieldset>

					<div class="modal-footer">
						<button class="btn btn-secondary" type="button" data-dismiss="modal">Voltar</button>
						<button type="submit" class="btn btn-success" name="command" value="CadastrarUsuario">Cadastrar</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>