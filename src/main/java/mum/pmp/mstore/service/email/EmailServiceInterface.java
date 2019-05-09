package mum.pmp.mstore.service.email;

import java.io.IOException;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.AddressException;

/*
 * Author: Stanley Julien
 * Date: 25-Apr-2019
 * Class Name: EmailServiceInterface
 * Module: SMTP Module
 * Description: Email API. 
 * 
 */

public interface EmailServiceInterface {

	void sendEmail()throws AddressException, MessagingException, IOException;
	void sendEmail(String email, String token)throws AddressException, MessagingException, IOException;
	void sendEmail(String email)throws AddressException, MessagingException, IOException;
	int sendEmail(String emailAddresses, String subject, String body)throws AddressException, MessagingException, IOException;
	void sendEmail(String[] email)throws AddressException, MessagingException, IOException;
	Properties emailProperties();
	Session sessionEmailAuth();
}
