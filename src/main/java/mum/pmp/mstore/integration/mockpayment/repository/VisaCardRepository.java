package mum.pmp.mstore.integration.mockpayment.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import mum.pmp.mstore.integration.mockpayment.model.VisaCard;

public interface VisaCardRepository extends JpaRepository<VisaCard, String> {
	
	Optional<VisaCard> findByCardNumberAndCardNameAndCsvAndExpireDate(String cardNumber, String cardName, String csv,
			String expireDate);
}
