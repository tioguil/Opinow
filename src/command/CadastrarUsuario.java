package command;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Usuario;
import service.UsuarioService;

public class CadastrarUsuario implements Command {

	@Override
	public void executar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Usuario usuario = new Usuario();
		SimpleDateFormat formatar = new SimpleDateFormat("yyyy-MM-dd");
		usuario.setNome(request.getParameter("nome"));
		usuario.setCpf(request.getParameter("cpf").replaceAll("[.-]", ""));
		usuario.setApelido(request.getParameter("apelido"));
		usuario.setEmail(request.getParameter("email"));
		usuario.setSenha(request.getParameter("senha"));

		try {
			String dtNasc = request.getParameter("data_nascimento");
			Date date = formatar.parse(dtNasc);
			java.sql.Date sqlData = new java.sql.Date(date.getTime());
			usuario.setDtNasc(sqlData);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		UsuarioService us = new UsuarioService();
		usuario = us.validarLogin(usuario);

		if (usuario.getId() == -1) {
			try {
				if (us.criar(usuario)) {
					usuario = us.validarLogin(usuario);
					HttpSession session = request.getSession();
					session = request.getSession();
					session.setAttribute("usuario", usuario);
					session.setMaxInactiveInterval(600);
					request.setAttribute("BemVindo", "Ficamos contentes por voc&#234; se juntar a essa comunidade!");
					RequestDispatcher view = request.getRequestDispatcher("ServletController?command=ListarEstabelecimento");
					view.forward(request, response);
				} else {
					String mensagem = "Erro ao cadastrar, por favor verifique os campos!";
					request.setAttribute("mensagemCadastro", mensagem);
					request.setAttribute("novoUsuario", usuario);
					RequestDispatcher view = request.getRequestDispatcher("logar.jsp");
					view.forward(request, response);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			String mensagem = "Usuario j&#225; cadastrado";
			request.setAttribute("mensagemCadastro", mensagem);
			request.setAttribute("novoUsuario", usuario);
			RequestDispatcher view = request.getRequestDispatcher("logar.jsp");
			view.forward(request, response);
		}
	}
}