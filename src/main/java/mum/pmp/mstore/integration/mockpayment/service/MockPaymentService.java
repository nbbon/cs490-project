/*
 * Author: Bon Nguyen
 * Date: 25-Apr-2019
 * Class Name: MockPaymentService
 * Module: MockPayment
 * Description: Define necessary methods provided for mock payment service  
 * 
 */

package mum.pmp.mstore.integration.mockpayment.service;

import java.time.LocalDate;

import javax.transaction.Transactional;

import mum.pmp.mstore.integration.mockpayment.model.MasterCard;
import mum.pmp.mstore.integration.mockpayment.model.MasterCardBalance;
import mum.pmp.mstore.integration.mockpayment.model.MasterCardTransaction;
import mum.pmp.mstore.integration.mockpayment.model.VisaCard;
import mum.pmp.mstore.integration.mockpayment.model.VisaCardBalance;
import mum.pmp.mstore.integration.mockpayment.model.VisaCardTransaction;

public interface MockPaymentService {
	@Transactional
	default public Boolean processVisaCardPaymentRequest(String fromCardNumber, String fromCardName, String fromCardCSV,
			String fromCardExpireDate, String toCardNumber, String toCardName, String toCardCSV, String toCardExpireDate, Double amount,
			String description) {
		if (getVisaCard(fromCardNumber, fromCardName, fromCardCSV, fromCardExpireDate) != null 
				&& getVisaCard(toCardNumber, toCardName, toCardCSV, toCardExpireDate) != null) {
			VisaCardBalance fromCardBalance = getVisaCardBalance(fromCardNumber);
			if (fromCardBalance != null && amount <= fromCardBalance.getBalance()) {
				Double fromCardNewBalance = fromCardBalance.getBalance() - amount;
				fromCardBalance.setBalance(fromCardNewBalance);
				updateVisaCardBalance(fromCardBalance);
				VisaCardTransaction fromTrans = new VisaCardTransaction(fromCardNumber, fromCardName, LocalDate.now(),
						amount, fromCardBalance.getBalance(), fromCardNewBalance, description);
				createVisaCardTransaction(fromTrans);
				
				VisaCardBalance toCardBalance = getVisaCardBalance(toCardNumber);
				if(toCardBalance == null) {
					toCardBalance = new VisaCardBalance(toCardNumber, LocalDate.now(), 0.0);
				}
				Double toCardNewBalance = toCardBalance.getBalance() + amount;
				toCardBalance.setBalance(toCardNewBalance);
				updateVisaCardBalance(toCardBalance);
				VisaCardTransaction toTrans = new VisaCardTransaction(toCardNumber, toCardName, LocalDate.now(),
						amount, toCardBalance.getBalance(), toCardNewBalance, description);
				createVisaCardTransaction(toTrans);
				return true;			
			} else {
				System.out.println("Visa Credit card amount is not enough for making payment");
			}
		} else {
			System.out.println("Visa Credit card is not found");
		}
		return false;
	};

	public VisaCardTransaction createVisaCardTransaction(VisaCardTransaction transaction);

	public Boolean updateVisaCardBalance(VisaCardBalance cardBalance);

	public VisaCardBalance getVisaCardBalance(String cardNumber);

	public VisaCard getVisaCard(String cardNumber, String cardName, String csv, String expireDate);

	@Transactional
	default public Boolean processMasterCardPaymentRequest(String fromCardNumber, String fromCardName, String fromCardCSV,
			String fromCardExpireDate, String toCardNumber, String toCardName, String toCardCSV, String toCardExpireDate, Double amount,
			String description) {
		if (getMasterCard(fromCardNumber, fromCardName, fromCardCSV, fromCardExpireDate) != null 
				&& getMasterCard(toCardNumber, toCardName, toCardCSV, toCardExpireDate) != null) {
			MasterCardBalance fromCardBalance = getMasterCardBalance(fromCardNumber);
			if (fromCardBalance != null && amount <= fromCardBalance.getBalance()) {
				Double fromCardNewBalance = fromCardBalance.getBalance() - amount;
				fromCardBalance.setBalance(fromCardNewBalance);
				updateMasterCardBalance(fromCardBalance);
				MasterCardTransaction fromTrans = new MasterCardTransaction(fromCardNumber, fromCardName, LocalDate.now(),
						amount, fromCardBalance.getBalance(), fromCardNewBalance, description);
				createMasterCardTransaction(fromTrans);
				
				MasterCardBalance toCardBalance = getMasterCardBalance(toCardNumber);
				if(toCardBalance == null) {
					toCardBalance = new MasterCardBalance(toCardNumber, LocalDate.now(), 0.0);
				}
				Double toCardNewBalance =  toCardBalance.getBalance() + amount;
				toCardBalance.setBalance(toCardNewBalance);
				updateMasterCardBalance(toCardBalance);
				MasterCardTransaction toTrans = new MasterCardTransaction(toCardNumber, toCardName, LocalDate.now(),
						amount, toCardBalance.getBalance(), toCardNewBalance, description);
				createMasterCardTransaction(toTrans);
				return true;			
			}else {
				System.out.println("Master Credit card amount is not enough for making payment");
			}
		}else {
			System.out.println("Master Credit card is not found");
		}
		return false;
	};

	public MasterCardTransaction createMasterCardTransaction(MasterCardTransaction transaction);

	public Boolean updateMasterCardBalance(MasterCardBalance cardBalance);

	public MasterCardBalance getMasterCardBalance(String cardNumber);

	public MasterCard getMasterCard(String cardNumber, String cardName, String csv, String expireDate);

}
