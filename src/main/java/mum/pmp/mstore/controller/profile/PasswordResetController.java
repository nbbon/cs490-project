package mum.pmp.mstore.controller.profile;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import mum.pmp.mstore.model.Email;
import mum.pmp.mstore.model.Password;
import mum.pmp.mstore.model.Profile;
import mum.pmp.mstore.service.email.EmailService;
import mum.pmp.mstore.service.security.ProfileService;

@Controller
public class PasswordResetController {

	@Autowired
	ProfileService profileService;
	
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
			Profile profile = profileService.findByEmail(emailParam.getEmail());
			if(profile != null)
			{
				profile.setToken(profile.getFirstName()+profile.getLastName());
				
				profileService.saveProfile(profile);
				emailService.sendEmail(emailParam.getEmail(), profile.getToken());
			}
			message = "Email sent";
			//model.addAttribute("message", message);
			
			return "password/enter_code";
		
	}
	
	@PostMapping(value = "/forgotpassword")
	public String forgotPassword(Model model, @ModelAttribute("confirmPassword") Password confirmPassword)
	{
		Profile person = profileService.findProfileByToken(confirmPassword.getToken());
		if(person != null)
		{
			return "password/forgot_password";
		}
		return "password/enter_code";
	}
	
	@PostMapping(value = "/resetpassword")
	public String resetPassword(Model model, @ModelAttribute("confirmPassword") Password confirmPassword)
	{
		Profile person = profileService.findProfileByToken(confirmPassword.getToken());
		//System.out.println(person.getToken());
		if(person != null)
		{
			return "password/forgot_password";
		}
		return "password/enter_code";
	}
}
