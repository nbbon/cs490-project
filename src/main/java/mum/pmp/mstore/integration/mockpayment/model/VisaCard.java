package mum.pmp.mstore.integration.mockpayment.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class VisaCard {
	@Id
	private String cardNumber;
	private String cardName;
	private String csv;
	private String expireDate;
	private Double balance;
	
	public VisaCard() {
		
	}
	public VisaCard(String cardNumber, String cardName, String csv, String expireDate, Double balance) {
		super();
		this.cardNumber = cardNumber;
		this.cardName = cardName;
		this.csv = csv;
		this.expireDate = expireDate;
		this.balance = balance;
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
	public String getCsv() {
		return csv;
	}
	public void setCsv(String csv) {
		this.csv = csv;
	}
	public String getExpireDate() {
		return expireDate;
	}
	public void setExpireDate(String expireDate) {
		this.expireDate = expireDate;
	}
	public Double getBalance() {
		return balance;
	}
	public void setBalance(Double balance) {
		this.balance = balance;
	}
	
	
}
