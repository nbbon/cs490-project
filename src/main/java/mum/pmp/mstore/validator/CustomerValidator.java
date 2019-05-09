package mum.pmp.mstore.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import mum.pmp.mstore.model.Address;
import mum.pmp.mstore.model.Customer;

/*
 * Author: Stanley Julien
 * Date: 25-Apr-2019
 * Class Name: CustomerValidator
 * Module: Reset Password
 * Description: The implementation of reset password  
 * 
 */

@Component
public class CustomerValidator implements Validator {

	private final Validator addressValidator;
	
	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return false;
	}

	public void validate(Object target, Errors errors) {
		
		Customer customer = (Customer) target;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "field.required");
		if (customer.getFirstName().length() < 2 || customer.getFirstName().length() > 20) {
			errors.rejectValue("firstName", "First name must be 2 to 20 alpha numeric characters.");
		}
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "field.required");
		if (customer.getLastName().length() < 2 || customer.getLastName().length() > 20) {
			errors.rejectValue("lastName", "Last name must be 2 to 20 alpha numeric characters.");
		}
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "Password is required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confirmPassword", "Confirm password is required");
		if (!customer.getPassword().equals(customer.getConfirmPassword())) {
			errors.rejectValue("password", "nomatch.password");
		}
		
		try {
			errors.pushNestedPath("address");
			ValidationUtils.invokeValidator(this.addressValidator, customer.getAddress(), errors);
		} finally {
			errors.popNestedPath();
		}
	}

	public CustomerValidator(Validator addressValidator) {
		if (addressValidator == null) {
			throw new IllegalArgumentException("The supplied [Validator] is " + "required and must not be null.");
		}
		if (!addressValidator.supports(Address.class)) {
			throw new IllegalArgumentException(
					"The supplied [Validator] must " + "support the validation of [Address] instances.");
		}
		this.addressValidator = addressValidator;
	}
	
	
}
