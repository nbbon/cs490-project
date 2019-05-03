package mum.pmp.mstore.integration.mockpayment.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mum.pmp.mstore.integration.mockpayment.model.MasterCard;

@Repository
public interface MasterCardRepository extends JpaRepository<MasterCard, String> {

	Optional<MasterCard> findByCardNumberAndCardNameAndCsvAndExpireDate(String cardNumber, String cardName, String csv,
			String expireDate);

}
