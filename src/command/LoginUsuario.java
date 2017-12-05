package command;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Usuario;
import service.UsuarioService;

public class LoginUsuario implements Command {

	@Override
	public void executar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UsuarioService us = new UsuarioService();
		Usuario usuario = new Usuario();

		usuario.setEmail(request.getParameter("email"));
		usuario = us.validarLogin(usuario);

		if (usuario.getId() == -1 || !usuario.getSenha().equals(request.getParameter("password"))) {
			RequestDispatcher view = request.getRequestDispatcher("ServletController?command=MelhoresAvaliados");
			String mensagem = "Usu&#225;rio e/ou Senha inv&#225;lido(s)";
			request.setAttribute("mensagemLogar", mensagem);
			view.forward(request, response);
		} else {
			HttpSession session = request.getSession();
			session = request.getSession();
			session.setAttribute("usuario", usuario);
			session.setMaxInactiveInterval(1800);
			RequestDispatcher view = request.getRequestDispatcher("ServletController?command=ListarEstabelecimento");
			view.forward(request, response);
		}
	}
}