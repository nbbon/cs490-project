package mum.pmp.mstore.integration.mockpayment.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mum.pmp.mstore.integration.mockpayment.model.MasterCard;
import mum.pmp.mstore.integration.mockpayment.model.MasterCardBalance;
import mum.pmp.mstore.integration.mockpayment.model.MasterCardTransaction;
import mum.pmp.mstore.integration.mockpayment.model.VisaCard;
import mum.pmp.mstore.integration.mockpayment.model.VisaCardBalance;
import mum.pmp.mstore.integration.mockpayment.model.VisaCardTransaction;
import mum.pmp.mstore.integration.mockpayment.repository.MasterCardBalanceRepository;
import mum.pmp.mstore.integration.mockpayment.repository.MasterCardRepository;
import mum.pmp.mstore.integration.mockpayment.repository.MasterCardTransactionRepository;
import mum.pmp.mstore.integration.mockpayment.repository.VisaCardBalanceRepository;
import mum.pmp.mstore.integration.mockpayment.repository.VisaCardRepository;
import mum.pmp.mstore.integration.mockpayment.repository.VisaCardTransactionRepository;
import mum.pmp.mstore.integration.mockpayment.service.MockPaymentService;

@Service
public class MockPaymentServiceImpl implements MockPaymentService {

	@Autowired
	MasterCardBalanceRepository masterCardBalanceRepository;

	@Autowired
	MasterCardTransactionRepository masterCardTransactionRepository;

	@Autowired
	MasterCardRepository masterCardRepository;

	@Autowired
	VisaCardBalanceRepository visaCardBalanceRepository;

	@Autowired
	VisaCardTransactionRepository visaCardTransactionRepository;

	@Autowired
	VisaCardRepository visaCardRepository;

	@Override
	public VisaCardTransaction createVisaCardTransaction(VisaCardTransaction transaction) {
		return visaCardTransactionRepository.save(transaction);
	}

	@Override
	public Boolean updateVisaCardBalance(VisaCardBalance cardBalance) {
		return visaCardBalanceRepository.save(cardBalance) != null;
	}

	@Override
	public VisaCardBalance getVisaCardBalance(String cardNumber) {
		Optional<VisaCardBalance> result = visaCardBalanceRepository.findById(cardNumber);
		return result.orElse(null);
	}

	@Override
	public VisaCard getVisaCard(String cardNumber, String cardName, String csv, String expireDate) {
		Optional<VisaCard> result = visaCardRepository.findByCardNumberAndCardNameAndCsvAndExpireDate(cardNumber,
				cardName, csv, expireDate);
		return result.orElse(null);
	}

	@Override
	public MasterCardTransaction createMasterCardTransaction(MasterCardTransaction transaction) {
		System.out.println("createMasterCardTransaction ");
		return masterCardTransactionRepository.save(transaction);
	}

	@Override
	public Boolean updateMasterCardBalance(MasterCardBalance cardBalance) {
		System.out.println("updateMasterCardBalance ");
		return masterCardBalanceRepository.save(cardBalance) != null;
	}

	@Override
	public MasterCardBalance getMasterCardBalance(String cardNumber) {
		Optional<MasterCardBalance> result = masterCardBalanceRepository.findById(cardNumber);
		System.out.println("getMasterCardBalance " + result.isPresent());
		return result.orElse(null);
	}

	@Override
	public MasterCard getMasterCard(String cardNumber, String cardName, String csv, String expireDate) {
		Optional<MasterCard> result = masterCardRepository.findByCardNumberAndCardNameAndCsvAndExpireDate(cardNumber,
				cardName, csv, expireDate);
		System.out.println("getMasterCard " + result.isPresent());
		return result.orElse(null);
	}

}
