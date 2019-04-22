package mum.pmp.mstore.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import mum.pmp.mstore.model.Customer;
import mum.pmp.mstore.service.customer.CustomerService;
import mum.pmp.mstore.utilities.PasswordValidator;

@Controller
public class CustomerController {

	@Autowired
	private CustomerService customerService;
	
	@Autowired
	PasswordValidator passwordValidator;
	
	@GetMapping("customerregistration")
	public String customerRegistration(Model model)
	{
		model.addAttribute("customerForm", new Customer());
		return "customer/customerregistration";
	}
	
	@PostMapping("customerregistration")
	public String customerRegistration(@Valid @ModelAttribute("customerForm") Customer customerForm, 
			BindingResult bindingResult)
	{
		//PasswordValidator passwordValidator = new PasswordValidator();
		passwordValidator.validate(customerForm, bindingResult);
		if(bindingResult.hasErrors())
		{
			System.out.println("Inside bindingResult");
			return "customer/customerregistration";
		}
		
		customerService.save(customerForm);
		
		return "redirect:/welcome";
	}
}
