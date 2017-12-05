package command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.EnviarEmail;
import model.Usuario;

public class Contato implements Command {

	@Override
	public void executar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Usuario usuario = new Usuario();
		HttpSession session = request.getSession();			
		String assunto = request.getParameter("assunto");
		String descricao = request.getParameter("descricao");
		usuario = (Usuario) session.getAttribute("usuario");
	
		EnviarEmail mailer = new EnviarEmail();

		// Configuracao do servidor SMTP
		String host = "smtp.gmail.com";
		String port = "587";
		String mailFrom = "opinowltda@gmail.com";
		String password = "nilone123";

		// Destinatario e Assunto do email
		String mailTo = "opinowltda@gmail.com";
		String subject = "OpiNow - CONTATO EFETUADO PELO SITE";

		// Conteudo do email, pode ter codigo html no corpo
		String message = "<h3>NOVO CONTATO FEITO ATRAVÉS DO SITE OPINOW</h3>" +
				"======================================================<br>" +
				"<p><strong>Nome:</strong> " + usuario.getNome() + "<br>" +
				"<strong>E-mail:</strong> <a href=\"mailto:"+usuario.getEmail()+"\">" + usuario.getEmail() + "</a><p>" +
				"<p><strong>Assunto:</strong> " + assunto + "<br>" +
				"<strong>Mensagem:</strong> <br> "+ descricao + "</strong></p>";

		try {
			mailer.sendHtmlEmail(host, port, mailFrom, password, mailTo, subject, message);
			System.out.println("E-mail enviado.");

		} catch (Exception ex) {
			System.out.println("Falha ao enviar o e-mail.");
			ex.printStackTrace();
		}

		String mensagem = "Sua mensagem foi recebida com sucesso, analisaremos e responderemos em até 5 dias úteis.";
		request.setAttribute("mensagem", mensagem);
		request.getRequestDispatcher("contato.jsp").forward(request, response);
	}
}