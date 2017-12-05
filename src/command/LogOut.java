package command;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogOut implements Command {

	@Override
	public void executar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		session = request.getSession();
		session.invalidate();
		RequestDispatcher dispatcher = request.getRequestDispatcher("ServletController?command=MelhoresAvaliados");
		dispatcher.forward(request, response);
	}
}