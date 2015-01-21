package br.edu.ifpb.caju.util;

import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import br.edu.ifpb.caju.dao.DAO;
import br.edu.ifpb.caju.dao.DAOPresidente;
import br.edu.ifpb.caju.model.Presidente;

public class JavaMail{
           
      public  String enviaEmailEsqueceuSenha(){
    	  
    	  Properties props = new Properties();
          /** Parâmetros de conexão com servidor Gmail */
          props.put("mail.smtp.host", "smtp.gmail.com");
          props.put("mail.smtp.socketFactory.port", "465");
          props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
          props.put("mail.smtp.auth", "true");
          props.put("mail.smtp.port", "465");

          Session session = Session.getDefaultInstance(props,new AutenticarEmail());
 
          session.setDebug(true);
          
          try {

              Message message = new MimeMessage(session);
              message.setFrom(new InternetAddress("cajutsiifpb@gmail.com")); //Remetente
              DAOPresidente dao = new DAOPresidente();
              
              //Pegar Email do Presidente
              DAO.open();
              DAO.begin();
              Presidente p = dao.findAtivo();
              DAO.close();
              
              
              Address[] toUser = InternetAddress.parse(p.getEmail());  

              message.setRecipients(Message.RecipientType.TO, toUser);
              message.setSubject("[Recuperacao_Senha]SistemaCaju");//Assunto
              
              String mensagem = String.format("A sua senha cadastrada é: %s \n Esse email foi enviado automaticamente, não responda esse email.\n Projeto C.A.J.U\n", p.getSenha());
              message.setText(mensagem);
              /**Método para enviar a mensagem criada*/
              Transport.send(message);
              return p.getEmail();
         } catch (MessagingException e) {
              throw new RuntimeException(e);
        }
         
    	  
      }
      
      private class AutenticarEmail extends Authenticator{
    	  
    	  protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("cajutsiifpb@gmail.com", "cajucaju");
          }
      }
}