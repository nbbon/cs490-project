package mum.pmp.mstore.model;

import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

public class CreditCard {
	@Id
	private String cardNumber;
	
	@NotEmpty(message = "Validation Date is required")
	private String validationDate;

	public CreditCard(String cardNumber, @NotEmpty(message = "Validation Date is required") String validationDate) {
		super();
		this.cardNumber = cardNumber;
		this.validationDate = validationDate;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getValidationDate() {
		return validationDate;
	}

	public void setValidationDate(String validationDate) {
		this.validationDate = validationDate;
	}
	
	
}
