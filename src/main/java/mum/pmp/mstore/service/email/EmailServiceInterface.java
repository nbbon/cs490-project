package mum.pmp.mstore.service.email;

import java.io.IOException;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.AddressException;

public interface EmailServiceInterface {

	void sendEmail()throws AddressException, MessagingException, IOException;
	Properties emailProperties();
	Session sessionEmailAuth();
}
