/*
 * Author: Yee Mon Zaw 
 * Date: 02-May-2019
 * Class Name: VendorValidator
 * Package: mum.pmp.mstore.validator
 * Description: Validator class for Vendor 
 * 
 */

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
        
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "vendorName", "notEmpty.vendor.name");
        if(vendor.getVendorName().length() <2 || vendor.getVendorName().length() > 20) 
        	errors.rejectValue("vendorName", "length.vendor.name");
        
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "vendorNumber", "notEmpty.vendor.vendornumber");
        if (vendor.getVendorNumber().length() < 2 || vendor.getVendorNumber().length() > 20) 
            errors.rejectValue("vendorNumber", "length.vendor.number");
        
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "regId", "notEmpty.vendor.regId");
        if(vendor.getRegId().length() <2 || vendor.getRegId().length() > 20) 
        	errors.rejectValue("regId", "length.vendor.regId");
        
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password",
    			"required.password", "notEmpty.vendor.password");
    		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confirmPassword",
				"required.confirmPassword", "notEmpty.vendor.password");
		
		if(!vendor.getPassword().equals(vendor.getConfirmPassword()))
			errors.rejectValue("password", "nomatch.password");
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phone", "notEmpty.phone");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "contactPerson", "notEmpty.vendor.contactperson");
        
        if(vendor.getPhone().length() > 11)
        	errors.rejectValue("phone", "length.vendor.phone");
        
        if(vendor.getContactPerson().length() < 2 || vendor.getContactPerson().length() > 20)
        	errors.rejectValue("contactPerson", "length.vendor.contactperson");
    }

    @InitBinder
    protected void initBinder(WebDataBinder binder) { 
    	binder.setValidator(new VendorValidator());
    }
}