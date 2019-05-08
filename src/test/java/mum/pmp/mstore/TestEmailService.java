package mum.pmp.mstore;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.junit.Test;

import mum.pmp.mstore.service.email.EmailService;
import mum.pmp.mstore.service.email.EmailServiceInterface;

public class TestEmailService extends MStoreApplicationTests{

	@Test
	public void testSendEmail() throws AddressException, MessagingException, IOException
	{
		EmailServiceInterface emailService = new EmailService();
		int status = emailService.sendEmail("tijesus151@gmail.com", "Test", "Test my JUnit test case.");
		assertEquals(1, status);
	}
}
