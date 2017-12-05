package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ServletIndex
 */
@WebServlet("")
public class ServletIndex extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doExecute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getParameter("command") == null){
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("ServletController?command=MelhoresAvaliados");
			dispatcher.forward(request, response);
		}
		
	}	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doExecute(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doExecute(request, response);
	}

}
