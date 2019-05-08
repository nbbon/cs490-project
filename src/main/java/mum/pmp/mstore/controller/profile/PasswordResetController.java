package mum.pmp.mstore.controller.profile;

import java.io.IOException;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import mum.pmp.mstore.model.Email;
import mum.pmp.mstore.model.Password;
import mum.pmp.mstore.model.Profile;
import mum.pmp.mstore.model.User;
import mum.pmp.mstore.repository.profile.UserRepository;
import mum.pmp.mstore.service.ResetPasswordService;
import mum.pmp.mstore.service.email.EmailService;
import mum.pmp.mstore.service.email.EmailServiceInterface;
import mum.pmp.mstore.service.security.ProfileService;
import mum.pmp.mstore.service.security.UserService;
import mum.pmp.mstore.validator.ResetPasswordValidator;

/*
 * Author: Stanley Julien
 * Date: 25-Apr-2019
 * Class Name: PasswordResetController
 * Module: Reset Password
 * Description: The implementation of reset password  
 * 
 */

@Controller
public class PasswordResetController {

	@Autowired
	ProfileService profileService;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	EmailServiceInterface emailService;
	
	@Autowired
	ResetPasswordValidator resetPasswordValidator;
	
	@Autowired
	ResetPasswordService resetPasswordService;
	
	private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	
	@Autowired
	Email email;
	
	@GetMapping(value = "/password/forgotpassword")
	public String enterEmailToResetPassword2(Model model, @ModelAttribute("email") Email emailParam) throws AddressException, MessagingException
	{
		model.addAttribute("emailPerson", email);
		return "password/enter_email";
		
	}
	
	@GetMapping(value = "/forgotpassword")
	public String enterEmailToResetPassword(Model model, @ModelAttribute("email") Email emailParam) throws AddressException, MessagingException
	{
		model.addAttribute("emailPerson", email);
		return "password/enter_email";
		
	}
	
	@GetMapping(value = "/sendemailforgotpassword")
	public String sendEmailToResetPassword(Model model, @ModelAttribute("email") Email emailParam) throws AddressException, MessagingException, IOException
	{
		System.out.println("emailParam: "+emailParam);
		
			//System.out.println("Inside else");
		Password passwordObj = new Password();
		model.addAttribute("passwordObj", passwordObj);
			String message = "";
			System.out.println(emailParam.getEmail());
			Profile profile = profileService.findByEmail(emailParam.getEmail());
			System.out.println(profile.getFirstName()+profile.getLastName());
			if(profile != null)
			{
				int unique = (int) (Math.random() * 9000) + 1000;
				//profile.setToken(profile.getFirstName()+profile.getLastName());
				profile.setToken(""+unique);
				
				profileService.saveProfile(profile);
				emailService.sendEmail(emailParam.getEmail(), profile.getToken());
				//passwordObj.setToken(""+unique);
			}
			message = "Email sent";
			//model.addAttribute("message", message);
			
			return "password/enter_code";
		
	}
	
	@PostMapping(value = "/forgotpassword")
	public String forgotPassword(Model model, @ModelAttribute("passwordObj") Password passwordObj)
	{
		Profile profile = profileService.findProfileByToken(passwordObj.getToken());
		Password passwordObj2 = new Password();
		
		if(profile != null)
		{
			passwordObj2.setToken(profile.getToken());
			model.addAttribute("passwordObj", passwordObj2);
			return "password/forgot_password";
		}
		
		return "password/enter_code";
	}
	
	@PostMapping(value = "/resetpassword")
	public String resetPassword(@ModelAttribute("passwordObj") Password passwordObj, BindingResult bindingResult)
	{
		resetPasswordValidator.validate(passwordObj, bindingResult);
		if(bindingResult.hasErrors())
		{
			return "password/forgot_password";
		}
		
		return resetPasswordService.resetPassword(passwordObj);
		
	}
	
}
