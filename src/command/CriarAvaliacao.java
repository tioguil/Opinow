package command;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Avaliacao;
import model.CategoriaAvaliacao;
import model.Estabelecimento;
import model.Usuario;
import service.AvaliacaoService;
import service.CategoriaAvaliacaoService;
import service.EstabelecimentoService;

public class CriarAvaliacao implements Command {

	@Override
	public void executar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Estabelecimento estabelecimento = new Estabelecimento();
		EstabelecimentoService service = new EstabelecimentoService();

		estabelecimento.setNome(request.getParameter("nome_estabelecimento"));
		estabelecimento.setCidade(request.getParameter("cidade_estabelecimento"));
		estabelecimento.setEndereco(request.getParameter("endereco_estabelecimento"));
		estabelecimento.setCategoria(request.getParameter("categoria_estabelecimento"));
		
		if(request.getParameter("estabelecimento_id") != null) {
			request.setAttribute("estabelecimento", service.carregarId(Integer.parseInt(request.getParameter("estabelecimento_id"))));
			RequestDispatcher view = request.getRequestDispatcher("novaAvaliacao.jsp");
			view.forward(request, response);
		} else if(request.getParameter("categoriaCadeirante") == null && request.getParameter("categoriaPiso") == null && request.getParameter("categoriaBraile") == null && request.getParameter("categoriaSanitario") == null || request.getParameter("estabelecimento_id") == "") {
			request.setAttribute("error", "&#201; necess&#225;rio selecionar pelo menos uma categoria para realizar a avalia&#231;&#227;o.");
			RequestDispatcher view = request.getRequestDispatcher("novaAvaliacao.jsp");
			view.forward(request, response);
		} else if(request.getParameter("comentario").length() > 140){
			request.setAttribute("error", "O coment&#225;rio precisa ter no m&#225;ximo 140 caracteres!");
			RequestDispatcher view = request.getRequestDispatcher("novaAvaliacao.jsp");
			view.forward(request, response);
		} else {
			if (service.compararEstabelecimento(estabelecimento))
				estabelecimento = service.carregar(estabelecimento);
			else
				estabelecimento = service.criar(estabelecimento);

			Avaliacao av = new Avaliacao();

			HttpSession session = request.getSession();
			av.setUsuario((Usuario) session.getAttribute("usuario"));
			av.setEstabelecimento(estabelecimento);
			av.setAvaliacao(request.getParameter("comentario"));

			AvaliacaoService as = new AvaliacaoService();
			av = as.incluir(av);

			if (request.getParameter("categoriaCadeirante") != null) {
				CategoriaAvaliacao catego = new CategoriaAvaliacao();
				catego.setNome("Acesso para cadeirantes");
				catego.setNota(Integer.parseInt(request.getParameter("categoriaCadeirante")));
				catego.setIdAvaliacao(av.getId());
				CategoriaAvaliacaoService ser = new CategoriaAvaliacaoService();
				try {
					ser.incluir(catego);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (request.getParameter("categoriaPiso") != null) {
				CategoriaAvaliacao catego = new CategoriaAvaliacao();
				catego.setNome("Sinalização de piso para deficientes visuais");
				catego.setNota(Integer.parseInt(request.getParameter("categoriaPiso")));
				catego.setIdAvaliacao(av.getId());
				CategoriaAvaliacaoService ser = new CategoriaAvaliacaoService();
				try {
					ser.incluir(catego);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (request.getParameter("categoriaBraile") != null) {
				CategoriaAvaliacao catego = new CategoriaAvaliacao();
				catego.setNome("Instrução para deficientes visuais em braile");
				catego.setNota(Integer.parseInt(request.getParameter("categoriaBraile")));
				catego.setIdAvaliacao(av.getId());
				CategoriaAvaliacaoService ser = new CategoriaAvaliacaoService();
				try {
					ser.incluir(catego);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (request.getParameter("categoriaSanitario") != null) {
				CategoriaAvaliacao catego = new CategoriaAvaliacao();
				catego.setNome("Sanitários para cadeirantes");
				catego.setNota(Integer.parseInt(request.getParameter("categoriaSanitario")));
				catego.setIdAvaliacao(av.getId());
				CategoriaAvaliacaoService ser = new CategoriaAvaliacaoService();
				try {
					ser.incluir(catego);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			request.setAttribute("success", "Avalia&#231;&#227;o cadastrada com sucesso!");
			RequestDispatcher view = request.getRequestDispatcher("novaAvaliacao.jsp");
			view.forward(request, response);
		}
	}
}
