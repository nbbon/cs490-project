/*
 * Author: Bon Nguyen
 * Date: 25-Apr-2019
 * Class Name: VisaCardBalanceRepository
 * Module: Mock Payment
 * Description: The repository interface VisaCard entity. Used as interface from ER to DB.
 * Used by MockPaymentService
 * 
 */

package mum.pmp.mstore.integration.mockpayment.repository;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mum.pmp.mstore.integration.mockpayment.model.VisaCard;

@Repository
@Transactional
public interface VisaCardRepository extends JpaRepository<VisaCard, String> {
	
	Optional<VisaCard> findByCardNumberAndCardNameAndCsvAndExpireDate(String cardNumber, String cardName, String csv,
			String expireDate);
}
