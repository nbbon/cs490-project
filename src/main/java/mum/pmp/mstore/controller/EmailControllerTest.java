package mum.pmp.mstore.controller;

import java.io.IOException;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import mum.pmp.mstore.service.email.EmailService;
import mum.pmp.mstore.service.email.EmailServiceInterface;

@RestController
public class EmailControllerTest {

	@GetMapping("/email")
	public String sendEmail() throws AddressException, MessagingException, IOException {

		EmailServiceInterface email = new EmailService();
		email.sendEmail();
		return "email send successfully!";
	}
}
