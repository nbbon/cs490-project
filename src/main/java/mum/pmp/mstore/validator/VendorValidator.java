package mum.pmp.mstore.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import mum.pmp.mstore.model.Vendor;

@Component
public class VendorValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return Vendor.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Vendor vendor = (Vendor) o;
        
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "vendorName", "NotEmpty");
        if(vendor.getVendorName().length() <2 || vendor.getVendorName().length() > 20) {
        	errors.rejectValue("vendorName", "Vendor name must be 2 to 20 alpha numeric characters.");
        }
        
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "vendorNumber", "NotEmpty");
        if (vendor.getVendorNumber().length() < 2 || vendor.getVendorNumber().length() > 20) {
            errors.rejectValue("vendorNumber", "Vendor number must be 2 to 20 alpha numeric characters.");
        }
        
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "regId", "NotEmpty");
        if(vendor.getRegId().length() <2 || vendor.getRegId().length() > 20) {
        	errors.rejectValue("regId", "vendor.regId.length");
        }
        
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password",
    			"required.password", "Field name is required.");
    		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confirmPassword",
				"required.confirmPassword", "Field name is required.");
		
		if(!vendor.getPassword().equals(vendor.getConfirmPassword()))
		{
			errors.rejectValue("password", "nomatch.password");
		}
		
    		
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "contactPerson", "NotEmpty");
        if(vendor.getContactPerson().length() < 2 || vendor.getContactPerson().length() > 20)
        {
        	errors.rejectValue("contactPerson", "vendor.contactperson.length");
        }
        
    }

    @InitBinder
    protected void initBinder(WebDataBinder binder) { 
    	binder.setValidator(new VendorValidator());
    }
}