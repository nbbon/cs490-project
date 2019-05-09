/*
 * Author: Bon Nguyen
 * Date: 25-Apr-2019
 * Class Name: MasterCardRepository
 * Module: Mock Payment
 * Description: The repository interface MasterCard entity. Used as interface from ER to DB.
 * Used by MockPaymentService
 * 
 */

package mum.pmp.mstore.integration.mockpayment.repository;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mum.pmp.mstore.integration.mockpayment.model.MasterCard;

@Repository
@Transactional
public interface MasterCardRepository extends JpaRepository<MasterCard, String> {

	Optional<MasterCard> findByCardNumberAndCardNameAndCsvAndExpireDate(String cardNumber, String cardName, String csv,
			String expireDate);

}
