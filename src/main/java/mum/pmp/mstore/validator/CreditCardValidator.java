/*
 * Author: Yee Mon Zaw 
 * Date: 02-May-2019
 * Class Name: CreditCardValidator
 * Package: mum.pmp.mstore.validator
 * Description: Validator class for Credit Card 
 * 
 */

package mum.pmp.mstore.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import mum.pmp.mstore.model.CreditCard;
import mum.pmp.mstore.model.Vendor;

@Component
public class CreditCardValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return Vendor.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        CreditCard cc = (CreditCard) o;
        
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "creditCard.cardName", "notEmpty.creditcard.name");
        if(cc.getCardName().length() <2 || cc.getCardName().length() > 20) 
        	errors.rejectValue("creditCard.cardName", "length.creditcard.name");
        
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "creditCard.cardNumber", "notEmpty.creditcard.number");
        if (cc.getCardNumber().length() != 16) 
            errors.rejectValue("creditCard.cardNumber", "length.creditcard.cardNumber");
        
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "creditCard.csv", "notEmpty.creditcard.csv");
        if(cc.getCsv().length() <1 || cc.getCsv().length() > 4) 
        	errors.rejectValue("creditCard.csv", "length.creditcard.csv");
        
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "creditCard.expireDate", "notEmpty.creditcard.expireDate");
        if(cc.getCsv().length() <2 || cc.getCsv().length() > 20) 
        	errors.rejectValue("creditCard.expireDate", "length.creditcard.expireDate");
        
    }

    @InitBinder
    protected void initBinder(WebDataBinder binder) { 
    	binder.setValidator(new CreditCardValidator());
    }
}