package mum.pmp.mstore.service.email;

import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import javax.mail.Address;
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

import org.springframework.expression.AccessException;

public class EmailService implements EmailServiceInterface{

	@Override
	public void sendEmail() throws AddressException, MessagingException, IOException {
		   Properties props = emailProperties();
		   
		   Session session = sessionEmailAuth();
		   Message msg = new MimeMessage(session);
		   msg.setFrom(new InternetAddress("storemanagement2019@gmail.com", false));

		   //msg.setRecipients(type, addresses);
		   Address stanley = new InternetAddress("stanley.julien20@gmail.com");
		   InternetAddress store = new InternetAddress("storemanagement2019@gmail.com");
		   //msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse("storemanagement2019@gmail.com"));
		   //msg.setR
		   Address[] emailAddress = {stanley, store};
		   msg.setRecipients(Message.RecipientType.TO, emailAddress);
		   
		   msg.setSubject("Test email");
		   msg.setContent("This is just to test the email module.", "text/html");
		   msg.setSentDate(new Date());

		   MimeBodyPart messageBodyPart = new MimeBodyPart();
		   messageBodyPart.setContent("Tutorials point email", "text/html");

		   Multipart multipart = new MimeMultipart();
		   multipart.addBodyPart(messageBodyPart);
		   //MimeBodyPart attachPart = new MimeBodyPart();

		   //attachPart.attachFile("/var/tmp/image19.png");
		   //multipart.addBodyPart(attachPart);
		   msg.setContent(multipart);
		   Transport.send(msg);   
		}

	@Override
	public Properties emailProperties() {
		Properties props = new Properties();
		   props.put("mail.smtp.auth", "true");
		   props.put("mail.smtp.starttls.enable", "true");
		   props.put("mail.smtp.host", "smtp.gmail.com");
		   props.put("mail.smtp.port", "587");
		return props;
	}

	@Override
	public Session sessionEmailAuth() {
		Properties props = emailProperties();
		   
		   Session session = Session.getInstance(props, new javax.mail.Authenticator() {
		      protected PasswordAuthentication getPasswordAuthentication() {
		         return new PasswordAuthentication("storemanagement2019@gmail.com", "Admin@123");
		      }
		   });
		return session;
	}



}
