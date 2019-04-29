package mum.pmp.mstore.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import mum.pmp.mstore.model.Address;
import mum.pmp.mstore.model.Customer;


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
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "field.required");
		
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
