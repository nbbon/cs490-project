package mum.pmp.mstore.utilities;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import mum.pmp.mstore.model.Customer;

@PropertySource("classpath:validation/customer.properties")
@Component
public class PasswordValidator implements Validator{

	@Autowired
	private Environment env;
	
	@Override
	public boolean supports(Class<?> clazz) {

		return Customer.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password",
				"required.password", "Field name is required.");
			
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "passwordConfirm",
					"required.passwordConfirm", "Field name is required.");
			
			Customer cust = (Customer)target;
			
			if(!(cust.getPassword().equals(cust.getPasswordConfirm()))){
				errors.rejectValue("password", "notmatch.password", env.getProperty("notmatch.password"));
				errors.rejectValue("passwordConfirm", "notmatch.password", env.getProperty("notmatch.password"));
				//errors.rejectValue("password", "password not match");
			}
		
	}

	
}
