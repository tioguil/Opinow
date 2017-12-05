package filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.Usuario;

@WebFilter(urlPatterns = { "/home.jsp", "/novaAvaliacao.jsp", "/editar_perfil.jsp", "/contato.jsp", "/ServletController" })

public class FilterLogado implements Filter {

	public void destroy() {}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession();
		Usuario usuario = new Usuario();

		String uri = req.getRequestURI();

		usuario = (Usuario) session.getAttribute("usuario");
		String command;

		if (req.getParameter("command") == null)
			command = "";
		else
			command = req.getParameter("command");

		if (usuario == null && !command.equals("CadastrarUsuario") && !command.equals("LoginUsuario") && !command.equals("RecuperarSenha")) {
			if (!command.equals("")) {
				if (command.equals("ListarAvaliacao") || command.equals("CriarAvaliacao"))
					req.getRequestDispatcher("ServletController?command=ListarEstabelecimento").forward(request, response);
				else
					req.getRequestDispatcher("ServletController?command=" + command).forward(request, response);
			} else {
				if (uri.equals("/opinow/novaAvaliacao.jsp") || uri.equals("/opinow/editar_perfil.jsp") || uri.equals("/opinow/contato.jsp"))
					req.getRequestDispatcher("ServletController?command=ListarEstabelecimento").forward(request, response);
				else
					req.getRequestDispatcher("ServletController?command=MelhoresAvaliados").forward(request, response);
			}
		} else
			chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {}

}