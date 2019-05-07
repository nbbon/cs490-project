package mum.pmp.mstore.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import org.hibernate.id.Assigned;

@Entity
//@Table(name = "Credit")
public class CreditCard {
	/*
	 * @Id
	 * 
	 * @GeneratedValue(strategy = GenerationType.AUTO) private int id;
	 */

	/*
	 * @EmbeddedId
	 * 
	 * @NotEmpty(message = "Validation card number is required") CreditCardNumber
	 * cardNumber
	 */;

	// private String cardNumber;

	@Id
	private int id;
	
	@NotEmpty(message = "Validation card number is required")
	private Integer cardNumber;

	@NotEmpty(message = "Validation name on card is required")
	private String cardName;

	@NotEmpty(message = "Validation expire date is required")
	private String expireDate;

	@NotEmpty(message = "Validation security code is required")
	private String csv;

	private Integer cardType; // 1: Visa; 2: Master

	public CreditCard() {
	}

	public CreditCard(@NotEmpty(message = "Validation card number is required") String cardNumber,

			@NotEmpty(message = "Validation name on card is required") String cardName,

			@NotEmpty(message = "Validation expire date is required") String expireDate,

			@NotEmpty(message = "Validation security code is required") String csv) {
		super();
		this.cardNumber = Integer.valueOf(cardNumber);
		this.cardName = cardName;
		this.expireDate = expireDate;
		this.csv = csv;
	}

	public void setCardNumber(Integer cardNumber) {
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

	public void setCardNumber(int cardNumber) {
		this.cardNumber = cardNumber;
	}

	public Integer getCardNumber() {
		return cardNumber;
	}

	public int getId() { return this.id; }
	

}
