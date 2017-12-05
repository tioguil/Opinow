package command;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Avaliacao;
import model.Usuario;
import service.AvaliacaoService;

public class ListarAvaliacao implements Command {

	@Override
	public void executar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		AvaliacaoService as = new AvaliacaoService();
		ArrayList<Avaliacao> lista = null;

		HttpSession session = request.getSession();
		Usuario usuario = new Usuario();

		usuario = (Usuario) session.getAttribute("usuario");

		lista = as.listarAvaliacao(usuario.getId());

		request.setAttribute("lista", lista);

		RequestDispatcher dispatcher = request.getRequestDispatcher("minhasAvaliacoes.jsp");
		dispatcher.forward(request, response);
	}
}