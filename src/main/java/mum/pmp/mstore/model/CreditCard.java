package mum.pmp.mstore.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

@Entity
public class CreditCard {
	@Id
	@NotEmpty(message = "Validation card number is required")
	private String cardNumber;
	
	@NotEmpty(message = "Validation name on card is required")
	private String cardName;
	
	@NotEmpty(message = "Validation expire date is required")
	private String expireDate;
	
	@NotEmpty(message = "Validation security code is required")
	private String csv;
	
	private Integer cardType; // 1: Visa; 2: Master
	
	public CreditCard() {}
	
	public CreditCard(@NotEmpty(message = "Validation card number is required") String cardNumber,
			@NotEmpty(message = "Validation name on card is required") String cardName,
			@NotEmpty(message = "Validation expire date is required") String expireDate,
			@NotEmpty(message = "Validation security code is required") String csv) {
		super();
		this.cardNumber = cardNumber;
		this.cardName = cardName;
		this.expireDate = expireDate;
		this.csv = csv;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getCardName() {
		return cardName;
	}

	public void setCardName(String cardName) {
		this.cardName = cardName;
	}

	public String getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(String expireDate) {
		this.expireDate = expireDate;
	}

	public String getCsv() {
		return csv;
	}

	public void setCsv(String csv) {
		this.csv = csv;
	}

	public Integer getCardType() {
		return cardType;
	}

	public void setCardType(Integer cardType) {
		this.cardType = cardType;
	}

	
	
}
