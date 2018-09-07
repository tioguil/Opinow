package command;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.EnviarEmail;
import model.Usuario;
import service.UsuarioService;

public class RecuperarSenha implements Command {

	@Override
	public void executar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Usuario usuario = new Usuario();
		usuario.setEmail(request.getParameter("email"));
		usuario.setCpf(request.getParameter("cpf").replaceAll("[.-]", ""));

		UsuarioService us = new UsuarioService();
		usuario = us.validarEmailCpf(usuario);

		if(usuario.getId() == -1 || !usuario.getCpf().equals(request.getParameter("cpf").replaceAll("[.-]", ""))) {
			String mensagemRecuperar = "E-mail e/ou CPF inv&#225;lidos!";
			request.setAttribute("mensagemRecuperar", mensagemRecuperar);
			RequestDispatcher view = request.getRequestDispatcher("logar.jsp");
			view.forward(request, response);
		} else {
			EnviarEmail mailer = new EnviarEmail();

			// Configuracao do servidor SMTP
			String host = "smtp.gmail.com";
			String port = "587";
			String mailFrom = "opinowltda@gmail.com";
			String password = "123456";

			// Destinatario e Assunto do email
			String mailTo = usuario.getEmail();
			String subject = "OpiNow - RECUPERAÇÃO DE SENHA";

			// Gera uma nova senha aleatoria atraves do metodo randomUUID de 8 caracteres. 
			UUID uuid = UUID.randomUUID();
			String senha = (uuid.toString().substring(0,8));

			// Conteudo do email, pode ter codigo html no corpo
			String message = "<p>Prezado <i>"+usuario.getNome()+"</i> conforme solicitado, segue a sua nova senha.</p>";
			message += "<p>Senha de Acesso: <b>"+senha+"</b></p>";
			message += "<p>Caso queira alterar a sua senha, realize o acesso com a senha gerada e clique em 'Editar Perfil' para efetuar a troca.</p>";
			message += "<b style='color:red;'>Equipe OpiNow</b>";

			try {
				mailer.sendHtmlEmail(host, port, mailFrom, password, mailTo, subject, message);
				System.out.println("E-mail enviado.");
				usuario.setSenha(senha);
				us.atualizar(usuario);
			} catch (Exception ex) {
				System.out.println("Falha ao enviar o e-mail.");
				ex.printStackTrace();
			}

			String mensagemRecuperar = "Uma nova senha foi gerada e enviada para o e-mail cadastrado!";
			request.setAttribute("mensagemRecuperar", mensagemRecuperar);
			request.getRequestDispatcher("ServletController?command=MelhoresAvaliados").forward(request, response);
		}
	}
}
