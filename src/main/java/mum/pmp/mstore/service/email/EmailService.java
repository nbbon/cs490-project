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

public class EmailService implements EmailServiceInterface {

	@Override
	public void sendEmail() throws AddressException, MessagingException, IOException {

		Address stanley = new InternetAddress("stanley.julien20@gmail.com");
		InternetAddress store = new InternetAddress("storemanagement2019@gmail.com");

		Address[] emailAddress = { stanley, store };
		Message msg = message(emailAddress);
		Transport.send(msg);
	}

	@Override
	public void sendEmail(String emailAddresses) throws AddressException, MessagingException {
		Address emailAddress = new InternetAddress(emailAddresses);// {stanley, store};
		Message msg = message(emailAddress);
		Transport.send(msg);
	}

	@Override
	public void sendEmail(String[] emailAddresses) throws AddressException, MessagingException {

		Address[] addresses = new Address[emailAddresses.length];// {stanley, store};
		for (int i = 0; i < emailAddresses.length; i++) {
			addresses[i] = new InternetAddress(emailAddresses[i]);
		}
		Message msg = message(addresses);

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

	private Message message(Address emailAddress) throws AddressException, MessagingException {
		Session session = sessionEmailAuth();
		Message msg = new MimeMessage(session);

		msg.setFrom(new InternetAddress("storemanagement2019@gmail.com", false));

		msg.setRecipient(Message.RecipientType.TO, emailAddress);

		msg.setSubject("Test email");
		msg.setContent("This is just to test the email module.", "text/html");
		msg.setSentDate(new Date());
		MimeBodyPart messageBodyPart = new MimeBodyPart();
		messageBodyPart.setContent("Our test email", "text/html");

		Multipart multipart = new MimeMultipart();
		multipart.addBodyPart(messageBodyPart);
		msg.setContent(multipart);

		return msg;

	}

	private Message message(Address[] emailAddress) throws AddressException, MessagingException {
		Session session = sessionEmailAuth();
		Message msg = new MimeMessage(session);

		msg.setFrom(new InternetAddress("storemanagement2019@gmail.com", false));

		msg.setRecipients(Message.RecipientType.TO, emailAddress);

		msg.setSubject("Test email");
		msg.setContent("This is just to test the email module.", "text/html");
		msg.setSentDate(new Date());
		MimeBodyPart messageBodyPart = new MimeBodyPart();
		messageBodyPart.setContent("Our test email", "text/html");

		Multipart multipart = new MimeMultipart();
		multipart.addBodyPart(messageBodyPart);
		msg.setContent(multipart);

		return msg;

	}

}
