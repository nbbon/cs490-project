package mum.pmp.mstore.model;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class CreditCardNumber implements Serializable{

	String cardNumber;
	
	public CreditCardNumber()
	{
		
	}
	
	public CreditCardNumber(String cardNumber)
	{
		this.cardNumber = cardNumber;
	}
	
	public String getCardNumber()
	{
		return cardNumber;
	}
}
