package email.utils;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import email.bean.MailBean;



public class EmailUtils {

	
public int enviarCorreo(MailBean mailBean ){
		
		Properties props = new Properties();

		props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
		
		// Nombre del host de correo, es smtp.gmail.com
		props.setProperty("mail.smtp.host", "smtp.gmail.com");
		
		// TLS si está disponible
		props.setProperty("mail.smtp.starttls.enable", "true");

		// Puerto de gmail para envio de correos
		props.setProperty("mail.smtp.port","587");

		// Nombre del usuario
		props.setProperty("mail.smtp.user", mailBean.getCorreoEmisor());

		// Si requiere o no usuario y password para conectarse.
		props.setProperty("mail.smtp.auth", "true");
		
		
			
		Session session = Session.getDefaultInstance(props);
	
	
		 
		 session.setDebug(true);
		MimeMessage message = new MimeMessage(session);
		try {
			// Quien envia el correo
//			message.setFrom(new InternetAddress(mailBean.getCorreoEmisor()));
			 message.setFrom(new InternetAddress(mailBean.getCorreoEmisor(), "SERVICE EXPRESS"));

			// A quien va dirigido
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(mailBean.getCorreoEmisor())); 
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(mailBean.getCorreoReceptor()));
		

			
			message.setSubject(mailBean.getTitle());
		
			
			
	         // This mail has 2 part, the BODY and the embedded image
	         MimeMultipart multipart = new MimeMultipart("related");

	         // first part (the html)
	         BodyPart messageBodyPart = new MimeBodyPart();
	         String htmlText = "<H1 align='center'>"+mailBean.getTitle()+"</H1><div align='center' ; box-shadow: 0 4px 8px 0 rgba(0,0,0,0.2);transition: 0.3s;width: 100%;border-radius: 5px; ><img style='width:800px;' src=\"http://www.gestionesmoviles.pe/wp-content/uploads/2016/08/courier-en-lima.jpg\"> </div> "
	         		+ "<br><div align='center' style=' box-shadow: 0 4px 8px 0 rgba(0,0,0,0.2);transition: 0.3s; width: 100%;border-radius: 5px;color:grey;'> "
	         		+mailBean.getMessage() + "<br>Acceder <a href='http://localhost:8080/Service_Express_PI/index.xhtml'>Click Aqui </a>"+"</div>";
	        
	         messageBodyPart.setContent(htmlText, "text/html");
	         // add it
	         multipart.addBodyPart(messageBodyPart);

	         // second part (the image)
//	         messageBodyPart = new MimeBodyPart();

//	         DataSource fds = new FileDataSource("d:/SERVICEEXPRESS.png");
//	         messageBodyPart.setDataHandler(new DataHandler(fds));
//	         messageBodyPart.setHeader("Content-ID", "<image>");
//	         messageBodyPart.setFileName("img.png");
//	         // add image to the multipart
//	         multipart.addBodyPart(messageBodyPart);

	         
	         // put everything together
	         message.setContent(multipart);
	         
			
	         
			Transport t = session.getTransport("smtp");
			
			t.connect(mailBean.getCorreoEmisor(),mailBean.getPassword());
			t.sendMessage(message,message.getAllRecipients());
			t.close();
			System.out.println("Termino envio");
			
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
		
		}catch (Exception e) {
			// TODO: handle exception
		}
		
		
		return 1;
	}
}
