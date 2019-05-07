package mum.pmp.mstore.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import mum.pmp.mstore.model.Password;
//import mum.pmp.mstore.model.Vendor;

/*
 * Author: Stanley Julien
 * Date: 25-Apr-2019
 * Class Name: ResetPasswordValidator
 * Module: Reset Password
 * Description: The implementation of reset password  
 * 
 */

@Component
public class ResetPasswordValidator implements Validator{

	@Override
    public boolean supports(Class<?> aClass) {
        return Password.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Password password = (Password) o;
        
        
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password",
    			"required.password", "notEmpty.password");
    		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confirmPassword",
				"required.confirmPassword", "notEmpty.vendor.password");
		
		if(!password.getPassword().equals(password.getConfirmPassword()))
			errors.rejectValue("password", "nomatch.password");
        
    }

    @InitBinder
    protected void initBinder(WebDataBinder binder) { 
    	binder.setValidator(new ResetPasswordValidator());
    }
}
