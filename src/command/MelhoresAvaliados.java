package command;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Estabelecimento;
import service.EstabelecimentoService;

public class MelhoresAvaliados implements Command {

	@Override
	public void executar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		EstabelecimentoService estabelecimento = new EstabelecimentoService ();
		ArrayList<Estabelecimento> lista = new ArrayList<>();
		
		lista = estabelecimento.tresMelhores();
		
		request.setAttribute("lista", lista);
		RequestDispatcher dispatcher = request.getRequestDispatcher("logar.jsp");
		dispatcher.forward(request, response);	
	}
}