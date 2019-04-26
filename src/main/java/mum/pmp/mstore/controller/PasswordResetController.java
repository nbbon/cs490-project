package mum.pmp.mstore.controller;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import mum.pmp.mstore.model.Email;
import mum.pmp.mstore.model.Password;
import mum.pmp.mstore.model.Person;
import mum.pmp.mstore.service.email.EmailService;
import mum.pmp.mstore.service.security.PersonService;

@Controller
public class PasswordResetController {

	@Autowired
	PersonService personService;
	
	@Autowired
	EmailService emailService;
	
	@Autowired
	Email email;
	
	@GetMapping(value = "/forgotpassword")
	public String enterEmailToResetPassword(Model model, @ModelAttribute("email") Email emailParam) throws AddressException, MessagingException
	{
		model.addAttribute("emailPerson", email);
		return "password/enter_email";
		
	}
	
	@GetMapping(value = "/sendemailforgotpassword")
	public String sendEmailToResetPassword(Model model, @ModelAttribute("email") Email emailParam) throws AddressException, MessagingException
	{
		System.out.println("emailParam: "+emailParam);
		
			//System.out.println("Inside else");
		model.addAttribute("confirmPassword", new Password());
			String message = "";
			System.out.println(emailParam.getEmail());
			Person person = personService.findByEmail(emailParam.getEmail());
			if(person != null)
			{
				person.setToken(person.getFirstName()+person.getLastName());
				personService.savePerson(person);
				emailService.sendEmail(emailParam.getEmail(), person.getToken());
			}
			message = "Email sent";
			//model.addAttribute("message", message);
			
			return "password/enter_code";
		
	}
	
	@PostMapping(value = "/forgotpassword")
	public String forgotPassword(Model model, @ModelAttribute("confirmPassword") Password confirmPassword)
	{
		Person person = personService.findPersonByToken(confirmPassword.getToken());
		if(person != null)
		{
			return "password/forgot_password";
		}
		return "password/enter_code";
	}
	
	@PostMapping(value = "/resetpassword")
	public String resetPassword(Model model, @ModelAttribute("confirmPassword") Password confirmPassword)
	{
		Person person = personService.findPersonByToken(confirmPassword.getToken());
		System.out.println(person.getToken());
		if(person != null)
		{
			return "password/forgot_password";
		}
		return "password/enter_code";
	}
}
