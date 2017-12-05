package command;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Estabelecimento;
import service.EstabelecimentoService;

public class PesquisarEstabelecimento implements Command{

	@Override
	public void executar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Estabelecimento estabelecimento = new Estabelecimento();
		EstabelecimentoService es = new EstabelecimentoService();
		
		estabelecimento.setNome(request.getParameter("pesquisa_nome_estabelecimento"));
		
		estabelecimento = es.carregar(estabelecimento);
		
		request.setAttribute("estabelecimento", estabelecimento);
		RequestDispatcher view = request.getRequestDispatcher("novaAvaliacao.jsp");
		view.forward(request, response);
	}
}