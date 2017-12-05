package command;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.AvaliacaoService;
import service.EstabelecimentoService;

public class VisualizarAvaliacao implements Command{

	@Override
	public void executar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		AvaliacaoService av = new AvaliacaoService();
		
		EstabelecimentoService estabelecimentoSv = new EstabelecimentoService();
			
		request.setAttribute("estabelecimento", estabelecimentoSv.carregarId(Integer.parseInt(request.getParameter("id"))));
		request.setAttribute("listAvaliacao", av.listarAvaliacaoCategoria(Integer.parseInt(request.getParameter("id"))));
		RequestDispatcher view = request.getRequestDispatcher("visualizarAvaliacao.jsp");
		view.forward(request, response); 
	}
}