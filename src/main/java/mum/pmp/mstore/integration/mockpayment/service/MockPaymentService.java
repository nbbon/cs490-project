package mum.pmp.mstore.integration.mockpayment.service;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

import mum.pmp.mstore.integration.mockpayment.model.MasterCard;
import mum.pmp.mstore.integration.mockpayment.model.MasterCardBalance;
import mum.pmp.mstore.integration.mockpayment.model.MasterCardTransaction;
import mum.pmp.mstore.integration.mockpayment.model.VisaCard;
import mum.pmp.mstore.integration.mockpayment.model.VisaCardBalance;
import mum.pmp.mstore.integration.mockpayment.model.VisaCardTransaction;

public interface MockPaymentService {
	default public VisaCardTransaction processVisaCardPaymentRequest(String cardNumber, String cardName, String csv, String expireDate, Double amount, String description) {
		if (getVisaCard(cardNumber, cardName, csv, expireDate) != null) {
			VisaCardBalance cardBalance = getVisaCardBalance(cardNumber);
			if (amount <= cardBalance.getBalance()) {
				Double remainBalance = cardBalance.getBalance() - amount;
				cardBalance.setBalance(remainBalance);
				if (updateVisaCardBalance(cardBalance)) {
					VisaCardTransaction transaction = new VisaCardTransaction(cardNumber, cardName, LocalDate.now(), amount,
							cardBalance.getBalance(), remainBalance, description);
					return createVisaCardTransaction(transaction);
				}
			}
		}
		return null;
	};

	public VisaCardTransaction createVisaCardTransaction(VisaCardTransaction transaction);
	public Boolean updateVisaCardBalance(VisaCardBalance cardBalance);
	public VisaCardBalance getVisaCardBalance(String cardNumber);
	public VisaCard getVisaCard(String cardNumber, String cardName, String csv, String expireDate);

	default public MasterCardTransaction processMasterCardPaymentRequest(String cardNumber, String cardName, String csv, String expireDate,
			Double amount, String description) {
		if (getMasterCard(cardNumber, cardName, csv, expireDate) != null) {
			MasterCardBalance cardBalance = getMasterCardBalance(cardNumber);
			if (amount <= cardBalance.getBalance()) {
				Double remainBalance = cardBalance.getBalance() - amount;
				cardBalance.setBalance(remainBalance);
				if (updateMasterCardBalance(cardBalance)) {
					MasterCardTransaction transaction = new MasterCardTransaction(cardNumber, cardName, LocalDate.now(), amount,
							cardBalance.getBalance(), remainBalance, description);
					return createMasterCardTransaction(transaction);
				}
			}
		}
		return null;
	}

	public MasterCardTransaction createMasterCardTransaction(MasterCardTransaction transaction);
	public Boolean updateMasterCardBalance(MasterCardBalance cardBalance);
	public MasterCardBalance getMasterCardBalance(String cardNumber);
	public MasterCard getMasterCard(String cardNumber, String cardName, String csv, String expireDate);

}
