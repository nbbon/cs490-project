package mum.pmp.mstore.integration.mockpayment.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class VisaCardTransaction {
	@Id
	private Long id;
	private String cardNumber;
	private String cardName;
	private LocalDate tDate;
	private Double tAmount;
	private Double preBalance;
	private Double remainBalance;
	private String description;
	public VisaCardTransaction(String cardNumber, String cardName, LocalDate tDate, Double tAmount, Double preBalance,
			Double remainBalance, String description) {
		this.cardNumber = cardNumber;
		this.cardName = cardName;
		this.tDate = tDate;
		this.tAmount = tAmount;
		this.preBalance = preBalance;
		this.remainBalance = remainBalance;
		this.description = description;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
	public LocalDate gettDate() {
		return tDate;
	}
	public void settDate(LocalDate tDate) {
		this.tDate = tDate;
	}
	public Double gettAmount() {
		return tAmount;
	}
	public void settAmount(Double tAmount) {
		this.tAmount = tAmount;
	}
	public Double getPreBalance() {
		return preBalance;
	}
	public void setPreBalance(Double preBalance) {
		this.preBalance = preBalance;
	}
	public Double getRemainBalance() {
		return remainBalance;
	}
	public void setRemainBalance(Double remainBalance) {
		this.remainBalance = remainBalance;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
