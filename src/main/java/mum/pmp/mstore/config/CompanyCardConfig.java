package mum.pmp.mstore.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:application.properties")
public class CompanyCardConfig {

	// Visa Card
	
	@Value("${company.credit.visa.card.number}")
	private String visaCardName;

	@Value("${company.credit.visa.card.name}")
	private String visaCardNumber;

	@Value("${company.credit.visa.card.csv}")
	private String visaCardCSV;
	
	@Value("${company.credit.visa.card.expireDate}")
	private String visaCardexpireDate;
	


	// Master Card
	@Value("${company.credit.master.card.number}")
	private String masterCardName;

	@Value("${company.credit.master.card.name}")
	private String masterCardNumber;

	@Value("${company.credit.master.card.csv}")
	private String masterCardCSV;
	
	@Value("${company.credit.master.card.expireDate}")
	private String masterCardexpireDate;

	public String getVisaCardName() {
		return visaCardName;
	}

	public String getVisaCardNumber() {
		return visaCardNumber;
	}

	public String getVisaCardCSV() {
		return visaCardCSV;
	}

	public String getVisaCardexpireDate() {
		return visaCardexpireDate;
	}

	public String getMasterCardName() {
		return masterCardName;
	}

	public String getMasterCardNumber() {
		return masterCardNumber;
	}

	public String getMasterCardCSV() {
		return masterCardCSV;
	}

	public String getMasterCardexpireDate() {
		return masterCardexpireDate;
	}

	
}
