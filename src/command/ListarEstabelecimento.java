package command;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Estabelecimento;
import service.EstabelecimentoService;


public class ListarEstabelecimento implements Command{

	@Override
	public void executar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		EstabelecimentoService es = new EstabelecimentoService();
		ArrayList<Estabelecimento> lista = null;
		ArrayList<Estabelecimento> listaCategoria = null;

		lista = es.listarEstabelecimento();
		listaCategoria = es.categoriaEstabelecimento();	

		request.setAttribute("listaCategoria", listaCategoria);

		request.setAttribute("lista", lista);

		RequestDispatcher dispatcher = request.getRequestDispatcher("home.jsp");
		dispatcher.forward(request, response);
	}

}
