package mum.pmp.mstore.integration.mockpayment.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MasterCardBalance {
	@Id
	private String cardNumber;
	private LocalDate lastUpdated;
	private Double balance;
	public MasterCardBalance(String cardNumber, LocalDate lastUpdated, Double balance) {
		super();
		this.cardNumber = cardNumber;
		this.lastUpdated = lastUpdated;
		this.balance = balance;
	}
	public String getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	public LocalDate getLastUpdated() {
		return lastUpdated;
	}
	public void setLastUpdated(LocalDate lastUpdated) {
		this.lastUpdated = lastUpdated;
	}
	public Double getBalance() {
		return balance;
	}
	public void setBalance(Double balance) {
		this.balance = balance;
	}
}
