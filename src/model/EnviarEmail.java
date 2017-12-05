package model;

import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
 
public class EnviarEmail {
 
    public void sendHtmlEmail(String host, String port, final String userName, final String password, String toAddress, String subject, String message) throws AddressException, MessagingException, IOException {
 
        // sets SMTP server properties
        Properties properties = new Properties();
        
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", port);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");

        properties.put("mail.transport.protocol", "smtp");

        //adicionei, trata-se de uma lista de hosts confi�veis
        properties.put("mail.smtp.ssl.trust", host);
 
        // creates a new session with an authenticator
        Authenticator auth = new Authenticator() {
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(userName, password);
            }
        };
 
        Session session = Session.getInstance(properties, auth);
 
        // creates a new e-mail message
        Message msg = new MimeMessage(session);
 
        msg.setFrom(new InternetAddress(userName));
        InternetAddress[] toAddresses = { new InternetAddress(toAddress) };
        msg.setRecipients(Message.RecipientType.TO, toAddresses);
        msg.setSubject(subject);
        msg.setSentDate(new Date());

        //esse e o campo de texto da mensagem
        MimeBodyPart mimeBodyPart1 = new MimeBodyPart();
        mimeBodyPart1.setText(message);
        mimeBodyPart1.setHeader("Content-Type", "text/html");

        //agora um pacote que contem todas as partes da mensagem, texto e anexo
        Multipart mp = new MimeMultipart();
        
        //adicionei o texto
        mp.addBodyPart(mimeBodyPart1);

        //configurei como conteudo da mensagem o pacote completo, com texto e anexo
        msg.setContent(mp);
 
        // sends the e-mail       
        Transport.send(msg);
    }
}