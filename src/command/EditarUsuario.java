package command;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Usuario;
import service.UsuarioService;

public class EditarUsuario implements Command {

	@Override
	public void executar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UsuarioService us = new UsuarioService();

		HttpSession session = request.getSession();

		Usuario usuario = new Usuario();
		usuario = (Usuario) session.getAttribute("usuario");

		if((request.getParameter("senha") == null || request.getParameter("senha") == "" ) && (request.getParameter("confirma-senha") == null || request.getParameter("confirma-senha") == "") && (request.getParameter("nova_senha") == null || request.getParameter("nova_senha") == "" )) {
			usuario.setId(usuario.getId());
			usuario.setNome(request.getParameter("nome"));
			usuario.setApelido(request.getParameter("apelido"));
			usuario.setSenha(usuario.getSenha());

			us.atualizar(usuario);
			
			String mensagem = "Usu&#225;rio atualizado com sucesso!";
			request.setAttribute("sucesso", mensagem);
			session.setAttribute("usuario", usuario);
			RequestDispatcher view = request.getRequestDispatcher("editar_perfil.jsp");
			view.forward(request, response);	
		} else if (usuario.getSenha().equals(request.getParameter("senha")) && request.getParameter("nova_senha").equals(request.getParameter("confirmar-senha"))) {
			if(request.getParameter("nova_senha") == "" || request.getParameter("confirmar-senha") == "") {
				String mensagem = "Favor preencher todos os campos de senha.";
				request.setAttribute("erro", mensagem);
				RequestDispatcher view = request.getRequestDispatcher("editar_perfil.jsp");
				view.forward(request, response);
				return;
			}

			usuario.setId(usuario.getId());
			usuario.setNome(request.getParameter("nome"));
			usuario.setApelido(request.getParameter("apelido"));
			usuario.setSenha(request.getParameter("confirmar-senha"));

			us.atualizar(usuario);
		
			String mensagem = "Usu&#225;rio atualizado com sucesso!";
			request.setAttribute("sucesso", mensagem);
			session.setAttribute("usuario", usuario);
			RequestDispatcher view = request.getRequestDispatcher("editar_perfil.jsp");
			view.forward(request, response);
		} else if (!request.getParameter("nova_senha").equals(request.getParameter("confirmar-senha"))) {
			String mensagem = "Nova senha e confirma&#231;&#227;o n&#227;o conferem!";
			request.setAttribute("erro", mensagem);
			RequestDispatcher view = request.getRequestDispatcher("editar_perfil.jsp");
			view.forward(request, response);
		} else {
			String mensagem = "Senha inv&#225;lida!";
			request.setAttribute("erro", mensagem);
			RequestDispatcher view = request.getRequestDispatcher("editar_perfil.jsp");
			view.forward(request, response);
		}
	}
}