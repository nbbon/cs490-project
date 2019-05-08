package mum.pmp.mstore.integration.mockpayment.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class MasterCardTransaction {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String cardNumber;
	private String cardName;
	private LocalDate tDate;
	private Double tAmount;
	private Double prevBalance;
	private Double remainBalance;
	private String description;
	
	public MasterCardTransaction() {
		
	}
	public MasterCardTransaction(String cardNumber, String cardName, LocalDate tDate, Double tAmount, Double prevBalance,
			Double remainBalance, String description) {
		this.cardNumber = cardNumber;
		this.cardName = cardName;
		this.tDate = tDate;
		this.tAmount = tAmount;
		this.prevBalance = prevBalance;
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

	public Double getPrevBalance() {
		return prevBalance;
	}

	public void setPrevBalance(Double prevBalance) {
		this.prevBalance = prevBalance;
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
